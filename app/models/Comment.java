package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Comment extends Model{
	public String author;
	public Date postedAt;
	@Lob
	public String content;
	@ManyToOne
	public Post post;
	public Comment(String author, String content, Post post) {		
		this.author = author;
		this.content = content;
		this.post = post;
		this.postedAt = new Date();
	}
	
}
