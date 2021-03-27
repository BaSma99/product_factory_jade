package product_project;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Scanner;

public class ClientBehaviour extends CyclicBehaviour {
    
 
    double money = 150000;
    
    
   Order order = new Order("book", 12.5, 100);
    

    @Override
    public void action() {

        
        Agent agent = getAgent();

        ACLMessage msg = agent.receive();

        if (msg != null) {
            if (msg.getContent().equals("you are good") || msg.getContent().equals("send only two products")) {
                System.out.println(msg.getContent());

                if (msg.getContent().equals("you are good")) {
                    sendOrder();
                }

            } else if (msg.getContent().substring(0, 9).equals("updated ")) {
                System.out.println(msg.getContent());

            } else if (msg.getContent().length() >= 22){
                if(msg.getContent().substring(0, 22).equals("Available Products => ")) {
                    AID clientID = msg.getSender();
                    String localID = clientID.getLocalName();
                    System.out.println(localID);
                    System.out.println(msg.getContent());
                    moneyState();

                }
            }
        }
    }

    public void moneyState() {
        

        String state = "";

        double total = order.singleProductPrice * order.singleProductCount;

        if (total > money)
            state = "bad";
        else
            state = "good";
            money = money - total;
            System.out.println(money);

        ACLMessage moneyState = new ACLMessage(ACLMessage.REQUEST);

        moneyState.addReceiver(new AID("FactoryAgent", AID.ISLOCALNAME));

        moneyState.setContent(state);

        getAgent().send(moneyState);
    }

    void sendOrder() {
        String order = "product: " + this.order.productName + ":" + this.order.singleProductPrice + ":" + this.order.singleProductCount;

        ACLMessage moneyState = new ACLMessage(ACLMessage.REQUEST);

        moneyState.addReceiver(new AID("FactoryAgent", AID.ISLOCALNAME));

        moneyState.setContent(order);

        getAgent().send(moneyState);
    }
}
