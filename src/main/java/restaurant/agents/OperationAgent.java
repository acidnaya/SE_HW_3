package restaurant.agents;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import restaurant.Config;
import restaurant.Main;
import restaurant.messages.Message;
import restaurant.resources.additional.*;
import restaurant.resources.basic.*;

@AllArgsConstructor
@Slf4j
public class OperationAgent extends Agent<Message> {
    OperationLog operationLog; // вас куда-то надо записывать и потом выводить лог
    ProcessAgent parent;
    KitchenOperation op;
    Cook cook;
    KitchenFacility facility;
    @Override
    public void run() {
        log.info("[{}] Operation {} for process {} started", Time.now, operationLog.getID(), operationLog.getProcessID());
        try {
            Thread.sleep((long) op.getTime() * 60 * 1000 / Config.speed);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            cook.setActive(true);
            facility.setActive(true);
            operationLog.fixEnded();
            log.info("[{}] Operation {} for process {} ended", Time.now, operationLog.getID(), operationLog.getProcessID());
            parent.increaseReadyCounter();
            Main.restaurant.addLog(operationLog);
        }
    }

    @Override
    protected void proceed(Message message) {

    }
}
