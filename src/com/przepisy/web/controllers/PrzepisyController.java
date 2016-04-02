package com.przepisy.web.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.przepisy.web.dao.Comment;
import com.przepisy.web.dao.Przepis;
import com.przepisy.web.dao.User;
import com.przepisy.web.service.CommentsService;
import com.przepisy.web.service.PrzepisyService;
import com.przepisy.web.service.UsersService;

@Controller
public class PrzepisyController {

	private static Logger logger = Logger.getLogger(HomeController.class);

	private PrzepisyService przepisyService;
	private UsersService usersService;
	private CommentsService commentsService;

	
	@Autowired
	public void setPrzepisyService(PrzepisyService przepisyService) {
		this.przepisyService = przepisyService;
	}

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@Autowired
	public void setCommnetsService(CommentsService commentsService) {
		this.commentsService = commentsService;
	}

	@RequestMapping(value = "/przepisy", method = RequestMethod.GET)
	public String showPrzepisy(@RequestParam(value = "user", required = false) String user, Model model) {

		
		List<Przepis> przepisy;
		if (user == null) {
			przepisy = przepisyService.getPrzepisy();
		}
		else {
			przepisy = przepisyService.getPrzepisy(user);
			model.addAttribute("user", user);
		}

		model.addAttribute("przepisy", przepisy);
		
		
		return "przepisy";
	}
	
	@RequestMapping(value = "/szukaj", method = RequestMethod.GET)
	public String showResults(@RequestParam(value = "query", required = true) String query, Model model) {

		
		List<Przepis> przepisy = przepisyService.searchForPrzepis(query);

		model.addAttribute("przepisy", przepisy);
		
		
		return "przepisy";
	}

	@RequestMapping(value = "/przepis", method = RequestMethod.GET)
	public String showPrzepis(@RequestParam("id") int id, Model model) {
		Przepis przepis = przepisyService.getPrzepis(id);
		model.addAttribute("przepis", przepis);
		Comment comment = new Comment();
		model.addAttribute("comment", comment);
		List<Comment> comments = commentsService.getComments(przepis);
		model.addAttribute("comments", comments);

		return "przepis";

	}

	@RequestMapping(value = "/nowycomment", method = RequestMethod.POST)
	public String doCreatePrzepis(RedirectAttributes redirectAttributes, Comment comment, Principal principal,
			@RequestParam(value = "id", required = true) int id) {

		User user = usersService.findUser(principal.getName());
		comment.setAutor(user);
		Przepis przepis = przepisyService.getPrzepis(id);
		comment.setPrzepis(przepis);
		commentsService.createComment(comment);
		comment.setData(new Date());

		redirectAttributes.addAttribute("id", id);
		return "redirect:/przepis";

	}

	@RequestMapping("/nowyprzepis")
	public String createPrzepis(Model model) {

		model.addAttribute("przepis", new Przepis());
		return "createprzepis";
	}

	/*
	 * boolean tryParseInt(String value) { try { Integer.parseInt(value); return
	 * true; } catch (NumberFormatException e) { return false; } }
	 */

	boolean validate(Przepis przepis) {
		// String s = Integer.toString(przepis.getMember_id()) + "";
		if (przepis.getName().length() == 0 || przepis.getText().length() == 0
		// || tryParseInt(s) == false || s.length() == 0 || s == null
		) {
			return false;
		} else
			return true;
	}

	@RequestMapping(value = "/docreateprzepis", headers = "content-type=multipart/*", method = RequestMethod.POST)
	public String doCreatePrzepis(Model model, Przepis przepis, BindingResult result, Principal principal,
			@RequestParam(value = "photo", required = false) MultipartFile file) {
		przepis.setUser(usersService.findUser(principal.getName()));
		przepis.setData(new Date());

		if (!(file.getSize() == 0)) {
			try {
				przepis.setPhoto(file.getBytes());

			} catch (Exception re) {
				logger.info(re.getMessage());
				return "createprzepis";
			}
		}
		else {
			
			try {
				ClassPathResource cpr = new ClassPathResource("default.png");
				InputStream is = cpr.getInputStream();
				przepis.setPhoto(IOUtils.toByteArray(is));
//				logger.info("czy photo jest rózne od null");
//				logger.info(przepis.getPhoto() != null);
			} catch (IOException re) {
				logger.info(re.getMessage());
				return "createprzepis";
			}

		}

		przepisyService.createPrzepis(przepis);

		return "przepisdodany";
	}

	@RequestMapping("/mojeprzepisy")
	public String userPrzepisy(Model model, Principal principal) {
		String username = principal.getName();
		 return "redirect:/przepisy?user=" + username;
	}

	@RequestMapping(value = "/getPhoto/{id}")
	public void getUserImage(HttpServletResponse response, @PathVariable("id") int id) throws IOException {

		response.setContentType("image/jpeg");
		byte[] buffer = przepisyService.getPrzepis(id).getPhoto();
		InputStream in1 = new ByteArrayInputStream(buffer);
		IOUtils.copy(in1, response.getOutputStream());
		
	}
}
