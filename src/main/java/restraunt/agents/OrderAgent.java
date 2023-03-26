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
    int counter;

    public OrderAgent(CustomerAgent c, List<Order> order) {
        //super(); // ??
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
//                var customer = findCustomer();
                customer.registerMessage(new EndMessage());
                stop(this);
            }
        }
    }

//    public CustomerAgent findCustomer() {
//        var customers = AgentRepository.findByType(CustomerAgent.class);
//        for (var customer : customers) {
//            if (Objects.equals(customer.getName(), customerName)) {
//                return customer;
//            }
//        }
//        return null;
//    }
}
