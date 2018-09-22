package com.partha.akka02;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//this create method has got several other useful overloads
    	ActorSystem actorSystem= ActorSystem.create("myActorSystem");
    	
    	
    	//creating the actor instance
    	final ActorRef actorRef1  = actorSystem.actorOf(MyActor.props(), "actor1_1");
    	
    	//sending the message
    	actorRef1.tell(new MyActor.RequestType1("this is custom request"), ActorRef.noSender());
    	actorRef1.tell("hello world", ActorRef.noSender());
    	//actorRef1.tell(new Integer(1), ActorRef.noSender());
    }
}
