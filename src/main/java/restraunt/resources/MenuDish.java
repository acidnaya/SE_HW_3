package restraunt.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDish {
    @JsonProperty("menu_dish_id")
    int ID;
    @JsonProperty("menu_dish_card")
    int card;
    @JsonProperty("menu_dish_price")
    int price;
    @JsonProperty("menu_dish_active")
    boolean active;
}
