package models;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Post extends Model{
	public String title;
	public Date postedAt;
	@Lob
	public String content;
	@ManyToOne
	public User author;
	
	public Post(String title, Date postedAt, String content) {
		super();
		this.title = title;
		this.postedAt = postedAt;
		this.content = content;
	}
	
}
