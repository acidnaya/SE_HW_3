package restaurant.agents;

import restaurant.Main;
import restaurant.messages.EndMessage;
import restaurant.messages.Message;
import restaurant.messages.ReserveMessage;
import restaurant.resources.basic.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAgent extends Agent<Message> {
    CustomerAgent customer;
    List<ProcessAgent> processes = new ArrayList<>();
    volatile private int counter;

    public OrderAgent(CustomerAgent c, List<Order> order) {
        customer = c;
        counter = order.size();
        for (var orderPart: order) {
            Main.restaurant.getWarehouse().registerMessage(new ReserveMessage());
            var p = new ProcessAgent(orderPart.getDish(), this);
            processes.add(p);
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
