package restaurant.agents;

import lombok.extern.slf4j.Slf4j;
import restaurant.messages.Message;
import restaurant.messages.OrderMessage;

@Slf4j
public class ManagerAgent extends Agent<Message> {
    @Override
    protected void proceed(Message message) {
        if (message instanceof OrderMessage m) {
            log.info("[{}] Manager received {} order", Time.now, m.getCustomer().customer.getCustomerName());

            var order = new OrderAgent(m.getCustomer(), m.getOrder());
            start(order);
        }
    }
}