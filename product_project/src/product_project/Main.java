package product_project;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class Main {

    public static void main(String[] args) {

        jade.core.Runtime r = jade.core.Runtime.instance();
        Profile profile = new ProfileImpl("localhost", 5000,"aa");
        ContainerController container = r.createMainContainer(profile);

        AgentController Agent1;
        AgentController Agent2;
        AgentController Agent3;
        AgentController Agent4;

        try {
            Agent2 = container.createNewAgent("ClientAgent", "com.AI.ClientAgent",null );
            Agent2.start();
          
        }catch (StaleProxyException e){
            System.out.println(e);
        }
        try {
            Agent3 = container.createNewAgent("ClientAgent1", "com.AI.ClientAgent",null );
            Agent3.start();
          
        }catch (StaleProxyException e){
            System.out.println(e);
        }
        try {
            Agent4 = container.createNewAgent("ClientAgent2", "com.AI.ClientAgent",null );
            Agent4.start();
          
        }catch (StaleProxyException e){
            System.out.println(e);
        }

        try {
            Agent1 = container.createNewAgent("FactoryAgent", "com.AI.FactoryAgent", null);
            Agent1.start();

        }catch (StaleProxyException e){
            System.out.println(e);
        }


    }
}
