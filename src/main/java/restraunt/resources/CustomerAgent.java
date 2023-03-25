package restraunt.resources;

import lombok.AllArgsConstructor;
import restraunt.Customer;
import restraunt.Main;
import restraunt.agent.Agent;
import restraunt.agent.Message;
import restraunt.agent.OrderMessage;

@AllArgsConstructor
public class CustomerAgent extends Agent {
    Customer cust;

    @Override
    protected void proceed(Message message) throws Exception {

    }

    @Override
    public void run() {
        var t = cust.getStarted().getTime();
        while (true) {
            if (Main.restaurant.time.isTimeToAct(t)) {
                System.out.println("---Customer register order---");
                Main.restaurant.getManager().registerMessage(new OrderMessage(cust.getOrder()));
                break;
            }
        }
    }
}
