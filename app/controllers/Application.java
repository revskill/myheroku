package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
    	// get first post
    	Post frontPost = Post.find("order by postedAt desc").first();    	    
    	// find 10 latest posts desc
    	List<Post> posts = Post.find("order by postedAt desc").from(1).fetch(10);
    	// render both
        render(frontPost, posts);
    }

}