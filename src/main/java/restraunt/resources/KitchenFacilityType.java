package restraunt.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class KitchenFacilityType {
    @JsonProperty("equip_type_id")
    int ID;
    @JsonProperty("equip_type_name")
    String name;
}
