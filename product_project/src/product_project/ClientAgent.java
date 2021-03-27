package product_project;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class ClientAgent extends Agent {


    @Override
    protected void setup() {

        System.out.println("Client one Sending ...");

        firstRequest();
        addBehaviour(new ClientBehaviour());

    }

    void firstRequest(){
        ACLMessage clientOrder = new ACLMessage(ACLMessage.REQUEST);

        clientOrder.addReceiver(new AID("FactoryAgent", AID.ISLOCALNAME));

        clientOrder.setContent("Request Products");

        send(clientOrder);
    }



}
