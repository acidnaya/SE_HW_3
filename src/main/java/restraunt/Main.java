package restraunt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// PUSH TO ACIDNAYA
public class Main {
    public static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        // logger.info("Program starts!");

         RestaurantSimulation rs = new RestaurantSimulation();
         rs.getResources();
        //MenuDish itemWithOwner = new ObjectMapper().readValue(json, MenuDish.class);
    }
}