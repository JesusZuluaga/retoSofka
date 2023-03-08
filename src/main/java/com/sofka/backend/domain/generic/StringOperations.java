package com.sofka.backend.domain.generic;

public interface StringOperations {

    String EMPTY = "";
    int INDEX_NOT_FOUND = -1;

    default boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    default boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    default boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    default String substringAfter(final String str, final String separator) {
        if (isEmpty(str)) {
            return str;
        }

        if (separator == null) {
            return EMPTY;
        }

        final int pos = str.indexOf(separator);

        if (pos == INDEX_NOT_FOUND) {
            return EMPTY;
        }

        return str.substring(pos + separator.length());
    }
}
