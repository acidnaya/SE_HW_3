package restraunt.agents;

import lombok.AllArgsConstructor;
import restraunt.messages.Message;
import restraunt.resources.basic.Cook;

@AllArgsConstructor
public class CookAgent extends Agent{
    Cook cook;

    @Override
    protected void proceed(Message message) throws Exception {

    }
}
