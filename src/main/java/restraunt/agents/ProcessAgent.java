package restraunt.agents;
import jdk.dynalink.Operation;
import lombok.AllArgsConstructor;
import restraunt.Main;
import restraunt.messages.EndMessage;
import restraunt.messages.Message;
import restraunt.agents.Time;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import restraunt.messages.ReserveCooksMessage;
import restraunt.messages.ReserveMessage;
import restraunt.resources.additional.KitchenOperation;
import restraunt.resources.basic.Process;

// @AllArgsConstructor
public class ProcessAgent extends Agent {
    int dish;
    OrderAgent parent;

    Process proc = new Process();

    List<KitchenOperation> operations; // список операций из дишкард -
    // их надо кинуть в кукАгент - он раскидывает их по свободным поварам (непонятно как мы чекаем свободность повара)

    public ProcessAgent(int d, OrderAgent p) {
        dish = d;
        parent = p;
        proc.setActive(true);
        proc.setDish(dish);
        try {
            proc.setStarted(Main.restaurant.time.getCurrentDate());
        } catch (ParseException e) {
            System.out.println("Can't set started data for " + getName());
        }
        var a = Main.restaurant.dishes;
        for (var i : a) {
            if (i.getID() == dish) {
                var cardNum = i.getCard();
                var b = Main.restaurant.dishCards;
                for (var j : b) {
                    if (j.getID() == cardNum) {
                        operations = j.getOperations();
                        break;
                    }
                }
                break;
            }
        }
    }


    @Override
    public void run() {
        System.out.println("Process for " + dish + " started");
        Main.restaurant.getCook().registerMessage(new ReserveCooksMessage(operations));
        try {
            Thread.sleep(100); // cпим
        } catch (InterruptedException e) {
        }
        proc.setActive(false);
        parent.registerMessage(new EndMessage());
    }

    @Override
    protected void proceed(Message message) throws Exception {

    }
}
