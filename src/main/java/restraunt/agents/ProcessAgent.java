package restraunt.agents;
import lombok.extern.slf4j.Slf4j;
import restraunt.Main;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import restraunt.messages.*;
import restraunt.resources.additional.KitchenOperation;
import restraunt.resources.additional.OperationLog;
import restraunt.resources.basic.Process;

@Slf4j
public class ProcessAgent extends Agent {
    volatile private static int counter = 0;
    private int ID;
    volatile private int ready = 0;
    int dish;
    OrderAgent parent;

    Process proc = new Process();

    List<KitchenOperation> operations; // список операций из дишкард -
    // их надо кинуть в кукАгент - он раскидывает их по свободным поварам (непонятно как мы чекаем свободность повара)

    public synchronized void increaseReadyCounter() {
        ready += 1;
    }

    public synchronized void setID() {
        counter += 1;
        ID = counter;
    }

    public ProcessAgent(int d, OrderAgent p) {
        setID();
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
        log.info("[{}] Process #{} started", Time.now, ID);
        var a = Main.restaurant.getDishes();
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
            if (ready == operations.size()) {
                break;
            }
        }
        log.info("[{}] Process #{} ended", Time.now, ID);
        parent.registerMessage(new EndMessage());
        stop(this);
    }

    @Override
    protected void proceed(Message message) throws Exception {

    }
}
