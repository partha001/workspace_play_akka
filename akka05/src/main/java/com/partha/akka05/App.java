package com.partha.akka05;

import com.partha.akka05.actor.ParentActor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * this example is to see how the supervision strategies work
 * @author partha
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//this create method has got several other useful overloads
    	ActorSystem actorSystem= ActorSystem.create("myActorSystem");
    	
    	
    	
    	final ActorRef parent  = actorSystem.actorOf(ParentActor.props(), "parent");
    	
    	parent.tell("resume", ActorRef.noSender());
    	//parent.tell("restart", ActorRef.noSender());
    	//parent.tell("stop", ActorRef.noSender());
    	//parent.tell("escalate", ActorRef.noSender());
    	
    	
    }
}
