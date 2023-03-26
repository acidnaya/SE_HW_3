package restraunt.resources.additional;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

public class KitchenOperation {
    @JsonProperty("oper_type")
    int ID;
    @Getter
    @JsonProperty("oper_time")
    double time;
    @JsonProperty("oper_async_point") // какой тип данных хз
    int point;
    @JsonProperty("oper_products")
    List<OperationProduct> products;
}