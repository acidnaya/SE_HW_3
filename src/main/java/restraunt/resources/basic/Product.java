package restraunt.resources.basic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class Product {
    @JsonProperty("prod_item_id")
    int ID;
    @JsonProperty("prod_item_type")
    int type;
    @JsonProperty("prod_item_name")
    String name;
    @JsonProperty("prod_item_company")
    String company;
    @JsonProperty("prod_item_unit")
    String unit;
    @JsonProperty("prod_item_quantity")
    double quantity;
    @JsonProperty("prod_item_cost")
    double cost;
   // @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = Configurations.timezone)
    @JsonProperty("prod_item_delivered")
    Date delivered;
   // @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = Configurations.timezone)
    @JsonProperty("prod_item_valid_until")
    Date valid_until;
}
