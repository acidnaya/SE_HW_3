package restraunt.agents;

import lombok.AllArgsConstructor;
import restraunt.Config;
import restraunt.Main;
import restraunt.messages.Message;
import restraunt.resources.additional.*;
import restraunt.resources.basic.*;

@AllArgsConstructor
public class OperationAgent extends Agent {
    OperationLog log; // вас куда-то надо записывать и потом выводить лог
    ProcessAgent parent;
    KitchenOperation op;
    Cook cook;
    KitchenFacility facility;
    @Override
    public void run() {
//        var a = Main.restaurant.cooks;
//        for (var i : a) {
//            if (!i.isActive()) {
//                i.setActive(true);
//                System.out.println(i.getCname() + " starts work");
//                try {
//                    Thread.sleep((long) op.getTime()); // со спид что то сделать
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                i.setActive(false);
//                System.out.println(i.getCname() + " ends work");
//                break;
//            }
//        }

        System.out.println("Operation " + log.getID() + " for process " + log.getProcessID() + " started");
        try {
            Thread.sleep((long) op.getTime() * 1000 / Config.speed);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            cook.setActive(true);
            facility.setActive(true);
            System.out.println("Operation " + log.getID() + " for process " + log.getProcessID() + " ended");
            parent.increaseReadyCounter();
        }
    }

    @Override
    protected void proceed(Message message) throws Exception {

    }
}
