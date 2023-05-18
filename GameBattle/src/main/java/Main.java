
import com.eduardo.helper.CustomString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String prueba = "ROW[1]$COL[2]$&ROW[3]$COL[4]$&FORMAT[PRUEBA]&";
        CustomString cs1 = new CustomString();
        CustomString cs2 = new CustomString();
        List<Map<String, String>> ships = new ArrayList<>();
        String line = "";
        while (true) {
            line = cs1.readUntil(prueba, "&");
            if (line.contains("FORMAT")) {
                break;
            }
            Map<String, String> data = new HashMap<>();
            String Row = cs2.readUntil(line, "$");
            String Col = cs2.readUntil(line, "$");
            data.put("row", cs2.readContent(Row, "[", "]"));
            data.put("col", cs2.readContent(Col, "[", "]"));
            ships.add(data);
            cs2.resetPos();
        }
        for (Map<String, String> ship : ships) {
            System.out.print(ship.get("row"));
            System.out.println(ship.get("col"));
        }
    }
}
