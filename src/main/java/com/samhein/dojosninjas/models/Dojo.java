package com.samhein.dojosninjas.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="dojos")
public class Dojo {

	//attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Dojo name cannot be blank")
	@Size( min = 2, max = 200 )
	private String dojoName;
	
	// relationships
	@OneToMany(mappedBy="dojo", fetch = FetchType.LAZY)
	private List<Ninja> dojoNinjas;
	
	// created and updated at
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	 
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	// empty constructor
	public Dojo() {}
	
	// full constructor
	public Dojo(String dojoName) {
		this.dojoName=dojoName;
	}
	
	// getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDojoName() {
		return dojoName;
	}

	public void setDojoName(String dojoName) {
		this.dojoName = dojoName;
	}

	public List<Ninja> getDojoNinjas() {
		return dojoNinjas;
	}

	public void setDojoNinjas(List<Ninja> dojoNinjas) {
		this.dojoNinjas = dojoNinjas;
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

	//PrePersist and PreUpdate
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}
	
	
	
	
}
