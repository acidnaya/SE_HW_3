package restraunt;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @JsonProperty("vis_name")
    String name;
    @JsonProperty("vis_ord_started")
    // так работает но возможно стоит сделать более универсальное решение с часовыми поясами
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = Configurations.timezone)
    Date started;
    @JsonProperty("vis_ord_ended")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = Configurations.timezone)
    Date ended; // ЗАЧЕМ КАК ПОЧЕМУ
    @JsonProperty("vis_ord_total")
    int total;
    @JsonProperty("vis_ord_dishes")
    List<Order> order;

}
