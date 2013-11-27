package net.sarigul.usermanager.entity;

import net.sarigul.usermanager.util.Toolbox;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Index;
import com.google.code.morphia.annotations.Indexes;

@Entity
@Indexes(@Index(name="uniqueUserIndex", unique= true, value="firstName, lastName, phoneNumber"))
public class User {
	@Id
	private ObjectId id;
	private String firstName, lastName;
	private Long phoneNumber;
	
	public ObjectId getId() {
		return id;
	}
	
	public User setId(ObjectId id) {
		this.id = id;
		return this;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}
	
	public User setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + " [id=" + id + ", name=" + firstName + ", phoneNumber=" + phoneNumber + "]";
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null || !(other instanceof User)) {
			return false;
		}
		User otherUser = (User) other;
		
		return 	Toolbox.equals(this.firstName, otherUser.firstName) &&
				Toolbox.equals(this.lastName, otherUser.lastName) &&
				Toolbox.equals(this.phoneNumber, otherUser.phoneNumber);
	}
}
