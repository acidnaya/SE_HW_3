package restraunt;

public class Main {
    public static RestaurantSimulation restaurant;
    public static void main(String[] args) {
        RestaurantSimulation rs = new RestaurantSimulation();
        rs.getResources();
        restaurant = new RestaurantSimulation();
        restaurant.getResources();
        restaurant.start();
    }
}