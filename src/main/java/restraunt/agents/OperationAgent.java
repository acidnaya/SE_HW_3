package restraunt.agents;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import restraunt.Config;
import restraunt.messages.Message;
import restraunt.resources.additional.*;
import restraunt.resources.basic.*;

@AllArgsConstructor
@Slf4j
public class OperationAgent extends Agent {
    OperationLog operationLog; // вас куда-то надо записывать и потом выводить лог
    ProcessAgent parent;
    KitchenOperation op;
    Cook cook;
    KitchenFacility facility;
    @Override
    public void run() {
        log.info("[{}] Operation {} for process {} started", Time.now, operationLog.getID(), operationLog.getProcessID());
        try {
            Thread.sleep((long) op.getTime() * 1000 / Config.speed);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            cook.setActive(true);
            facility.setActive(true);
            log.info("[{}] Operation {} for process {} ended", Time.now, operationLog.getID(), operationLog.getProcessID());
            parent.increaseReadyCounter();
        }
    }

    @Override
    protected void proceed(Message message) throws Exception {

    }
}
