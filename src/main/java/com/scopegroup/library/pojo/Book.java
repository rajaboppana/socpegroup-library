/**
 * 
 */
package com.scopegroup.library.pojo;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Raja
 *
 * Scope Group test
 */

@Entity
@Table(name = "book")
public class Book {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "email")
	private String email;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "type")
	private Character type;
	@Column(name = "isbn", unique = true)
	private String isbn;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "date")
	private String date;
	
		
	
	/**
	 * @param id
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param type
	 * @param isbn
	 * @param title
	 * @param description
	 * @param date
	 */
	
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", type="
				+ type + ", isbn=" + isbn + ", title=" + title + ", description=" + description + ", date=" + date
				+ "]";
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Character getType() {
		return type;
	}
	public void setType(Character type) {
		this.type = type;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
    public boolean equals(Object obj) { 
        if (obj instanceof Book) 
            return isbn.equals(((Book)obj).isbn); 
        else 
            return false; 
    } 

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.isbn);
        return hash;
    }
	
	

}
