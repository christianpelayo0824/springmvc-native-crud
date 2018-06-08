package com.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.entity.User;
import com.webapp.services.UserServices;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserServices userServices;

	@RequestMapping(value = "/list")
	public ModelAndView showList() {
		ModelAndView mv = new ModelAndView();
		List<User> user = userServices.getAllUser();
		mv.addObject("USER", user);
		mv.setViewName("list");
		return mv;

	}

	@RequestMapping(value = "/showFormForAddUser")
	public ModelAndView showFormForAddUser() {
		ModelAndView mv = new ModelAndView();
		User user = new User();
		mv.addObject("user", user);
		mv.setViewName("user-form");
		return mv;
	}

	@PostMapping(value = "/addUserToDb")
	public ModelAndView showList(@ModelAttribute("user") User user) {
		ModelAndView mv = new ModelAndView();
		if (user.getFirstname() == "" || user.getLastname() == "" || user.getEmail() == "") {
			mv.setViewName("redirect:/user/showFormForAddUser");
		} else {
			userServices.createUser(user);
			mv.setViewName("redirect:/user/list");
		}

		return mv;
	}

	@GetMapping(value = "/showFormForUpdate")
	public ModelAndView showFormForUpdate(@RequestParam("userId") String userId) {
		ModelAndView mv = new ModelAndView();
		User user = userServices.getUserById(Integer.parseInt(userId));
		mv.addObject("user", user);
		mv.setViewName("user-form");
		return mv;
	}

	@GetMapping(value = "/delete")
	public ModelAndView deleteUser(@RequestParam("userId") String userId) {
		ModelAndView mv = new ModelAndView();
		System.out.println(userId);
		userServices.deleteUser(Integer.parseInt(userId));
		mv.setViewName("redirect:/user/list");
		return mv;
	}
}
