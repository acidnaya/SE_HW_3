package restraunt;

import restraunt.resources.basic.KitchenOperationType;

import java.util.Date;
import java.util.List;

public class ProcessAgent {
    int ID;
    int dish;
    Date started;
    Date ended;
    boolean active;
    List<KitchenOperationType> operations;
}
