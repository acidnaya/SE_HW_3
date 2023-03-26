package restraunt.agents;

import lombok.AllArgsConstructor;
import restraunt.messages.*;
import restraunt.resources.basic.Customer;
import restraunt.Main;

@AllArgsConstructor
public class CustomerAgent extends Agent {
    Customer cust;

    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof EndMessage m) {
            cust.setEnded(Main.restaurant.time.getCurrentDate());
            System.out.println(getName() + " received his order at " + cust.getEnded());
            stop(this);
        }
    }

    @Override
    public void run() {
        var t = cust.getStarted().getTime();
        while (true) {
            if (Main.restaurant.time.isTimeToAct(t)) {
                System.out.println("---Customer register order---");
                Main.restaurant.getManager().registerMessage(new OrderMessage(this, cust.getOrder()));
                break;
            }
        }
        super.run();
    }
}
