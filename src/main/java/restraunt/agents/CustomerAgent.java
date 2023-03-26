package restraunt.agents;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import restraunt.messages.*;
import restraunt.resources.basic.Customer;
import restraunt.Main;

@AllArgsConstructor
@Slf4j
public class CustomerAgent extends Agent {
    Customer customer;

    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof EndMessage m) {
            customer.setEnded(Main.restaurant.time.getCurrentDate());
            System.out.println(getName() + " received his order at " + customer.getEnded());
            stop(this);
        }
    }

    @Override
    public void run() {
        var t = customer.getStarted().getTime();
        while (true) {
            if (Main.restaurant.time.isTimeToAct(t)) {
                log.info("[{}] Customer {} made an order", Time.now, customer.getCustomerName());
                Main.restaurant.getManager().registerMessage(new OrderMessage(this, customer.getOrder()));
                break;
            }
        }
        super.run();
    }
}
