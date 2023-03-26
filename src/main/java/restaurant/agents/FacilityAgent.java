package restaurant.agents;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import restaurant.resources.basic.KitchenFacility;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class FacilityAgent {
    List<KitchenFacility> facilities;

    public synchronized KitchenFacility getFreeFacility(int requiredType) {
        while (true) {
            for (var f : facilities) {
                if (f.getType() != requiredType) {
                    continue;
                }
                if (f.isActive()) {
                    f.setActive(false);
                    log.info("[{}] Facility {} reserved.", Time.now, f.getName());
                    return f;
                }
            }
        }
    }
}
