package restraunt;

import restraunt.agent.Agent;
import restraunt.agent.Message;
import restraunt.agent.OrderMessage;

public class ManagerAgent extends Agent {
    CookAgent cook;

    protected void setup() {
        System.out.println("Hello, I am a manager!");
//        cook = new CookAgent(1, "John", true);
//        cook.setup();
    }

    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof OrderMessage m) {
            System.out.println("Customer ordered: " + m.order);
        }
    }
}