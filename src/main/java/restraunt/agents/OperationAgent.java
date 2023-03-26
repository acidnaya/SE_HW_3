package restraunt.agents;

import restraunt.Main;
import restraunt.messages.EndMessage;
import restraunt.messages.Message;
import restraunt.messages.ReserveCooksMessage;
import restraunt.resources.additional.KitchenOperation;

public class OperationAgent extends Agent{
    KitchenOperation op;
    OperationAgent(KitchenOperation operation) {
        op = operation;
    }
    @Override
    public void run() {
        var a = Main.restaurant.cooks;
        for (var i : a) {
            if (!i.isActive()) {
                i.setActive(true);
                System.out.println(i.getCname() + " starts work");
                try {
                    Thread.sleep((long) op.getTime()); // со спид что то сделать
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                i.setActive(false);
                System.out.println(i.getCname() + " ends work");
                break;
            }
        }
    }

    @Override
    protected void proceed(Message message) throws Exception {

    }
}
