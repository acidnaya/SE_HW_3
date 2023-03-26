package restaurant.agents;

import lombok.extern.slf4j.Slf4j;
import restaurant.messages.*;
import restaurant.resources.basic.*;
import java.util.*;


/**
 * СкладАгент
 * */
@Slf4j
public class WarehouseAgent extends Agent<Message> {

    public WarehouseAgent(List<Product> products) {
        Map<Integer, Double> typedProducts = new HashMap<>();
        for(var product: products) {
            typedProducts.merge(product.getType(), product.getQuantity(), Double::sum);
        }
        if (typedProducts.isEmpty()) {
            log.info("[{}] Warehouse is empty", Time.now);
        }
    }

    @Override
    protected void proceed(Message message) {
        if (message instanceof ReserveMessage) {
            log.info("[{}] Warehouse reserved products", Time.now);
        }
    }
}
