package restraunt.agents;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import restraunt.messages.Message;
import restraunt.messages.ReserveCooksMessage;
import restraunt.messages.ReserveMessage;
import restraunt.resources.basic.Cook;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class CookAgent extends Agent{
    List<Cook> cooks;
    public synchronized Cook getFreeCook() {
        while (true) {
            for (var cook : cooks) {
                if(cook.isActive()) {
                    cook.setActive(false);
                    return cook;
                }
            }
        }
    }
    @Override
    protected void proceed(Message message) throws Exception {
//        System.out.println("Cook got message " + message);
//        if (message instanceof ReserveCooksMessage m) {
//            System.out.println(getName() + " reserve people" ); // пока так
//            for (var op : ((ReserveCooksMessage) message).ops) {
//                Thread myThready = new Thread(new Runnable()
//                {
//                    public void run() //Этот метод будет выполняться в побочном потоке
//                    {
//                        OperationAgent operAgent = new OperationAgent(op);
//                        operAgent.run();
//                    }
//                });
//                myThready.start();
//            }
//        }
    }
}
