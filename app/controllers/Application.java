package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {
	@Before
	static void addDefaults() {
	    renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
	    renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
	}
    public static void index() {
    	// get first post
    	Post frontPost = Post.find("order by postedAt desc").first();    	    
    	// find 10 latest posts desc
    	List<Post> olderPosts = Post.find("order by postedAt desc").from(1).fetch(10);
    	// render both
        render(frontPost, olderPosts);
    }
    public static void show(Long id) {
    	Post post = Post.findById(id);
    	render(post);
    }
}