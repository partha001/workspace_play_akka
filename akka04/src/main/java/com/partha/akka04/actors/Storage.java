package com.partha.akka04.actors;

import java.util.ArrayList;
import java.util.List;

import com.partha.akka04.bo.User;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class Storage extends AbstractActor{
	
	public static class AddUser{
		public User user;

		public AddUser(User user) {
			super();
			this.user = user;
		}		
	}
	
	//lets suppose this actor stores the user in the below list
	private List<User> userList=new ArrayList<>();
	
	
	public static Props props(){
		return Props.create(Storage.class);
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(AddUser.class, userReq -> {
					userList.add(userReq.user);
					System.out.println("Storage.createReceive() :: new user added");
				})
				.build();
	}
	
	

}
