package restaurant.resources.basic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Process {
    @JsonProperty("oper_id")
    int ID;
    @JsonProperty("ord_dish")
    int dish;
    @JsonProperty("proc_started")
    Date started;
    @JsonProperty("proc_ended")
    Date ended;
    @JsonProperty("proc_active")
    boolean active;
    List<KitchenOperationType> operations;
}
