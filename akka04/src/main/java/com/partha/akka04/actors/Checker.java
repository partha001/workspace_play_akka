package com.partha.akka04.actors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.partha.akka04.bo.User;
import com.partha.akka04.messages.CheckerMessage;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class Checker extends AbstractActor{

	//message definition start

	//request message
	public static class CheckUser extends CheckerMessage{

		public User user;

		public User getUser() {
			return user;
		}

		public CheckUser(User user) {
			super();
			this.user = user;
		}		
		
	}

	//response messages
	public static class BlackUser extends CheckerMessage{
		public User user;

		public BlackUser(User user) {
			super();
			this.user = user;
		}	
	}

	public static class WhiteUser extends CheckerMessage{
		public User user;
		
		public WhiteUser(User user) {
			super();
			this.user = user;
		}	
	}
	//message definition end
	
	//lets suppose it checks the for the blacklisted user against the entries in below list
	List<User> userBlackList=new ArrayList<>(Arrays.asList(new User("bibhash","bibhas@gmail.com")));
	
	
	public static Props props(){
		return Props.create(Checker.class);
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(CheckUser.class, checkReq -> {
					if(userBlackList.contains(checkReq.getUser())){
						getSender().tell(new BlackUser(checkReq.getUser()), getSelf());
					}else{
						getSender().tell(new WhiteUser(checkReq.getUser()), getSelf());
					}
					System.out.println("Checker.createReceive() :: checking completed successfully");
				})
				.build();
	}


}
