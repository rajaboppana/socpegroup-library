/**
 * 
 */
package com.scopegroup.library.pojo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Raja
 *
 * Scope Group test
 */
@Entity
@Table(name = "publication")
public class Publication {
	
	@Column(name = "title")
	private String title;
	@Id
	@Column(name = "isbn")
	private String isbn;
	@Column(name = "description")
	private String description;
	@Column(name = "date")
	private String date;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "publication_author", 
    joinColumns = { @JoinColumn(name = "isbn", referencedColumnName = "isbn") }, 
    inverseJoinColumns = { @JoinColumn(name = "email", referencedColumnName = "email") })
	private Set<Author> authors = new HashSet<>();
	
	
	
	/**
	 * @param id
	 * @param title
	 * @param isbn
	 * @param description
	 * @param date
	 * @param authors
	 */
	public Publication(String title, String isbn, String description, String date, Set<Author> authors) {
		
		this.title = title;
		this.isbn = isbn;
		this.description = description;
		this.date = date;
		this.authors = authors;
	}
	
	
	/**
	 * 
	 */
	public Publication() {
		// TODO Auto-generated constructor stub
	}

	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Set<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
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
        if (obj instanceof Publication) 
            return isbn.equals(((Publication)obj).isbn); 
        else 
            return false; 
    } 

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.isbn);
        return hash;
    }
    
    
	@Override
	public String toString() {
		return "Publication [title=" + title + ", isbn=" + isbn + ", description=" + description
				+ ", date=" + date + ", authors=" + authors + ", getTitle()=" + getTitle()
				+ ", getIsbn()=" + getIsbn() + ", getAuthors()=" + getAuthors() + ", getDescription()="
				+ getDescription() + ", getDate()=" + getDate() + ", hashCode()=" + hashCode() + ", getClass()="
				+ getClass() + ", toString()=" + super.toString() + "]";
	}
	

}
