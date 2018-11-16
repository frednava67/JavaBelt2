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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity 
@Table(name="ideas") 
public class Idea { 
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    @Size(min = 1, max = 255)
    private String content;
    
    @Column(updatable=false) 
    private Date createdAt; 
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name="creator_id") 
    private User creator; 
    
    @ManyToMany(fetch = FetchType.LAZY) 
    @JoinTable( 
        name = "ideas_users",  
        joinColumns = @JoinColumn(name = "idea_id"),  
        inverseJoinColumns = @JoinColumn(name = "user_id") 
    )
    private List<User> likers;
     
    public Idea() { 
         
    } 
     
    public Idea(String content) { 
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

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<User> getLikers() {
		return likers;
	}

	public void setLikers(List<User> likers) {
		this.likers = likers;
	}
}
