package restraunt.agents;

import lombok.AllArgsConstructor;
import restraunt.messages.EndMessage;
import restraunt.messages.Message;

// @AllArgsConstructor
public class ProcessAgent extends Agent {
    int dish;
    OrderAgent parent;

    public ProcessAgent(int d, OrderAgent p) {
        dish = d;
        parent = p;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100); // cпим
        } catch (InterruptedException e) {
        }
        parent.registerMessage(new EndMessage());
    }

    @Override
    protected void proceed(Message message) throws Exception {

    }
}
