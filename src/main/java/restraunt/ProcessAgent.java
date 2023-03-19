package restraunt;

import restraunt.resources.*;

import java.util.Date;
import java.util.List;

public class ProcessAgent {
    int ID;
    int dish;
    Date started;
    Date ended;
    boolean active;
    List<KitchenOperation> operations;
}
