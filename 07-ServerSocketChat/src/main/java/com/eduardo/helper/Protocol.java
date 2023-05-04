package com.eduardo.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Protocol {

    public static String regexFormat = "&FORMAT\\[(LOGIN|REGISTER|DATA){1}\\]&";

    public static String getFormat(String message) {
        CustomString cs = new CustomString();
        Pattern pattern = Pattern.compile(regexFormat);
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            return cs.readContent(matcher.group(), "[", "]");
        }
        return "";
    }

    public static Map<String, String> formatLogin(String message) throws ErrorFormatException {
        Map<String, String> map = new HashMap<>();
        CustomString cs = new CustomString();
        String lineId = cs.readUntil(message, "&");
        String lineName = cs.readUntil(message, "&");
        String linePassword = cs.readUntil(message, "&");
        if (!lineId.contains("SESSIONID") || !lineName.contains("NAME") || !linePassword.contains("PASSWORD")) {
            throw new ErrorFormatException("format Login Error");
        }
        map.put("SESSIONID", cs.readContent(lineId, "[", "]"));
        map.put("NAME", cs.readContent(lineName, "[", "]"));
        map.put("PASSWORD", cs.readContent(linePassword, "[", "]"));
        return map;
    }

    public static Map<String, String> formatRegister(String message) throws ErrorFormatException {
        Map<String, String> map = new HashMap<>();
        CustomString cs = new CustomString();
        String lineId = cs.readUntil(message, "&");
        String lineName = cs.readUntil(message, "&");
        String linePassword = cs.readUntil(message, "&");
        if (!lineId.contains("SESSIONID") || !lineName.contains("NAME") || !linePassword.contains("PASSWORD")) {
            throw new ErrorFormatException("format Register Error");
        }
        map.put("SESSIONID", cs.readContent(lineId, "[", "]"));
        map.put("NAME", cs.readContent(lineName, "[", "]"));
        map.put("PASSWORD", cs.readContent(linePassword, "[", "]"));
        return map;
    }

    public static Map<String, String> formatData(String message) throws ErrorFormatException {
        CustomString cs = new CustomString();
        Map<String, String> map = new HashMap();
        String lineId = cs.readUntil(message, "&");
        String lineData = cs.readUntil(message, "&");
        if (!lineId.contains("SESSIONID") || !lineData.contains("DATA")) {
            throw new ErrorFormatException("format Data Error");
        }
        map.put("SESSIONID", cs.readContent(lineId, "[", "]"));
        map.put("DATA", cs.readContent(lineData, "[", "]").trim());
        return map;
    }

    public static String setFormatLogin(String sessionId, String name, String password) {
        return String.format("SESSIONID[%s]&NAME[%s]&PASSWORD[%s]&FORMAT[LOGIN]&", sessionId, name, password);
    }
    
     public static String setFormatRegister(String sessionId, String name, String password) {
        return String.format("SESSIONID[%s]&NAME[%s]&PASSWORD[%s]&FORMAT[REGISTER]&", sessionId, name, password);
    }

    public static String setFormatData(String sessionId, String data) {
        return String.format("SESSIONID[%s]&DATA[%s]&FORMAT[DATA]&", sessionId, data);
    }
}