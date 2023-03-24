package restraunt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @JsonProperty("vis_name")
    String name;
    @JsonProperty("vis_ord_started")
    String started;
    @JsonProperty("vis_ord_ended")
    String ended; // ЗАЧЕМ КАК ПОЧЕМУ
    @JsonProperty("vis_ord_total")
    int total;
    @JsonProperty("vis_ord_dishes")
    List<Order> order;

}
