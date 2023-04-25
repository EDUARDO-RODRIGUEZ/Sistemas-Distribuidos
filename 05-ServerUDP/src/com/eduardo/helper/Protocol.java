package com.eduardo.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Protocol {

    public static String regexFormat = "&FORMAT\\[((init)|(message|close)){1}\\]&";

    public static String getFormat(String message) {
        CustomString cs = new CustomString();
        Pattern pattern = Pattern.compile(regexFormat, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            return cs.readContent(matcher.group(), "[", "]");
        }
        return "";
    }

    public static Map<String, String> formatInit(String message) {
        Map<String, String> data = new HashMap<>();
        CustomString cs = new CustomString();
        String lineName = cs.readUntil(message, "&");
        if (!lineName.contains("NAME")) {
            return data;
        }
        data.put("NAME", cs.readContent(lineName, "[", "]"));
        return data;
    }

    public static Map<String, String> formatClose(String message) {
        Map<String, String> data = new HashMap<>();
        CustomString cs = new CustomString();
        String lineId = cs.readUntil(message, "&");
        if (!lineId.contains("ID")) {
            return data;
        }
        data.put("ID", cs.readContent(lineId, "[", "]"));
        return data;
    }

    public static Map<String, String> formatMessage(String message) {
        CustomString cs = new CustomString();
        Map<String, String> map = new HashMap();
        String lineId = cs.readUntil(message, "&");
        String lineData = cs.readUntil(message, "&");
        if (!lineId.contains("ID") || !lineData.contains("DATA")) {
            return map;
        }
        map.put("ID", cs.readContent(lineId, "[", "]"));
        map.put("DATA", cs.readContent(lineData, "[", "]").trim());
        return map;
    }

    public static String setFormatInit(String name) {
        return String.format("NAME[%s]&FORMAT[init]&", name);
    }

    public static String setFormatMessage(String id, String message) {
        return String.format("ID[%s]&DATA[%s]&FORMAT[message]&", id, message);
    }

    public static String setFormatClose(String id) {
        return String.format("ID[%s]&FORMAT[close]&", id);
    }
}
