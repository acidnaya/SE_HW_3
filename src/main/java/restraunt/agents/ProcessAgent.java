package restraunt.agents;
import restraunt.Main;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import restraunt.messages.*;
import restraunt.resources.additional.KitchenOperation;
import restraunt.resources.additional.OperationLog;
import restraunt.resources.basic.Process;

// @AllArgsConstructor
public class ProcessAgent extends Agent {
    private static int counter = 0;
    private int ID;
    private int ready = 0;
    int dish;
    OrderAgent parent;

    Process proc = new Process();

    List<KitchenOperation> operations; // список операций из дишкард -
    // их надо кинуть в кукАгент - он раскидывает их по свободным поварам (непонятно как мы чекаем свободность повара)


    public synchronized void increaseReadyCounter() {
        ready += 1;
    }

    public ProcessAgent(int d, OrderAgent p) {
        ID = ++counter;
        dish = d;
        parent = p;
        proc.setActive(true);
        proc.setDish(dish);
        try {
            proc.setStarted(Main.restaurant.time.getCurrentDate());
        } catch (ParseException e) {
            System.out.println("Can't set started data for " + getName());
        }
    }


    @Override
    public void run() {
        System.out.println("Process for " + dish + " started");
        var a = Main.restaurant.dishes;
        int eqID = 0;
        int cardNum = 0;
        for (var i : a) {
            if (i.getID() == dish) {
                cardNum = i.getCard();
                var b = Main.restaurant.dishCards;
                for (var j : b) {
                    if (j.getID() == cardNum) {
                        eqID = j.getEquipment();
                        operations = j.getOperations();
                        break;
                    }
                }
                break;
            }
        }
        Collections.sort(operations);
        var f = Main.restaurant.getFacility().getFreeFacility(eqID);
        for (int i = 0; i < operations.size(); ++i) {
            var cook = Main.restaurant.getCook().getFreeCook();
            OperationLog l = new OperationLog(ID, cardNum, eqID, cook.getID());
            OperationAgent operationAgent= new OperationAgent(l, this, operations.get(i), cook, f);
            Agent.start(operationAgent);
            if (!(i < operations.size() - 1 && operations.get(i).getPoint() == operations.get(i + 1).getPoint())) {
                while (true) {
                    if (ready == i + 1) {
                        break;
                    }
                }
            }
        }
        while (true) {
            System.out.println("ПОВИС");
            if (ready == operations.size()) {
                break;
            }
        }
        System.out.println("Process " + ID + " ended");
        parent.registerMessage(new EndMessage());
        stop(this);
//        Main.restaurant.getCook().registerMessage(new ReserveCooksMessage(operations));
//        try {
//            Thread.sleep(100); // cпим
//        } catch (InterruptedException e) {
//        }
//        proc.setActive(false);
//        parent.registerMessage(new EndMessage());
    }

    @Override
    protected void proceed(Message message) throws Exception {

    }
}
