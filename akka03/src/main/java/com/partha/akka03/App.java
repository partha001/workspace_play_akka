package com.partha.akka03;

import com.partha.akka03.actors.MusicPlayer;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * this program is to demonstrate how to call one actor 
 * from inside another actor
 * @author partha
 */
public class App 
{
    public static void main( String[] args )
    {
    	//this create method has got several other useful overloads
    	ActorSystem actorSystem= ActorSystem.create("myActorSystem");
    	
    	//creating the actor instance
    	final ActorRef playerRef1  = actorSystem.actorOf(MusicPlayer.props(), "player");
    	
    	//sending the message
    	playerRef1.tell(new MusicPlayer.StartMusic(), ActorRef.noSender());
   
    }
}
