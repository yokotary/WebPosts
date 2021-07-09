package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostsForm {

	private Long id;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 10)
	private String name;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 150)
	private String post;

	@NotNull
	@NotBlank
	private String day;
}
