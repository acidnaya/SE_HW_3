package restraunt.agents;

import lombok.AllArgsConstructor;
import restraunt.messages.Message;
import restraunt.resources.basic.DishCard;
import restraunt.resources.basic.MenuDish;

import java.util.List;

@AllArgsConstructor
public class MenuAgent extends Agent {

    private List<DishCard> cards;
    private List<MenuDish> dishes;

    @Override
    protected void proceed(Message message) throws Exception {

    }
}
