package restraunt.agents;

import restraunt.messages.*;
import restraunt.resources.basic.*;
import java.util.*;

public class WarehouseAgent extends Agent {
    private List<Product> products;
    private List<ProductType> productTypes;
    private Map<Integer, Double> typedProducts;

    public WarehouseAgent(List<Product> products, List<ProductType> productTypes) {
        typedProducts = new HashMap<>();
        for(var product: products) {
            typedProducts.merge(product.getType(), product.getQuantity(), (prev, q) -> prev + q);
        }
//        typedProducts.forEach((key, value) -> {
//            System.out.println("Key = " + key + ", Value = " + value);
//        });
    }

    private void reserveProduct(int productType, double quantity) {
        var q = typedProducts.get(productType);
        typedProducts.replace(productType, q - quantity);
    }

    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof ReserveMessage m) {
            System.out.println(getName() + " reserve products" );
        }
    }
}
