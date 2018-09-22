package com.partha.akka01;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;


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
    	   	
    }
}
