package restraunt.resources;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDish {
    int ID;
    int card;
    int price;
    @Setter
    boolean active;
}
