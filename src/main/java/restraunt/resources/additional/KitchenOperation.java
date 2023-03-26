package restraunt.resources.additional;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class KitchenOperation implements Comparable{
    @JsonProperty("oper_type")
    int ID;
    @JsonProperty("oper_time")
    double time;
    @JsonProperty("oper_async_point")
    int point;
    @JsonProperty("oper_products")
    List<OperationProduct> products;

    @Override
    public int compareTo(Object o) {
        var op = (KitchenOperation)o;
        return point - op.point;
    }
}