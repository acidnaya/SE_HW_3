package restraunt.resources.basic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restraunt.resources.additional.KitchenOperation;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DishCard {
    @JsonProperty("card_id")
    int ID;
    @JsonProperty("dish_name")
    String name;
    @JsonProperty("card_descr")
    String description;
    @JsonProperty("card_time")
    double time;
    @JsonProperty("equip_type")
    int equipment;
    @JsonProperty("operations")
    List<KitchenOperation> operations;
}
