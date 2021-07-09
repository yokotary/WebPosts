package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Posts;
import com.example.demo.repository.PostsRepository;

@Service
public class PostsService {

	@Autowired
	private PostsRepository repository;

	/*
	 * 全件検索
	 * @return 全書き込み表示 List<Posts>
	 */
	public List<Posts> findAll() {
		return repository.findAll();
	}

	/**
	 * IDを指定してレコードを読み込む
	 * @param id Long
	 * @return 指定したレコード Optional<User>
	 */
	public Optional<Posts> findById(Long id) {
		return repository.findById(id);
	}

	/**
	 * IDを指定してレコードを削除
	 * @param id
	 */
	public void deleteId(Long id) {
		repository.deleteById(id);
	}

	/**
	 * 追加・修正する
	 * @param posts
	 */
	public void save(Posts posts) {
		repository.save(posts);
	}

}
