package restraunt.agent;

import lombok.AllArgsConstructor;
import restraunt.Order;

import java.util.List;

@AllArgsConstructor
public class OrderMessage extends Message {
    public List<Order> order;
}
