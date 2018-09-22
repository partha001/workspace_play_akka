package com.partha.akka01;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * @description: this is a simple akka program which takes which processess
 * 				string object requests
 * @author partha
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//this create method has got several other useful overloads
    	ActorSystem actorSystem= ActorSystem.create("myActorSystem");
    	
    	
    	//creating the actor instance
    	final ActorRef actorRef1  = actorSystem.actorOf(MyActor1.props(), "actor1_1");
    	
    	//sending the message
    	actorRef1.tell("hello world", ActorRef.noSender());
    	actorRef1.tell(new Integer(1), ActorRef.noSender());
    	actorRef1.tell("hello world", ActorRef.noSender());
    	   	
    }
}
