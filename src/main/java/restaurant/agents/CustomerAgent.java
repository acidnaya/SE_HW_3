package restaurant.agents;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import restaurant.messages.*;
import restaurant.resources.basic.Customer;
import restaurant.Main;

@AllArgsConstructor
@Slf4j
public class CustomerAgent extends Agent {
    Customer customer;

    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof EndMessage) {
            customer.setEnded(Main.restaurant.time.getCurrentDate());
            log.info(getName() + " received his order at " + customer.getEnded());
           // stop(this);
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
