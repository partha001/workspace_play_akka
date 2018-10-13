package com.partha.akka06_actorSelection;

import com.partha.akka06_actorSelection.actors.Counter;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;

/**
 * this is an example of actorRef and actorSelection
 * @author partha
 *
 */
public class App 
{
	public static void main( String[] args )
	{
		try {

			ActorSystem system=ActorSystem.create("myActorSystem");

			//creating an actorref
			ActorRef ref1= system.actorOf(Counter.props(), "counter1"); 
			System.out.println("value of actorRef is:"+ref1);

			//creating an actor-selection
			ActorSelection actorSelection = system.actorSelection("counter");
			System.out.println("value of actor-selection is :"+actorSelection);

			ref1.tell(PoisonPill.getInstance(),ActorRef.noSender());


			Thread.sleep(5000);
			
			ActorRef ref2= system.actorOf(Counter.props(), "counter1"); 
			System.out.println("value of actorRef is:"+ref2);

			//creating an actor-selection
			ActorSelection actorSelection2 = system.actorSelection("counter");
			System.out.println("value of actor-selection is :"+actorSelection2);

			

			System.out.println("now terminating the system");
			system.terminate();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
