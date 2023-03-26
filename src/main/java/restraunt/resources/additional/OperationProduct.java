package restraunt.resources.additional;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OperationProduct {
    @JsonProperty("prod_type")
    int ID;
    @JsonProperty("prod_quantity")
    double quantity;
}