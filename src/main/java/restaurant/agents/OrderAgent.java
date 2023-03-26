package restaurant.agents;

import restaurant.Main;
import restaurant.messages.EndMessage;
import restaurant.messages.Message;
import restaurant.messages.ReserveMessage;
import restaurant.resources.basic.Order;
import java.util.List;

public class OrderAgent extends Agent<Message> {
    private final CustomerAgent customer;
    volatile private int counter;

    public OrderAgent(CustomerAgent c, List<Order> order) {
        customer = c;
        counter = order.size();
        for (var orderPart: order) {
            Main.restaurant.getWarehouse().registerMessage(new ReserveMessage());
            var p = new ProcessAgent(orderPart.getDish(), this);
            start(p);
        }
    }

    private synchronized void decreaseCounter() {
        --counter;
    }

    @Override
    protected void proceed(Message message) {
        if (message instanceof EndMessage) {
            decreaseCounter();
            if (counter == 0) {
                customer.registerMessage(new EndMessage());
                stop(this);
            }
        }
    }
}
