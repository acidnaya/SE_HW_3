package restraunt.agents;

import restraunt.Main;
import restraunt.messages.*;
import restraunt.resources.basic.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        if (message instanceof TimeMessage m) {
            System.out.println(getName() + " get time message");
        } else if (message instanceof EndMessage m) {
            counter--;
            System.out.println(getName() + " get end message");
            if (counter == 0) {
                customer.registerMessage(new EndMessage());
                stop(this);
            }
        }
    }
}
