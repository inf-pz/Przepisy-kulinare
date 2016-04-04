package com.przepisy.web.controllers;

import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import com.przepisy.web.dao.Report;
import com.przepisy.web.dao.User;
import com.przepisy.web.service.CommentsService;
import com.przepisy.web.service.PrzepisyService;
import com.przepisy.web.service.ReportsService;
import com.przepisy.web.service.UsersService;

@Controller
public class AdminController {

	private UsersService usersService;
	private CommentsService commentsService;
	private PrzepisyService przepisyService;
	private ReportsService reportsService;

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
	@Autowired
	public void setReportsService(ReportsService reportsService) {
		this.reportsService = reportsService;
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
			reportsService.deleteReports(przepis);
		}
		usersService.deleteUser(user);

		return "redirect:/admin/users";
	}

	@RequestMapping(value = "/admin/przepis/delete", method = RequestMethod.GET)
	public String deletePrzepis(@RequestParam(value = "id", required = true) int id,
			RedirectAttributes redirectAttributes,
			@RequestParam(value = "adminpanel", required = false) Boolean adminPanel) {
		Przepis przepis = przepisyService.getPrzepis(id);
		String autor = przepis.getUser().getLogin();
		commentsService.deleteComments(przepis);
		reportsService.deleteReports(przepis);
		przepisyService.deletePrzepis(przepis);
		redirectAttributes.addAttribute("login", autor);
		
		if (adminPanel != null){
			return "redirect:/admin/reports";
		}
		return "redirect:/przepisy";
	}
	
	@RequestMapping(value = "/admin/comments", method = RequestMethod.GET)
	public String showFlaggedComments(Model model){
		List<Comment> comments = commentsService.getFlaggedComments();
		model.addAttribute("comments", comments);
		return "adminflaggedcomments";
	}

	@RequestMapping(value = "/admin/comment/delete", method = RequestMethod.GET)
	public String deleteComment(@RequestParam(value = "id", required = true) int id,
			RedirectAttributes redirectAttributes,
			@RequestParam(value = "adminpanel", required = false) Boolean adminPanel) {

		Comment comment = commentsService.getComment(id);
		String autor = comment.getAutor().getLogin();
		int przepisid = comment.getPrzepis().getId();
		commentsService.deleteComment(comment);

		if (adminPanel == null) {
			redirectAttributes.addAttribute("id", przepisid);
			return "redirect:/przepis";
			
		} else {
			if (adminPanel == true) {
				redirectAttributes.addAttribute("login", autor);
				return "redirect:/admin/user";
			} else {
				return "adminflaggedcomments";
			}
		}
	}



	@RequestMapping(value = "/admin/comment/flag", method = RequestMethod.GET)
	public String flagComment(@RequestParam(value = "id", required = true) int id,
			RedirectAttributes redirectAttributes, Model model) {

		Comment comment = commentsService.getComment(id);
		commentsService.flagComment(comment);

		model.addAttribute("id", comment.getPrzepis().getId());
		return "commentflagged";
	}

	@RequestMapping(value = "/admin/comment/unflag", method = RequestMethod.GET)
	public String unFlagComment(@RequestParam(value = "id", required = true) int id,
			RedirectAttributes redirectAttributes) {

		Comment comment = commentsService.getComment(id);
		commentsService.unflagComment(comment);

		return "redirect:/admin/comments";
	}

	@RequestMapping(value = "/admin/comment/edit", method = RequestMethod.GET)
	public String editComment(@RequestParam(value = "id", required = true) int id, Model model,
			@RequestParam(value = "adminpanel", required = false) Boolean adminPanel) {

		Comment comment = commentsService.getComment(id);
		model.addAttribute("comment", comment);
		if (adminPanel != null){
			model.addAttribute("adminpanel", adminPanel);
		}

		return "admineditcomment";
	}
	
	@RequestMapping(value = "/admin/comment/edited", method = RequestMethod.POST)
	public String updateComment(RedirectAttributes redirectAttributes, Comment comment,
			@RequestParam(value = "adminpanel", required = false) Boolean adminPanel) {

		Comment oldComment = commentsService.getComment(comment.getId());
		oldComment.setText(comment.getText());
		commentsService.saveComment(oldComment);
		int przepisid = oldComment.getPrzepis().getId();

		if (adminPanel != null){
			return "redirect:/admin/comments";
		}
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

		int przepisid = przepis.getId();
		Przepis przepisOld = przepisyService.getPrzepis(przepis.getId());
		przepisOld.setName(przepis.getName());
		przepisOld.setSkladniki(przepis.getSkladniki());
		przepisOld.setText(przepis.getText());
		przepisOld.setCzas(przepis.getCzas());

		przepisyService.savePrzepis(przepisOld);

		redirectAttributes.addAttribute("id", przepisid);
		return "redirect:/przepis";
	}
	
	@RequestMapping(value = "/admin/przepis/flag", method = RequestMethod.GET)
	public String showFlagPrzepis(@RequestParam(value = "id", required = true) int id, Model model) {

		Przepis przepis = przepisyService.getPrzepis(id);
		model.addAttribute("przepis", przepis);
		model.addAttribute("report", new Report());

		return "flagprzepis";
	}
	
	
	@RequestMapping(value = "admin/przepis/flagged", method = RequestMethod.POST)
	public String flagPrzepis(Report report, RedirectAttributes redirectAttributes, Principal principal,
			Model model,
			@RequestParam(value = "id", required = true) int id){
		User user = usersService.findUser(principal.getName());
		Przepis przepis = przepisyService.getPrzepis(id);
		
		
		report.setUser(user);
		report.setPrzepis(przepis);
		report.setData(new Date());
		reportsService.createReport(report);

		model.addAttribute("id", report.getPrzepis().getId());
		return "przepisflagged";
	}
	
	@RequestMapping(value = "/admin/reports", method = RequestMethod.GET)
	public String showFlaggedPrzepisy(Model model){
		List<Report> reports = reportsService.getReports();
		model.addAttribute("reports", reports);
		return "adminreports";
	}
	
	@RequestMapping(value = "/admin/report/delete", method = RequestMethod.GET)
	public String unFlagPrzepis(@RequestParam(value = "id", required = true) int id,
			RedirectAttributes redirectAttributes) {

		Report report = reportsService.getReport(id);
		reportsService.deleteReport(report);

		return "redirect:/admin/reports";
	}

}