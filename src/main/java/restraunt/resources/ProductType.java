package restraunt.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Getter
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
