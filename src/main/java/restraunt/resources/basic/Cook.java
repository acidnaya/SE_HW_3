package restraunt.resources.basic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class Cook {
    @JsonProperty("cook_id")
    int ID;
    @JsonProperty("cook_name")
    String cname;
    @JsonProperty("cook_active")
    boolean active;
}
