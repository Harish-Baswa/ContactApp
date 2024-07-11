package com.edubridge.ContactApp;

import java.util.List;
import java.util.Scanner;

import com.edubridge.ContactApp.dao.ContactDaoImpl;
import com.edubridge.ContactApp.model1.Contact;
import com.edubridge.ContactApp.dao.ContactDao;

public class App 
{
    public static void main( String[] args )
    {
    	ContactDao dao= new ContactDaoImpl();
    	Scanner in=new Scanner(System.in);
    	while(true) {
    		System.out.println("MY CONTACT APP");
    		System.out.println("--------------");
    		System.out.println("1.ADD CONTACT");
    		System.out.println("2.VIEW ALL CONTACTS");
    		System.out.println("3.SEARCH CONTACTS");
    		System.out.println("4.UPDATE CONTACT");
    		System.out.println("5.DELETE CONTACT");
    		System.out.println("6.DELETE ALL CONTACTS");
    		System.out.println("0. EXIT");
    		System.out.println("PLEASE CHOOSE OPTION:");
    		
    		int option =in.nextInt();
    		switch (option) {
			case 1:
				System.out.println("Please enter contact name:");
				String name=in.next();
				
				System.out.println("Please enter contact email:");
				String email=in.next();
				
				System.out.println("Please enter contact mobile:");
				long mobile=in.nextLong();
				
				Contact newContact=new Contact();
				newContact.setName(name);
				newContact.setEmail(email);
				newContact.setMobile(mobile);
				
				int status=dao.addContact(newContact);
				if(status>=1) {
					System.out.println("New Contact Saved!!!");
				}else {
					System.out.println("Something is wrong!!!");
				}
				break;
			case 2:
				List<Contact> contacts=dao.getAllContacts();
				if(contacts.size() !=0) {
					for(Contact c : contacts) {
						System.out.println(c.getId()+"\t"+c.getName()+"\t"+c.getEmail()
						+"\t"+c.getMobile());
					}
				}else {
					System.out.println("no contacts found");
				}
				break;
			case 3:
				 System.out.println("Please enter the name of the contact to search:");
                 String searchName = in.next();
                 Contact contact = (Contact) dao.getContact(searchName);
                 if (contact !=null) {
                     System.out.println(contact.getId() + "\t" + contact.getName() + "\t" + contact.getEmail() + "\t" + contact.getMobile());
                 } else {
                     System.out.println("Contact not found");
                 }
				break;
			 case 4:
                 System.out.println("Please enter the ID of the contact to update:");
                 int id = in.nextInt();

                 System.out.println("Please enter new contact name:");
                 String Name = in.next();

                 System.out.println("Please enter new contact email:");
                 String Email = in.next();

                 System.out.println("Please enter new contact mobile:");
                 long Mobile = in.nextLong();

                 Contact c = new Contact();
                 c.setId(id);
                 c.setName(Name);
                 c.setEmail(Email);
                 c.setMobile(Mobile);

                 int Status = dao.updateContact(c);
                 if (Status >= 1) {
                     System.out.println("Contact Updated!!!");
                     System.out.println("------------------");
                     System.out.println(c.getId()+"\t"+c.getName()+"\t"+c.getEmail()
						+"\t"+c.getMobile());
                 }else {
                	 
                     System.out.println("There is no existing contact!!!");
                 }
                 break;
			 case 5:
                 System.out.println("Please enter the name of the contact to delete:");
                 String dname = in.next();
                 int dStatus = dao.deleteContact(dname);
                 if (dStatus >= 1) {
                     System.out.println("Contact Deleted!!!");
                 } else {
                     System.out.println("There is no such contact to delete!!!");
                 }
                 break;
			 case 6:
                 dao.deleteAllContacts();
                 System.out.println("All Contacts Deleted!!!");
                 break;
			case 0:
				System.out.println("Byee!!!");
				System.exit(0);

			default:
				System.out.println("Invalide Option!!!!");
				break;
			}
    	}
    }
}
