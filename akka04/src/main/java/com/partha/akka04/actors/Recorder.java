package com.partha.akka04.actors;

import java.util.concurrent.TimeUnit;

import com.partha.akka04.bo.User;
import com.partha.akka04.messages.CheckerMessage;
import com.partha.akka04.messages.RecorderMessage;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;

public class Recorder extends AbstractActor{
	
	//request message class
	public static class NewUser extends RecorderMessage{
		public User user;

		public NewUser(User user) {
			super();
			this.user = user;
		}	
	}
	
	
	private ActorRef checker;
	private ActorRef storage;
	
	public static Props props(ActorRef checker, ActorRef storage){
		return Props.create(Recorder.class, checker , storage);
	}
	
	
	

	public Recorder(ActorRef checker, ActorRef storage) {
		super();
		this.checker = checker;
		this.storage = storage;
	}


	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(NewUser.class, req ->{
					
					System.out.println("Recorder.createReceive() :: new user request received");
					
					Timeout timeout = new Timeout(5, TimeUnit.SECONDS);
					scala.concurrent.Future<Object> future = Patterns.ask(checker, new Checker.CheckUser(req.user), timeout);
					CheckerMessage message = (CheckerMessage) Await.result(future, timeout.duration());
					
					if(Checker.BlackUser.class.getName().equals(message.getClass().getName())){
						System.out.println("Recorder.createReceive() :: this is a blacklisted user");
					}else{
						System.out.println("Recorder.createReceive() :: this is not a blacklisted user");
						System.out.println("Recorder.createReceive() :: now making the storage request");
						storage.tell(new Storage.AddUser(req.user), getSelf());
					}
				}).build();
	}
	

}
