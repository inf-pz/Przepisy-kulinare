package com.przepisy.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public void setPrzepisyService(PrzepisyService przepisyService){
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
	public String showUsers(Model model){
		List<User> users = usersService.getUsers();
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
		for (Comment comment : comments){
			commentsService.deleteComment(comment);
		}
		List<Przepis> przepisy = przepisyService.getPrzepisy(username);
		for (Przepis przepis : przepisy){
			commentsService.deleteComments(przepis);
			przepisyService.deletePrzepis(przepis);
		}
		usersService.deleteUser(user);		
		
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value = "/admin/przepis/delete", method = RequestMethod.GET)
	public String deletePrzepis(@RequestParam(value = "id", required = true) int id) {
		Przepis przepis = przepisyService.getPrzepis(id);
		String autor = przepis.getUser().getLogin();
		commentsService.deleteComments(przepis);
		przepisyService.deletePrzepis(przepis);
		
		return "redirect:/admin/user?login=" + autor;
	}
	
	@RequestMapping(value = "/admin/comment/delete", method = RequestMethod.GET)
	public String deleteComment(@RequestParam(value = "id", required = true) int id) {

		Comment comment = commentsService.getComment(id);
		String autor = comment.getAutor().getLogin();
		commentsService.deleteComment(comment);

		
		return "redirect:/admin/user?login=" + autor;
	}
}
