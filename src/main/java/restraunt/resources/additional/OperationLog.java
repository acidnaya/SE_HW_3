package restraunt.resources.additional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import restraunt.Main;

import java.text.ParseException;
import java.util.Date;
@Getter
public class OperationLog {
    private static int counter = 0;
    private int ID;
    private int processID;
    private int card;
    private Date started;
    private Date ended;
    private int equipmentID;
    private int cookID;

    public OperationLog(int p, int c, int e, int cook) {
        ID = ++counter;
        processID = p;
        try {
            started = Main.restaurant.time.getCurrentDate();
        } catch (ParseException ex) {
            started = new Date();
        }
        ended = started;
        equipmentID = e;
        cookID = cook;
    }
}
