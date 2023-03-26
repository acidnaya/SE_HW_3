package restraunt.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import restraunt.agents.CustomerAgent;
import restraunt.resources.basic.Order;

import java.util.List;

@AllArgsConstructor
@Getter
public class OrderMessage extends Message {
    private CustomerAgent customer;
    private List<Order> order;
}
