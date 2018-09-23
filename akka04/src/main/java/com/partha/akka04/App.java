package com.partha.akka04;

import com.partha.akka04.actors.Checker;
import com.partha.akka04.actors.Recorder;
import com.partha.akka04.actors.Storage;
import com.partha.akka04.bo.User;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * this program is to understand the difference between ask and tell
 * @author partha
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//this create method has got several other useful overloads
    	ActorSystem actorSystem= ActorSystem.create("myActorSystem");
    	
    	final ActorRef checker =  actorSystem.actorOf(Checker.props(), "checker");
    	
    	final ActorRef storage =  actorSystem.actorOf(Storage.props(), "storage");
    	
    	final ActorRef recorder  = actorSystem.actorOf(Recorder.props(checker , storage), "recorder");
    	
    	//the below is not a blacklisted user
    	User user=new User("partha", "partha@gmail.com");
    	
    	//the below is blacklisted user
    	//User user=new User("bibhas", "bibhas@gmail.com");
    	recorder.tell( new Recorder.NewUser(user), ActorRef.noSender());
    }
}
