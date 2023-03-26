package restraunt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static Logger logger = LoggerFactory.getLogger(Main.class);
    public static RestaurantSimulation restaurant;
    public static void main(String[] args) {
        RestaurantSimulation rs = new RestaurantSimulation();
        rs.getResources();
        restaurant = new RestaurantSimulation();
        restaurant.getResources();
        restaurant.start();
    }
}