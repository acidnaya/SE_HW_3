package restraunt.agents;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import restraunt.messages.Message;
import restraunt.messages.OrderMessage;

@Slf4j
public class ManagerAgent extends Agent {
    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof OrderMessage m) {
//            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//            String json = ow.writeValueAsString(m.getOrder());
//            System.out.println(getName() + " get ordered message " + json);
            log.info("[{}] Manager received {} order", Time.now, m.getCustomer().getName());
            var a = m.getOrder();

            var order = new OrderAgent(m.getCustomer(), m.getOrder());
            order.start(order);
        }
    }
}