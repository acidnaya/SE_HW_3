package restraunt.agents;

import lombok.extern.slf4j.Slf4j;
import restraunt.messages.Message;
import restraunt.messages.OrderMessage;

@Slf4j
public class ManagerAgent extends Agent {
    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof OrderMessage m) {
            log.info("[{}] Manager received {} order", Time.now, m.getCustomer().getName());

            var order = new OrderAgent(m.getCustomer(), m.getOrder());
            start(order);
        }
    }
}