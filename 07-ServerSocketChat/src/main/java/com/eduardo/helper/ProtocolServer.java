package com.eduardo.helper;

import com.eduardo.server.game.Tablero;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProtocolServer {

    public static String regexFormat = "FORMAT\\[(LOGIN|REGISTER|TABLERO_SET|DATA|SIZE_MATRIX|COUNT_ONLINE|CONFIRM_PLAY|INIT_PLAY|GET_SHIPS_TABLERO|SHOOT_SHIPS){1}\\]&";

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

    public static Map<String, String> formatTableroSet(String message) throws ErrorFormatException {
        Map<String, String> map = new HashMap<>();
        CustomString cs = new CustomString();
        String lineId = cs.readUntil(message, "&");
        String lineRow = cs.readUntil(message, "&");
        String lineCol = cs.readUntil(message, "&");
        String lineValue = cs.readUntil(message, "&");
        if (!lineId.contains("SESSIONID") || !lineRow.contains("ROW") || !lineCol.contains("COL") || !lineValue.contains("VALUE")) {
            throw new ErrorFormatException("format Tablero Set");
        }
        map.put("SESSIONID", cs.readContent(lineId, "[", "]"));
        map.put("ROW", cs.readContent(lineRow, "[", "]"));
        map.put("COL", cs.readContent(lineCol, "[", "]"));
        map.put("VALUE", cs.readContent(lineValue, "[", "]"));
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

    public static Map<String, String> formatSizeMatrix(String message) throws ErrorFormatException {
        CustomString cs = new CustomString();
        Map<String, String> map = new HashMap();
        String lineId = cs.readUntil(message, "&");
        if (!lineId.contains("SESSIONID")) {
            throw new ErrorFormatException("format Size Matrix Error");
        }
        map.put("SESSIONID", cs.readContent(lineId, "[", "]"));
        return map;
    }

    public static Map<String, String> formatCountOnline(String message) throws ErrorFormatException {
        CustomString cs = new CustomString();
        Map<String, String> map = new HashMap();
        String lineId = cs.readUntil(message, "&");
        if (!lineId.contains("SESSIONID")) {
            throw new ErrorFormatException("format Size Matrix Error");
        }
        map.put("SESSIONID", cs.readContent(lineId, "[", "]"));
        return map;
    }

    public static Map<String, String> formatConfirmPlay(String message) throws ErrorFormatException {
        CustomString cs = new CustomString();
        Map<String, String> map = new HashMap();
        String lineId = cs.readUntil(message, "&");
        if (!lineId.contains("SESSIONID")) {
            throw new ErrorFormatException("format Size Matrix Error");
        }
        map.put("SESSIONID", cs.readContent(lineId, "[", "]"));
        return map;
    }

    public static Map<String, String> formatInitPlay(String message) throws ErrorFormatException {
        CustomString cs = new CustomString();
        Map<String, String> map = new HashMap();
        String lineId = cs.readUntil(message, "&");
        if (!lineId.contains("SESSIONID")) {
            throw new ErrorFormatException("format Size Matrix Error");
        }
        map.put("SESSIONID", cs.readContent(lineId, "[", "]"));
        return map;
    }

    public static Map<String, String> formatGetTablero(String message) throws ErrorFormatException {
        CustomString cs = new CustomString();
        Map<String, String> map = new HashMap();
        String lineId = cs.readUntil(message, "&");
        if (!lineId.contains("SESSIONID")) {
            throw new ErrorFormatException("format Size Matrix Error");
        }
        map.put("SESSIONID", cs.readContent(lineId, "[", "]"));
        return map;
    }

    public static Map<String, String> formatShootShip(String message) throws ErrorFormatException {
        CustomString cs = new CustomString();
        Map<String, String> map = new HashMap();
        String lineId = cs.readUntil(message, "&");
        String lineRow = cs.readUntil(message, "&");
        String lineCol = cs.readUntil(message, "&");
        if (!lineId.contains("SESSIONID") || !lineRow.contains("ROW") || !lineCol.contains("COL")) {
            throw new ErrorFormatException("format shooy ship Error");
        }
        map.put("SESSIONID", cs.readContent(lineId, "[", "]"));
        map.put("ROW", cs.readContent(lineRow, "[", "]"));
        map.put("COL", cs.readContent(lineCol, "[", "]"));
        return map;
    }

    public static String setFormatNewUser() {
        return String.format("FORMAT[NEW_USER]&");
    }

    public static String setFormatCountOnline(int count) {
        return String.format("COUNT[%s]&FORMAT[COUNT_ONLINE]&", count);
    }

    public static String setFormatMatrix(int nfila, int ncolumna) {
        return String.format("MATRIXFILA[%s]&MATRIXCOLUMNA[%s]&FORMAT[SIZE_MATRIX]&", nfila, ncolumna);
    }

    public static String setFormatResponse(boolean ok, Response response, String message) {
        return String.format("OK[%s]&MESSAGE[%s]&RESPONSE[%s]&FORMAT[RESPONSE]&", ok, message, response.name());
    }

    public static String setFormatId(String sessionId) {
        return String.format("SESSIONID[%s]&FORMAT[ID]&", sessionId);
    }

    public static String setFormatAuthenticated() {
        return String.format("FORMAT[AUTHENTICATED]&");
    }

    public static String setFormatConfirmPlay() {
        return String.format("FORMAT[CONFIRM_PLAY]&");
    }

    public static String setFormatInitPlay() {
        return String.format("FORMAT[INIT_PLAY]&");
    }

    public static String setFormatGetTablero(Tablero tablero) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int f = 0; f < Tablero.NF; f++) {
            for (int c = 0; c < Tablero.NC; c++) {
                if (Objects.nonNull(tablero.getData()[f][c])) {
                    stringBuilder.append(String.format("ROW[%s]$COL[%s]$VALUE[%s]$&", f, c, 1));
                }
            }
        }
        stringBuilder.append("FORMAT[GET_SHIPS_TABLERO]&");
        return stringBuilder.toString();
    }

    public static String setFormatDestroyShip(int row, int col) {
        return String.format("ROW[%s]&COL[%s]&FORMAT[DESTROY_SHIP]&", row, col);
    }

    public static String setFormatGameOver() {
        return String.format("FORMAT[GAME_OVER]&");
    }

    public static String setFormatWinner() {
        return String.format("FORMAT[WINNER]&");
    }

    public static String setFormatShootShip(boolean value) {
        return String.format("VALUE[%b]&FORMAT[SHOOT_SHIP]&", value);
    }

    public static String setFormatTurnShoot() {
        return String.format("FORMAT[TURN_SHOOT]&");
    }
}
