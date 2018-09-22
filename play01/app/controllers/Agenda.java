package controllers;

import java.util.List;

import models.Contact;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

public class Agenda extends Controller{
	
	public  Result list(){
		List<Contact> contact = Contact.find.all();
		return ok(views.html.list.render(contact));
	}
		
	public Result show(Long id){
		Contact contact = Contact.find.byId(id);
		if(contact == null){
			return notFound();
		}else{
			return ok(views.html.show.render(contact));
		}
	}

	public  Result newContact(){
		//actually FormFactory should be injected
		FormFactory formFactory=new FormFactory(null, null, null);
		Form<Contact> contactForm=formFactory.form(Contact.class);
		return ok(views.html.newContact.render(contactForm));
	}
	
	
	public Result createContact(){
		//actually FormFactory should be injected
		FormFactory formFactory=new FormFactory(null, null, null);
		Form<Contact> contactForm=formFactory.form(Contact.class).bindFromRequest();
		Contact contact=contactForm.get();
		contact.save();
		return redirect("/contacts");
	}
	
}
