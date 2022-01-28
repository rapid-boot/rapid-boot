package com.rapidboot.generator.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author guangfeng
 */
public class StringUtils {

    private static final char SEPARATOR = '_';
    private static final String TABLE_PREFIX = "t_";


    public static boolean isNotBlank(String input) {
        return Objects.nonNull(input) && input.trim().length() > 0;
    }

    public static boolean isBlank(String input) {
        return !isNotBlank(input);
    }

    public static Map<String, String> getColumnsRef(int index) {
        Map<String, String> ret = new HashMap<>(1);
        ret.put("$ref", "$.columns[" + index + "]");
        return ret;
    }

    /**
     * 推测字段的 query 类型。有：
     * <p>
     * "!=",
     * "<=",
     * "=",
     * "BetWeen",
     * "Like",
     * "NotNull",
     *
     * @param columnType
     * @return
     */
    public static String getQueryTypeFromColumnType(String columnType) {
        if (isBlank(columnType)) {
            return "NotNull";
        } else if ("String".equalsIgnoreCase(columnType)) {
            return "like";
        } else if ("LocalDateTime".equalsIgnoreCase(columnType) || "Timestamp".equalsIgnoreCase(columnType) || "LocalDate".equalsIgnoreCase(columnType)) {
            return "Between";
        } else {
            return "=";
        }
    }

    public static String getJavaTypeFromColumnType(String columnType) {
        if (Objects.isNull(columnType)) {
            return "String";
        } else if ("tinyint".equalsIgnoreCase(columnType)) {
            return "Integer";
        } else if ("smallint".equalsIgnoreCase(columnType)) {
            return "Integer";
        } else if ("mediumint".equalsIgnoreCase(columnType)) {
            return "Integer";
        } else if ("int".equalsIgnoreCase(columnType)) {
            return "Integer";
        } else if ("integer".equalsIgnoreCase(columnType)) {
            return "Integer";
        } else if ("bigint".equalsIgnoreCase(columnType)) {
            return "Long";
        } else if ("float".equalsIgnoreCase(columnType)) {
            return "Float";
        } else if ("double".equalsIgnoreCase(columnType)) {
            return "Double";
        } else if ("decimal".equalsIgnoreCase(columnType)) {
            return "BigDecimal";
        } else if ("bit".equalsIgnoreCase(columnType)) {
            return "Boolean";
        } else if ("char".equalsIgnoreCase(columnType)) {
            return "String";
        } else if ("varchar".equalsIgnoreCase(columnType)) {
            return "String";
        } else if ("tinytext".equalsIgnoreCase(columnType)) {
            return "String";
        } else if ("text".equalsIgnoreCase(columnType)) {
            return "String";
        } else if ("mediumtext".equalsIgnoreCase(columnType)) {
            return "String";
        } else if ("longtext".equalsIgnoreCase(columnType)) {
            return "String";
        } else if ("date".equalsIgnoreCase(columnType)) {
            return "LocalDate";
        } else if ("datetime".equalsIgnoreCase(columnType)) {
            return "LocalDateTime";
        } else if ("timestamp".equalsIgnoreCase(columnType)) {
            return "Timestamp";
        } else {
            return columnType;
        }
    }


    /**
     * 通过表名获取类名
     *
     * @param tableName
     * @return
     */
    public static String getClassNameFromTableName(String tableName) {
        if (Objects.isNull(tableName)) {
            return null;
        }
        if (tableName.startsWith(TABLE_PREFIX) || tableName.startsWith(TABLE_PREFIX.toUpperCase())) {
            tableName = tableName.substring(TABLE_PREFIX.length());
        }
        return toCapitalizeCamelCase(tableName);
    }

    /**
     * 通过表名获取类名
     *
     * @param tableName
     * @return
     */
    public static String getInstanceNameFromTableName(String tableName) {
        String className = getClassNameFromTableName(tableName);
        if (Objects.isNull(className)) {
            return null;
        }
        return className.substring(0, 1).toLowerCase() + className.substring(1);
    }


    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

//        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }


    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

}
