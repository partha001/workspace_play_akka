package com.partha.akka03;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class MusicPlayer extends AbstractLoggingActor{

	static class StartMusic extends PlayerMessage{
		
	}
	
	
	static class StopMusic extends PlayerMessage{
		
	}
	
	static Props props(){
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
