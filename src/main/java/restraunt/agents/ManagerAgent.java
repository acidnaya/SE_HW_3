package restraunt.agents;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import restraunt.messages.Message;
import restraunt.messages.OrderMessage;

public class ManagerAgent extends Agent {
    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof OrderMessage m) {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(m.getOrder());
            System.out.println(getName() + " get ordered message " + json);
            var a = m.getOrder();

            var order = new OrderAgent(m.getCustomer(), m.getOrder());
            order.start(order);
        }
    }
}