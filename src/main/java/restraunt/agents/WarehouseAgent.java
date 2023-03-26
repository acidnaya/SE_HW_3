package restraunt.agents;

import lombok.extern.slf4j.Slf4j;
import restraunt.messages.*;
import restraunt.resources.basic.*;
import java.util.*;

@Slf4j
public class WarehouseAgent extends Agent {
    private List<Product> products;
    private List<ProductType> productTypes;
    private Map<Integer, Double> typedProducts;

    public WarehouseAgent(List<Product> products, List<ProductType> productTypes) {
        typedProducts = new HashMap<>();
        for(var product: products) {
            typedProducts.merge(product.getType(), product.getQuantity(), (prev, q) -> prev + q);
        }
    }

    private void reserveProduct(int productType, double quantity) {
        var q = typedProducts.get(productType);
        typedProducts.replace(productType, q - quantity);
    }

    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof ReserveMessage m) {
            log.info("[{}] Warehouse reserved products", Time.now);
        }
    }
}
