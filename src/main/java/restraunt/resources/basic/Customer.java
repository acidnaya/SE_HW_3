package restraunt.resources.basic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import restraunt.Config;
import restraunt.resources.basic.Order;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class Customer {
    @JsonProperty("vis_name")
    String customerName;
    @JsonProperty("vis_ord_started")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = Config.timezone)
    Date started;
    @JsonProperty("vis_ord_ended")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = Config.timezone)
    Date ended;
    @JsonProperty("vis_ord_total")
    int total;
    @JsonProperty("vis_ord_dishes")
    List<Order> order;
}
