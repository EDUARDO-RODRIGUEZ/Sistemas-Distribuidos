package com.eduardo.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Protocol {

    public static String regexFormat = "FORMAT\\[(DATA|ID|AUTHENTICATED|RESPONSE|SIZE_MATRIX|COUNT_ONLINE|NEW_USER|CONFIRM_PLAY|INIT_PLAY){1}\\]&";

    public static String getFormat(String message) {
        CustomString cs = new CustomString();
        Pattern pattern = Pattern.compile(regexFormat);
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            return cs.readContent(matcher.group(), "[", "]");
        }
        return "";
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

    public static Map<String, String> formatSizeMatrix(String message) throws ErrorFormatException {
        CustomString cs = new CustomString();
        Map<String, String> map = new HashMap();
        String lineFila = cs.readUntil(message, "&");
        String lineColumna = cs.readUntil(message, "&");
        if (!lineFila.contains("MATRIXFILA") || !lineColumna.contains("MATRIXCOLUMNA")) {
            throw new ErrorFormatException("format size matrix Error");
        }
        map.put("MATRIXFILA", cs.readContent(lineFila, "[", "]"));
        map.put("MATRIXCOLUMNA", cs.readContent(lineColumna, "[", "]"));
        return map;
    }

    public static Map<String, String> formatID(String message) throws ErrorFormatException {
        CustomString cs = new CustomString();
        Map<String, String> map = new HashMap();
        String lineId = cs.readUntil(message, "&");
        if (!lineId.contains("SESSIONID")) {
            throw new ErrorFormatException("format Data Error");
        }
        map.put("SESSIONID", cs.readContent(lineId, "[", "]"));
        return map;
    }

    public static Map<String, String> formatCountOnline(String message) throws ErrorFormatException {
        CustomString cs = new CustomString();
        Map<String, String> map = new HashMap();
        String lineCount = cs.readUntil(message, "&");
        if (!lineCount.contains("COUNT")) {
            throw new ErrorFormatException("format Data Error");
        }
        map.put("COUNT", cs.readContent(lineCount, "[", "]"));
        return map;
    }

    public static Map<String, String> formatResponse(String message) throws ErrorFormatException {
        Map<String, String> map = new HashMap<>();
        CustomString cs = new CustomString();
        String lineOK = cs.readUntil(message, "&");
        String lineMessage = cs.readUntil(message, "&");
        String lineResponse = cs.readUntil(message, "&");
        if (!lineOK.contains("OK") || !lineMessage.contains("MESSAGE") || !lineResponse.contains("RESPONSE")) {
            throw new ErrorFormatException("format Response Error");
        }
        map.put("OK", cs.readContent(lineOK, "[", "]"));
        map.put("MESSAGE", cs.readContent(lineMessage, "[", "]"));
        map.put("RESPONSE", cs.readContent(lineResponse, "[", "]"));
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

    public static String setFormatId(String sessionId) {
        return String.format("SESSIONID[%s]&FORMAT[ID]&", sessionId);
    }

    public static String setFormatTableroSet(String sessionId, int row, int col, TableroValue tableroValue) {
        return String.format("SESSIONID[%s]&ROW[%s]&COL[%s]&VALUE[%s]&FORMAT[TABLERO_SET]&",
                sessionId, String.valueOf(row), String.valueOf(col), String.valueOf(tableroValue.getValue())
        );
    }

    public static String setFormatSizeMatrix(String sessionId) {
        return String.format("SESSIONID[%s]&FORMAT[SIZE_MATRIX]&", sessionId);
    }

    public static String setFormatCountOnline(String sessionId) {
        return String.format("SESSIONID[%s]&FORMAT[COUNT_ONLINE]&", sessionId);
    }

    public static String setFormatConfirmPlay(String sessionId) {
        return String.format("SESSIONID[%s]&FORMAT[CONFIRM_PLAY]&", sessionId);
    }

    public static String setFormatInitPlay(String sessionId) {
        return String.format("SESSIONID[%s]&FORMAT[INIT_PLAY]&", sessionId);
    }

}
