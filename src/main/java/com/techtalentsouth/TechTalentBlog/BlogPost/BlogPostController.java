package com.techtalentsouth.TechTalentBlog.BlogPost;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogPostController {

	@Autowired
	private BlogPostRepository blogPostRepository;

	//list all blog posts
	@GetMapping("/")
	public ModelAndView index(BlogPost blogPost) {
		ModelAndView mv = new ModelAndView("blogpost/index");
		mv.addObject("blogPosts", blogPostRepository.findAll());
		return mv;
	}

	//handles the saving of the new blog post
	@PostMapping("/blog_posts/new")
	public ModelAndView createPost(BlogPost blogPost) {
		ModelAndView mv = new ModelAndView("blogpost/result");
		BlogPost post = blogPostRepository.save(new BlogPost(blogPost.getTitle(), blogPost.getAuthor(), blogPost.getBlogEntry()));
		mv.addObject("post", post);
		return mv;
	}
	
	//shows the form for creating a blog post
	@GetMapping("/blog_posts/new")
	public ModelAndView newPostForm(BlogPost blogPost) {
		ModelAndView mv = new ModelAndView("blogpost/create_post");
		return mv;
	}
	
	//shows the form for editing a blog post
	@GetMapping("/blog_posts/edit/{id}")
	public ModelAndView updatePostForm(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("blogpost/edit_post");
		Optional<BlogPost> post = blogPostRepository.findById(id);
		mv.addObject("blogPost", post);
		return mv;
	}
	
	//saves the edits to the blog post
	@PutMapping("/blog_posts/edit")
	public ModelAndView updatePost(BlogPost blogPost) {
		ModelAndView mv = new ModelAndView("redirect:/");
		blogPostRepository.save(blogPost);
		return mv;
	}
	
	//deletes blog post
	@DeleteMapping("/blog_posts/delete/{id}")
	public ModelAndView deletepost(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("redirect:/");
		blogPostRepository.deleteById(id);
		return mv;
	}

}