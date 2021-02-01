package com.java.sdk.util;


import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;


/**
 * @author kesc
 * @since 2017/12/25
 */
public final class StringUtil extends StringUtils {
    public static final String COMMA = ",";
    public static final String UNDER_LINE = "_";
    public static final String DOT = ".";
    public static final String MINUS = "-";
    public static final String VIRGULE = "/";
    public static final String COLON = ":";
    public static final String PLUS = "+";
    public static final String EQUIVALENT = "=";
    public static final String GT = ">";
    public static final String LT = "<";
    public static final String VERTICAL_LINE = "|";
    public static final String STAR = "*";
    public static final String PERCENT = "%";
    public static final String AND = "&";
    public static final String DOLOR = "$";
    public static final String L_QUOT = "(";
    public static final String R_QUOT = ")";
    public static final String SEMICOLON = ";";
    public static final String AT = "@";
    public static final String EXCLAMATION = "!";

    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static String latterUuid() {
        return UUID.randomUUID().toString().replace(MINUS, EMPTY);
    }

    public static String camel2Underline(String str) {
        if (isBlank(str)) {
            return str;
        }
        str = str.trim();
        StringBuilder result = new StringBuilder(str.length());
        result.append(Character.toLowerCase(str.charAt(0)));
        for (int i = 1; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                result.append('_').append(Character.toLowerCase(ch));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String underline2Camel(String str) {
        if (isBlank(str)) {
            return str;
        }
        str = str.trim();
        StringBuilder result = new StringBuilder(str.length());
        boolean flag = false;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if ('_' == ch) {
                flag = true;
            } else {
                if (flag) {
                    result.append(Character.toUpperCase(ch));
                    flag = false;
                } else {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }

    public static final String EMPTY = "";

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isContainEmpty(String... args) {
        if (args == null) {
            return false;
        } else {
            String[] var1 = args;
            int var2 = args.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                String arg = var1[var3];
                if (arg == null || "".equals(arg)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    public static String trimToEmpty(String str) {
        return str == null ? "" : str.trim();
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    public static boolean startsWith(String str, String prefix) {
        return startsWith(str, prefix, false);
    }

    private static boolean startsWith(String str, String prefix, boolean ignoreCase) {
        if (str != null && prefix != null) {
            return prefix.length() > str.length() ? false : str.regionMatches(ignoreCase, 0, prefix, 0, prefix.length());
        } else {
            return str == null && prefix == null;
        }
    }

    public static boolean startsWithIgnoreCase(String str, String prefix) {
        return startsWith(str, prefix, true);
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        } else {
            int sz = str.length();

            for(int i = 0; i < sz; ++i) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static <T> String join(Collection<T> collection, String separator) {
        return join(collection, separator, new StringUtil.StringFormatter<T>() {
            @Override
            public String format(T obj) {
                return obj.toString();
            }
        });
    }

    public static <T> String join(Collection<T> collection, String separator, StringUtil.StringFormatter<T> formatter) {
        Iterator<T> iterator = collection.iterator();
        if (iterator == null) {
            return null;
        } else if (!iterator.hasNext()) {
            return "";
        } else {
            T first = iterator.next();
            if (!iterator.hasNext()) {
                return first == null ? "" : formatter.format(first);
            } else {
                StringBuilder buf = new StringBuilder(256);
                if (first != null) {
                    buf.append(formatter.format(first));
                }

                while(iterator.hasNext()) {
                    buf.append(separator);
                    T obj = iterator.next();
                    if (obj != null) {
                        buf.append(formatter.format(obj));
                    }
                }

                return buf.toString();
            }
        }
    }

    public interface StringFormatter<T> {
        String format(T var1);
    }


}
