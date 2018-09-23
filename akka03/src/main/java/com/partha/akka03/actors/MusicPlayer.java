package com.partha.akka03.actors;

import com.partha.akka03.messages.PlayerMessage;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class MusicPlayer extends AbstractLoggingActor{

	//actor message definition start
	public static class StartMusic extends PlayerMessage{
		
	}
	
	
	static class StopMusic extends PlayerMessage{
		
	}
	//actor message definition end
	
	public static Props props(){
		return Props.create(MusicPlayer.class);
	}
	
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(StopMusic.class, req->{
					System.out.println("i dont want to stop music");
				})
				.match(StartMusic.class, req ->{
					System.out.println("inside MusicPlayer.receive()");
					ActorRef ref= context().actorOf(MusicController.props(), "controller");
					ref.tell(new MusicController.Play(), getSelf());
				})
				.build();
	}

}
