package com.partha.akka04.bo;

public class User {
	
	public String username;
	
	public String email;

	public User(String username, String email) {
		super();
		this.username = username;
		this.email = email;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj!=null){
			if(((User)obj).email.equals(this.email))
				return true;
			else
				return false;
		}else{
			return false;
		}
	}
	
	

}
