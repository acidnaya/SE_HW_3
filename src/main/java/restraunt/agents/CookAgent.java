package restraunt.agents;

import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import restraunt.messages.Message;
import restraunt.resources.basic.Cook;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class CookAgent extends Agent{
    List<Cook> cooks;
    public synchronized Cook getFreeCook() {
        while (true) {
            for (var cook : cooks) {
                if(cook.isActive()) {
                    cook.setActive(false);
                    log.info("[{}] Cook {} reserved.", Time.now, cook.getCname());
                    return cook;
                }
            }
        }
    }
    @Override
    protected void proceed(Message message) throws Exception {
    }
}
