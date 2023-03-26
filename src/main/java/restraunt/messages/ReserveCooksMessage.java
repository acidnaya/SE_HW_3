package restraunt.messages;

import jdk.dynalink.Operation;
import restraunt.resources.additional.KitchenOperation;

import java.util.List;

public class ReserveCooksMessage extends Message{
    public List<KitchenOperation> ops;
    public ReserveCooksMessage(List<KitchenOperation> operations) {
        ops = operations;
    }
}
