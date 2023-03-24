package restraunt.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class ProductType {
    @JsonProperty("prod_type_id")
    int ID;
    @JsonProperty("prod_type_name")
    String name;
    @JsonProperty("prod_is_food")
    boolean isFood;
}
