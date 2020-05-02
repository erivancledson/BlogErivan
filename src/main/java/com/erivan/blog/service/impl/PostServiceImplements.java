package com.erivan.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.erivan.blog.model.Post;
import com.erivan.blog.repository.PostRepository;
import com.erivan.blog.service.PostService;


@Service
public class PostServiceImplements implements PostService{
	
	@Autowired
	PostRepository postRepository;

	@Override
	public List<Post> findAll() {
	
		return postRepository.findAll();
	}

	@Override
	public Post findById(long id) {
		
		return postRepository.findById(id).get();
	}

	@Override
	public Post save(Post post) {
		
		return postRepository.save(post);
	}

	@Override
	public void deleteById(long id) {
		
		 postRepository.deleteById(id);
	}
	
	  

}
