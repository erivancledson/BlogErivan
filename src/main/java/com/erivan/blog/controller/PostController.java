package com.erivan.blog.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erivan.blog.model.Post;
import com.erivan.blog.service.PostService;

@Controller
public class PostController {
	
	@Autowired
	PostService postService;
	

	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView getPosts() {
		ModelAndView mv = new ModelAndView("posts");
		List<Post> posts = postService.findAll();
		mv.addObject("posts", posts);
		return mv;
	}
	
	//lista de postagens do adm
	@RequestMapping(value="/postsListagem", method = RequestMethod.GET)
	public ModelAndView getPostsListagem() {
		ModelAndView mv = new ModelAndView("postsListagem");
		List<Post> posts = postService.findAll();
		mv.addObject("posts", posts);
		return mv;
	}
	
	@RequestMapping(value="/posts/{id}", method=RequestMethod.GET)
	public ModelAndView getPostDetails(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("postDetails");
		Post post = postService.findById(id);
		mv.addObject("post", post);
		return mv;
	}
	
	@RequestMapping(value="/newpost", method = RequestMethod.GET)
	public String getPostForm() {
		return "postForm";
	}
	
	@RequestMapping(value="/newpost", method=RequestMethod.POST)
	public String savePost(@Valid Post post, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
		
			return "redirect:/newpost";
		}
		
		post.setData(LocalDate.now());
		postService.save(post);
		return "redirect:/postsListagem";
	}
	
	//deletar post
	@RequestMapping("/deletarPost")
	public String deletarPost(long id) {
		Post post = postService.findById(id);
		postService.deleteById(id);
		return "redirect:/postsListagem";
		
	}
	

}
