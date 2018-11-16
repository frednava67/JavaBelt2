package com.freddo.beltrevproject.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity 
@Table(name="messages") 
public class Message { 
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    @Size(min=1, max = 512)
    private String content; 
    @Column(updatable=false) 
    private Date createdAt; 
    private Date updatedAt; 

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name="messager_id") 
    private User messager;
    
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name="event_id") 
    private Event event;
     
    public Message() { 
         
    } 
     
    public Message(String content) { 
    	this.content = content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public User getMessager() {
		return messager;
	}

	public void setMessager(User messager) {
		this.messager = messager;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	} 
}
