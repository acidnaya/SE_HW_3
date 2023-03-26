package restraunt.agents;

import restraunt.Main;
import restraunt.messages.EndMessage;
import restraunt.messages.Message;
import restraunt.messages.ReserveMessage;
import restraunt.messages.TimeMessage;
import restraunt.resources.basic.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAgent extends Agent {
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
            p.start(p);
        }
    }
    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof TimeMessage) {
            System.out.println(getName() + " get time message");
        } else if (message instanceof EndMessage) {
            counter--;
            System.out.println(getName() + " get end message");
            if (counter == 0) {
                customer.registerMessage(new EndMessage());
                stop(this);
            }
        }
    }
}
