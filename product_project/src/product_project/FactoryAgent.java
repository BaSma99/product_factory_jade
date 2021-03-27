package product_project;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Scanner;

public class FactoryAgent extends Agent {

    Product[] products = {
            new Product("book", 14.5, 120),
            new Product("phone", 850, 50),
            new Product("pen", 2.5, 600)
    };


    @Override
    protected void setup() {
        System.out.println("Factory is Running ...");

        receiveBehave();
    }

    void receiveBehave(){
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();

                if (msg != null) {

                    AID clientID = msg.getSender();
                    String localID = clientID.getLocalName();

                    if(msg.getContent().equals("Request Products")){
                        System.out.println("new order from: " + localID);
                        System.out.println(msg.getContent());
                        sendProductList();

                        Scanner updated = new Scanner(System.in);
                        System.out.println("Enter 1 to update or 0 to skip");
                        String updated_state = updated.nextLine();

                        if(updated_state.equals("1")){
                            update();
                        }else if(updated_state.equals("0")){
                            receiveBehave();
                        }
                    } else if (msg.getContent().equals("bad") || msg.getContent().equals("good")){
                        receiveMoneyState(msg.getContent());
                    }else if(msg.getContent().substring(0,9).equals("product: ")){
                        System.out.println(msg.getContent());
                    }
                }
            }
        });
    }

    void sendProductList() {

        String msg = "Available Products => ";

        for (Product product : this.products) {
            msg += product.productName + ":";
        }
        ACLMessage acceptance = new ACLMessage(ACLMessage.REQUEST);

        acceptance.addReceiver(new AID("ClientAgent", AID.ISLOCALNAME));

        acceptance.setContent(msg);

        send(acceptance);
    }


    public void receiveMoneyState(String msg) {
        System.out.println(msg);

        responseToState(msg);
    }

    void responseToState(String msg){
        if(msg.equals("bad")){

            ACLMessage state = new ACLMessage(ACLMessage.REQUEST);

            state.addReceiver(new AID("ClientAgent", AID.ISLOCALNAME));

            state.setContent("send only two products");

            send(state);
        }else if(msg.equals("good")){

            ACLMessage state = new ACLMessage(ACLMessage.REQUEST);

            state.addReceiver(new AID("ClientAgent", AID.ISLOCALNAME));

            state.setContent("you are good");

            send(state);
        }
    }

    void update(){

        Scanner updated = new Scanner(System.in);
        System.out.println("Enter updated product");
        String updated_product = updated.nextLine();

        ACLMessage state = new ACLMessage(ACLMessage.REQUEST);

        state.addReceiver(new AID("ClientAgent", AID.ISLOCALNAME));

        state.setContent("updated " + updated_product);

        send(state);
    }

}
