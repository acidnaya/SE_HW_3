package restraunt.agents;

import restraunt.messages.Message;
import restraunt.messages.OrderMessage;

public class ManagerAgent extends Agent {
    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof OrderMessage m) {
            System.out.println(getName() + " get ordered message " + m.getOrder());
            var order = new OrderAgent(m.getCustomer(), m.getOrder());
            order.start(order);
        }
    }
}