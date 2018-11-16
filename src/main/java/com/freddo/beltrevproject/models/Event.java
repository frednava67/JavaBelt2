package com.freddo.beltrevproject.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity 
@Table(name="events") 

public class Event { 
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 
    @Size(min = 1, max = 255)
    private String name;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull
    private Date eventDate;
    @Size(min = 1, max = 255)
    private String location;
    @Size(min = 2, max = 2)
    private String state;
    @Column(updatable=false) 
    private Date createdAt; 
    private Date updatedAt; 
    
    @ManyToMany(fetch = FetchType.LAZY) 
    @JoinTable( 
        name = "events_users",  
        joinColumns = @JoinColumn(name = "event_id"),  
        inverseJoinColumns = @JoinColumn(name = "user_id") 
    )
    private List<User> users;
    
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name="host_id") 
    private User host;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name="messager_id") 
    private User messager;
    
    @OneToMany(mappedBy="event", fetch = FetchType.LAZY) 
    private List<Message> messages;
    
    
    public Event() { 
    } 
      
	@PrePersist 
		protected void onCreate(){ 
		this.createdAt = new Date(); 
	} 
	@PreUpdate 
		protected void onUpdate(){ 
		this.updatedAt = new Date(); 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	public User getMessager() {
		return messager;
	}

	public void setMessager(User messager) {
		this.messager = messager;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	} 
}
