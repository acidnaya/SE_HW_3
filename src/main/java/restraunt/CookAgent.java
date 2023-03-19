package restraunt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class CookAgent {
    @JsonProperty("cook_id")
    int ID;
    @JsonProperty("cook_name")
    String name;
    @JsonProperty("cook_active")
    boolean active;


}
