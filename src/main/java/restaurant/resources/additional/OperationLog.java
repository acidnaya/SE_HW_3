package restaurant.resources.additional;

import lombok.Getter;
import restaurant.Main;

import java.text.ParseException;
import java.util.Date;
@Getter
public class OperationLog {
    private static int counter = 0;
    private int ID;
    private int processID;
    private Date started;
    private Date ended;
    private int equipmentID;
    private int cookID;

    public OperationLog(int p, int e, int cook) {
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
