package com.partha.akka07.routerGroup;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class Worker extends AbstractActor{
	
	public static class Work{
		
	}
	
	public static Props props(){
		return Props.create(Worker.class);
	}
	

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(Work.class, req -> {
					System.out.println("I have a received a worker message and my ActorRef is:"+ self() );
					System.out.println(self().path());
				})
				.build();
	}

}
