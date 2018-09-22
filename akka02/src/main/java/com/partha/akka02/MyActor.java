package com.partha.akka02;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class MyActor extends AbstractLoggingActor{

	private LoggingAdapter logger = Logging.getLogger(getContext().getSystem(), this);

	
	static class RequestType1{
		
		public String message;

		RequestType1(String message){
			this.message=message;
		}
	}

	public static Props props(){
		return Props.create(MyActor.class);
	}


	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(RequestType1.class, req ->{
					logger.info("have received request of type1 with request message: "+ req.message);
				})
				.match(Integer.class, System.out::println)
				.match(Object.class,req -> {
					this.onMessage();
				})
				.matchAny(req -> {
					System.out.println(req);
				})
				.build();
	}
	
	
	public void  onMessage(){
		System.out.println("some message received");
	}



}
