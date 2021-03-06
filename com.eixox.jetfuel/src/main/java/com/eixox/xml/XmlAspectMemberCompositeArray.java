package com.eixox.xml;

import java.lang.reflect.Array;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.eixox.reflection.AspectMember;

public class XmlAspectMemberCompositeArray extends XmlAspectMember {

	private final XmlAspect childAspect;
	private final Class<?> childClass;

	public XmlAspectMemberCompositeArray(AspectMember member, String xmlName, Class<?> childClass, XmlAspect childAspect) {
		super(member, xmlName);

		this.childClass = childClass;
		this.childAspect = childAspect;
	}

	@Override
	protected Object parse(Element element) {
		NodeList nodes = element.getElementsByTagName(getXmlName());
		if (nodes == null)
			return null;
		int count = nodes.getLength();
		if (count < 1)
			return null;

		Object arr = Array.newInstance(childClass, count);

		for (int i = 0; i < count; i++) {
			Object item;
			try {
				item = childClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			this.childAspect.read(item, (Element) nodes.item(i));
			Array.set(arr, i, item);
		}
		return arr;

	}

}
