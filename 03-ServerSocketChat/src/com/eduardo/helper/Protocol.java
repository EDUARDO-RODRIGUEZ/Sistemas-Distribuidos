package com.eduardo.helper;

import java.util.HashMap;
import java.util.Map;

public class Protocol {

    public static Map<String, String> formatConexion(String message) {
        Map<String, String> data = new HashMap<>();
        CustomString cs = new CustomString();
        String lineId = cs.readUntil(message, "&");
        String lineName = cs.readUntil(message, "&");
        if (!lineId.contains("ID") || !lineName.contains("NAME")) {
            return data;
        }
        data.put("ID", cs.readContent(lineId, "[", "]"));
        data.put("NAME", cs.readContent(lineName, "[", "]"));
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
}
