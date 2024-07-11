package com.edubridge.ContactApp.dao;

import java.util.List;

import com.edubridge.ContactApp.model1.Contact;

public interface ContactDao {
	 int addContact(Contact c);
	 List<Contact> getAllContacts();
	 Contact getContact(String name);
	 int updateContact(Contact c);
	 int deleteContact(String name);
	 void deleteAllContacts();
	

}
