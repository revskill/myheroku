package models;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Post extends Model{
	public String title;
	public Date postedAt;
	@Lob
	public String content;
	@ManyToOne
	public User author;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL)
	public List<Comment> comments;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	public Set<Tag> tags;
	
	public Post(User author, String title, String content) {
		this.comments = new ArrayList<Comment>();
		this.tags = new TreeSet<Tag>();
		this.author = author;
		this.title = title;
		this.postedAt = new Date();
		this.content = content;
	}
	public Post addComment(String author, String content)
	{
		Comment newComment = new Comment(author, content, this).save();
		this.comments.add(newComment);
		return this;
	}
	public Post previous() {
	    return Post.find("postedAt < ? order by postedAt desc", postedAt).first();
	}
	 
	public Post next() {
	    return Post.find("postedAt > ? order by postedAt asc", postedAt).first();
	}
	public Post tagItWith(String name) {
	    tags.add(Tag.findOrCreateByName(name));
	    return this;
	}
	
	public static List<Post> findTaggedWith(String tag) {
	    return Post.find(
	        "select distinct p from Post p join p.tags as t where t.name = ?", tag
	    ).fetch();
	}
	public static List<Post> findTaggedWith(String... tags) {
	    return Post.find(
	        "select distinct p.id from Post p join p.tags as t where t.name in (:tags) group by p.id having count(t.id) = :size"
	    ).bind("tags", tags).bind("size", tags.length).fetch();
	}
	
	
}
