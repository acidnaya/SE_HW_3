package restaurant.agents;

import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import restaurant.resources.basic.Cook;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class CookAgent {
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
}
