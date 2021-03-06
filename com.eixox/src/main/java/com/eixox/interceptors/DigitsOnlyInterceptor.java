package com.eixox.interceptors;

import com.eixox.Convert;
import com.eixox.Strings;

public class DigitsOnlyInterceptor implements Interceptor {

    public synchronized final Object intercept(Object input) {
        if (input == null || !(input instanceof String))
            return input;
        else
            return Strings.digitsOnly((String) input);
    }

    private DigitsOnlyInterceptor() {
    }

    public static final DigitsOnlyInterceptor Instance = new DigitsOnlyInterceptor();


    public static long toLong(Object in) {
        return Convert.toLong(
                new DigitsOnlyInterceptor()
                        .intercept(in));
    }
}