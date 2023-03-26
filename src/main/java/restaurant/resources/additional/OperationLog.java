package restraunt.resources.additional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import restraunt.Config;
import restraunt.Main;

import java.text.ParseException;
import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@Jacksonized
public class OperationLog {
    private static int counter = 0;
    @JsonProperty("oper_id")
    private int ID;
    @JsonProperty("oper_proc")
    private int processID;
    @JsonProperty("oper_card")
    private int card;
    @JsonProperty("oper_started")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = Config.timezone)
    private Date started;
    @JsonProperty("oper_ended")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = Config.timezone)
    private Date ended;
    @JsonProperty("oper_equip_id")
    private int equipmentID;
    @JsonProperty("oper_coocker_id")
    private int cookID;

    public OperationLog(int p, int c, int e, int cook) {
        ID = ++counter;
        processID = p;
        card = c;
        try {
            started = Main.restaurant.time.getCurrentDate();
        } catch (ParseException ex) {
            started = new Date();
        }
        ended = started;
        equipmentID = e;
        cookID = cook;
    }

    public void fixEnded() {
        try {
            ended = Main.restaurant.time.getCurrentDate();
        } catch (ParseException ex) {
            ended = started;
        }
    }

    public OperationLog(int id, int p, int c, Date s, Date e, int eq, int cook) {
        ID = id;
        processID = p;
        card = c;
        started = s;
        ended = e;
        equipmentID = eq;
        cookID = cook;
    }

    public OperationLog createCopy() {
        return new OperationLog(ID, processID, card, started, ended, equipmentID, cookID);
    }
}
