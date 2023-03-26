package restraunt.resources.basic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class KitchenFacility {
    @JsonProperty("equip_type")
    int type;
    @JsonProperty("equip_name")
    String name;
    @JsonProperty("equip_active")
    boolean active;
}
