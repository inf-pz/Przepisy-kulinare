package com.przepisy.web.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.przepisy.web.dao.Comment;
import com.przepisy.web.dao.Przepis;
import com.przepisy.web.dao.User;
import com.przepisy.web.service.CommentsService;
import com.przepisy.web.service.PrzepisyService;
import com.przepisy.web.service.UsersService;

@Controller
public class AdminController {

	private UsersService usersService;
	private CommentsService commentsService;
	private PrzepisyService przepisyService;

	@Autowired
	public void setPrzepisyService(PrzepisyService przepisyService) {
		this.przepisyService = przepisyService;
	}

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@Autowired
	public void setCommentsService(CommentsService commentService) {
		this.commentsService = commentService;
	}

	@RequestMapping("/admin/users")
	public String showUsers(Model model) {
		List<User> users = usersService.getUsers();

		Collections.sort(users, new Comparator<User>() {
			@Override
			public int compare(final User object1, final User object2) {
				return object1.getLogin().compareTo(object2.getLogin());
			}
		});

		model.addAttribute("users", users);
		return "adminusers";
	}

	@RequestMapping(value = "/admin/user", method = RequestMethod.GET)
	public String showUser(@RequestParam(value = "login", required = true) String username, Model model) {
		User user = usersService.findUser(username);
		List<Comment> comments = commentsService.getComments(user);
		List<Przepis> przepisy = przepisyService.getPrzepisy(username);
		model.addAttribute("przepisy", przepisy);
		model.addAttribute("comments", comments);
		model.addAttribute("user", user);
		return "adminuser";
	}

	@RequestMapping(value = "/admin/user/delete", method = RequestMethod.GET)
	public String showDeleteUser(@RequestParam(value = "user", required = true) String username) {

		User user = usersService.findUser(username);
		List<Comment> comments = commentsService.getComments(user);
		for (Comment comment : comments) {
			commentsService.deleteComment(comment);
		}
		List<Przepis> przepisy = przepisyService.getPrzepisy(username);
		for (Przepis przepis : przepisy) {
			commentsService.deleteComments(przepis);
			przepisyService.deletePrzepis(przepis);
		}
		usersService.deleteUser(user);

		return "redirect:/admin/users";
	}

	@RequestMapping(value = "/admin/przepis/delete", method = RequestMethod.GET)
	public String deletePrzepis(@RequestParam(value = "id", required = true) int id,
			RedirectAttributes redirectAttributes) {
		Przepis przepis = przepisyService.getPrzepis(id);
		String autor = przepis.getUser().getLogin();
		commentsService.deleteComments(przepis);
		przepisyService.deletePrzepis(przepis);

		redirectAttributes.addAttribute("login", autor);
		return "redirect:/admin/user";
	}

	@RequestMapping(value = "/admin/comment/delete", method = RequestMethod.GET)
	public String deleteComment(@RequestParam(value = "id", required = true) int id,
			RedirectAttributes redirectAttributes,
			@RequestParam(value = "adminpanel", required = false) Boolean adminPanel) {

		Comment comment = commentsService.getComment(id);
		String autor = comment.getAutor().getLogin();
		int przepisid = comment.getPrzepis().getId();
		commentsService.deleteComment(comment);

		if (adminPanel != null) {
			redirectAttributes.addAttribute("login", autor);
			return "redirect:/admin/user";
		} else {
			redirectAttributes.addAttribute("id", przepisid);
			return "redirect:/przepis";
		}
	}

	@RequestMapping(value = "/admin/comment/edit", method = RequestMethod.GET)
	public String editComment(@RequestParam(value = "id", required = true) int id, Model model) {

		Comment comment = commentsService.getComment(id);
		model.addAttribute("commment", comment);

		return "admineditcomment";
	}

	@RequestMapping(value = "/admin/comment/edited", method = RequestMethod.POST)
	public String updateComment(RedirectAttributes redirectAttributes, Comment comment) {

		commentsService.saveComment(comment);
		int przepisid = comment.getPrzepis().getId();

		redirectAttributes.addAttribute("id", przepisid);
		return "redirect:/przepis";
	}

	@RequestMapping(value = "/admin/przepis/edit", method = RequestMethod.GET)
	public String editPrzepis(@RequestParam(value = "id", required = true) int id, Model model) {

		Przepis przepis = przepisyService.getPrzepis(id);
		model.addAttribute("przepis", przepis);

		return "admineditprzepis";
	}

	@RequestMapping(value = "/admin/przepis/edited", method = RequestMethod.POST)

	public String updatePrzepis(RedirectAttributes redirectAttributes, Przepis przepis) {

		przepisyService.savePrzepis(przepis);
		int przepisid = przepis.getId();

		redirectAttributes.addAttribute("id", przepisid);
		return "redirect:/przepis";
	}

}
