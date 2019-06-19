package com.formsub.exam;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ceritificate {

	@Id
	@GeneratedValue
	private Long id;
	private String cerificateName;
	
	@ManyToOne
    @JoinColumn(name = "student_id")
	private Student student;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCerificateName() {
		return cerificateName;
	}

	public void setCerificateName(String cerificateName) {
		this.cerificateName = cerificateName;
	}

	public Ceritificate() {

	}

	public Ceritificate(Long id, String cerificateName) {
		super();
		this.id = id;
		this.cerificateName = cerificateName;
	}

}
