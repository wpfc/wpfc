package cn.edu.ntu.wpfc.entity;

import java.io.Serializable;

public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private String contact;
	
	private String telephone;
	
	private String photo;
	
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
