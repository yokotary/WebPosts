package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.PostsForm;
import com.example.demo.model.Posts;
import com.example.demo.service.PostsService;

@Controller
@RequestMapping("posts")
public class PostsController {

	@Autowired
	private PostsService service;

	@GetMapping("")
	public String index(Model model) {
		List<Posts> list = service.findAll();
		model.addAttribute("list", list);
		return "posts/index";
	}

	/**
	 * 削除確認
	 * @param id
	 * @param model
	 * @return
	 */
	@PostMapping("/delete/confirm/{id}")
	public String deleteConfirm(@PathVariable Long id, Model model) {
		Optional<Posts> optional = service.findById(id);
		if (optional.isPresent()) {
			//あったら
			model.addAttribute("posts", optional.get());
			return "posts/delete/confirm";
		} else {
			return "redirect:/posts/";
		}
	}

	/**
	 * 削除
	 * @param id
	 * @param model
	 * @return
	 */
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		service.deleteId(id);
		return "redirect:/posts/";
	}

	/**
	 * 投稿フォーム
	 * @param form
	 * @param model
	 * @return
	 */
	@PostMapping("/create/form")
	public String createForm(@ModelAttribute PostsForm form, Model model) {
		model.addAttribute(form);
		return "posts/create/form";
	}

	/**
	 * 投稿確認画面
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("/create/confirm")
	public String createConfirm(@ModelAttribute PostsForm form,
			BindingResult result, Model model) {
		if (result.hasErrors())
			return createForm(form, model);
		return "posts/create/confirm";
	}

	@PostMapping("/create/")
	public String create(@ModelAttribute PostsForm form, Model model) {
		Posts posts = new Posts();
		posts.setName(form.getName());
		posts.setPost(form.getPost());
		service.save(posts);
		return "redirect:/posts/";
	}
}
