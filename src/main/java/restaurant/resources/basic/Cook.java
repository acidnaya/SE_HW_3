package restaurant.resources.basic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cook {
    @JsonProperty("cook_id")
    int ID;
    @JsonProperty("cook_name")
    String cname;
    @JsonProperty("cook_active")
    boolean active;
}
