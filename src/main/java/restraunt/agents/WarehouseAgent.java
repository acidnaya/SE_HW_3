package restraunt.agents;

import lombok.extern.slf4j.Slf4j;
import restraunt.messages.*;
import restraunt.resources.basic.*;
import java.util.*;


/**
 * СкладАгент
 * */
@Slf4j
public class WarehouseAgent extends Agent {
    private Map<Integer, Double> typedProducts;

    public WarehouseAgent(List<Product> products) {
        typedProducts = new HashMap<>();
        for(var product: products) {
            typedProducts.merge(product.getType(), product.getQuantity(), Double::sum);
        }
    }


    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof ReserveMessage) {
            log.info("[{}] Warehouse reserved products", Time.now);
        }
    }
}
