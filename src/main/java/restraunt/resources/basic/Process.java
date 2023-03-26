package restraunt.resources.basic;

import restraunt.resources.basic.KitchenOperationType;

import java.util.Date;
import java.util.List;

public class Process {
    int ID;
    int dish;
    Date started;
    Date ended;
    boolean active;
    List<KitchenOperationType> operations;
}
