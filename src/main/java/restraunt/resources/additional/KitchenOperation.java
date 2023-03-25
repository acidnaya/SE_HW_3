package restraunt.resources.additional;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class KitchenOperation {
    @JsonProperty("oper_type")
    int ID;
    @JsonProperty("oper_time")
    double time;
    @JsonProperty("oper_async_point") // какой тип данных хз
    int point;
    @JsonProperty("oper_products")
    List<OperationProduct> products;
}