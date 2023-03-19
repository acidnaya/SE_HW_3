package restraunt.resources;

import java.util.Date;

public class Product {
    int ID;
    ProductType type; // возможно тут int
    String name;
    String company;
    String unit; // можно использовать Enum
    double quantity;
    double cost;
    Date delivered;
    Date valid_until;
}
