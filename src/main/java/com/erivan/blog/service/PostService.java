package com.erivan.blog.service;

import java.util.List;

import com.erivan.blog.model.Post;

public interface PostService {

	List<Post> findAll();
	Post findById(long id);
	Post save(Post post);
	void deleteById(long id);
}
