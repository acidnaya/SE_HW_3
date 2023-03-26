package restraunt.resources.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @JsonProperty("ord_dish_id")
    int ID;
    @JsonProperty("menu_dish")
    int dish;
    @JsonIgnore()
    public String toString() {
        return "Order: " + Integer.toString(ID) + "; Dish: " + Integer.toString(dish);
    }
}
