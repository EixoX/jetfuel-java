package com.eixox;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.Normalizer;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public final class Urls {

	// _____________________________________________________________________________________________
	public static final String downloadText(String url) throws IOException {
		String charset = "UTF-8";
		URLConnection connection = new URL(url).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream inputStream = null;
		try {
			inputStream = connection.getInputStream();
			return Streams.readText(inputStream, Charset.forName(charset));
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException logOrIgnore) {
				}
		}
	}

	public static final Document downloadXml(String url)
			throws IOException, ParserConfigurationException, SAXException {

		URLConnection connection = new URL(url).openConnection();
		connection.setRequestProperty("Accept", "application/xml");

		DocumentBuilderFactory objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
		Document doc = objDocumentBuilder.parse(connection.getInputStream());

		return doc;
	}

	// _____________________________________________________________________________________________
	public static final HashMap<String, String> parseParameters(String encodedParameters) {
		if (encodedParameters == null || encodedParameters.isEmpty())
			return null;

		HashMap<String, String> hashmap = new HashMap<String, String>();
		String[] keyvaluePairs = encodedParameters.split("&");
		for (int i = 0; i < keyvaluePairs.length; i++) {
			String[] keyvalues = keyvaluePairs[i].split("=");
			if (keyvalues.length > 0) {
				if (keyvalues.length == 1)
					hashmap.put(keyvalues[0], keyvalues[0]);
				else
					hashmap.put(keyvalues[0], keyvalues[1]);
			}
		}

		return hashmap;
	}

	// _____________________________________________________________________________________________
	public static final String friendlyfy(String url) {
		if (url == null || url.isEmpty())
			return url;

		StringBuilder builder = new StringBuilder(url.length());
		boolean isPreviousNonLetterOrDigit = false;
		for (int i = 0; i < url.length(); i++)
			if (Character.isLetterOrDigit(url.charAt(i))) {
				builder.append(Character.toLowerCase(url.charAt(i)));
				isPreviousNonLetterOrDigit = false;
			} else if (!isPreviousNonLetterOrDigit && i < (url.length() - 1)) {
				builder.append('-');
				isPreviousNonLetterOrDigit = true;
			}

		return Normalizer.normalize(builder.toString().toLowerCase(), Normalizer.Form.NFD);

	}

	public static String getFrom(String url) throws IOException {
		return getFrom(url, null, "application/x-www-form-urlencoded");
	}

	public static String getFrom(String url, String basicAuth, String contentType) throws IOException {
		return request(url, "GET", null, basicAuth, contentType);
	}
	
	public static String postTo(String url, String postData, String contentType) throws IOException {
		return request(url, "POST", postData, null, contentType);
	}

	public static String postTo(String url, String basicAuth, String postData, String contentType) throws IOException {
		return request(url, "POST", postData, basicAuth, contentType);
	}
	
	@SafeVarargs
	public static String postTo(String url, Pair<String, String>... data) throws IOException {
		return postTo(url, Strings.urlEncode(data), "application/x-www-form-urlencoded");
	}

	@SafeVarargs
	public static String postTo(String url, String basicAuth, Pair<String, String>... data) throws IOException {
		return postTo(url, basicAuth, Strings.urlEncode(data), "application/x-www-form-urlencoded");
	}

	public static String deleteTo(String url, String basicAuth, String data, String contentType) throws IOException {
		return request(url, "DELETE", data, basicAuth, contentType);
	}
	
	public static String putTo(String url, String basicAuth, String jsonData) throws IOException {
		return request(url, "PUT", jsonData, basicAuth, "application/json");
	}
	
	public static String httpRequest(String url, String requestMethod, String postData, String basicAuth, String contentType) throws IOException {
		URL authUrl = new URL(url);
		HttpURLConnection con = (HttpURLConnection) authUrl.openConnection();

		con.setInstanceFollowRedirects(false);

		con.setRequestMethod(requestMethod);

		con.setRequestProperty("Content-Type", contentType);
		con.setRequestProperty("Accept", "UTF-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		
		if (basicAuth != null && !basicAuth.isEmpty()) {
			con.setRequestProperty("Authorization", basicAuth);
		}

		if (postData != null && !postData.isEmpty()) {
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postData.toString());
			wr.flush();
			wr.close();
		}

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} catch (IOException ioe) {
			InputStream errorStream = con.getErrorStream();
			if (errorStream != null) {

				String errorContent;
				try {
					errorContent = Streams.readText(errorStream, Charset.forName("UTF-8"));
				} catch (Exception e) {
					throw ioe;
				}
				throw new RuntimeException(errorContent, ioe);
			} else
				throw ioe;
		}
	}
	
	public static String httpsRequest(String url, String requestMethod, String postData, String basicAuth, String contentType) throws IOException {
		URL authUrl = new URL(url);
		HttpURLConnection con = (HttpURLConnection) authUrl.openConnection();

		con.setInstanceFollowRedirects(false);
		con.setRequestMethod(requestMethod);
		
		con.setRequestProperty("Content-Type", contentType);
		con.setRequestProperty("Accept", "charset=utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		
		if (basicAuth != null && !basicAuth.isEmpty()) {
			con.setRequestProperty("Authorization", basicAuth);
		}

		if (postData != null && !postData.isEmpty()) {
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), Charset.forName("UTF-8"));
			osw.write(postData);
			osw.flush();
			osw.close();
		}

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} catch (IOException ioe) {
			InputStream errorStream = con.getErrorStream();
			if (errorStream != null) {

				String errorContent;
				try {
					errorContent = Streams.readText(errorStream, Charset.forName("UTF-8"));
				} catch (Exception e) {
					throw ioe;
				}
				throw new RuntimeException(errorContent, ioe);
			} else
				throw ioe;
		}
	}
	
	
	// _____________________________________________________________________________________________
	private static String request(String url, String requestMethod, String postData, String basicAuth, String contentType) throws IOException {
		if (url.toLowerCase().startsWith("https"))
			return httpsRequest(url, requestMethod, postData, basicAuth, contentType);
		else
			return httpRequest(url, requestMethod, postData, basicAuth, contentType);
	}
}
