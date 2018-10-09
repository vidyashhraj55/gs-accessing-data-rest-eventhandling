package hello;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

//import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import ch.qos.logback.core.net.SyslogOutputStream;

@Entity
//@EntityListeners(AuditingEntityListener.class)
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String firstName;
	private String lastName;
	
	@Transient
	private String fullname=firstName+" "+lastName ;
	
//	@Temporal(TemporalType.DATE)
//    private Date created;
//
//    @Temporal(TemporalType.DATE)
//    private Date modified;


   
	private Date created = new Date();
	private Date updated = new Date();

	@PostUpdate
	public void upadte() {
		System.out.println("post upate happens");;
		this.updated = new Date(); }
	@PreUpdate
	public void setLastUpdate() { 
		System.out.println("upate happens");;
		this.updated = new Date(); }
	@PrePersist
	public void persist() {
		System.out.println("pre persist");
	}
	@PostPersist
	public void postpersist() {
		System.out.println("post persist");
	}
	
	@PreRemove
	public void delete() {
		System.out.println("pree delete");
	}
	@PostRemove
	public void postdelete() {
		System.out.println("post delete");
	}
	public String getFullname() {
		return this.getFirstName()+" "+this.getLastName();
	}
   public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}