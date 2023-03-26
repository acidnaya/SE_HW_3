package restaurant.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import restaurant.agents.CustomerAgent;
import restaurant.resources.basic.Order;

import java.util.List;

/**
 * Сообщение с заказом
 */
@AllArgsConstructor
@Getter
public class OrderMessage extends Message {
    private CustomerAgent customer;
    private List<Order> order;
}
