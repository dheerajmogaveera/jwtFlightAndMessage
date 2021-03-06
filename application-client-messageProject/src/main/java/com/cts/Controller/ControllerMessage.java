package com.cts.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cts.Services.UserService;
import com.cts.model.JwtRequest;
import com.cts.model.Message;
import com.cts.model.User;

@Controller
public class ControllerMessage {

	@Autowired
	private UserService userService;
//	@Autowired
//	private MessageService ms;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showIndexPage(ModelMap m) {
		User u = new User();
		m.addAttribute("u", u);
		return "index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegisterPage(HttpSession session) {
		if (session.getAttribute("user") != null)
			return "redirect:/UserChoice";
		return "register";
	}

	@RequestMapping(value = "/UserChoice", method = RequestMethod.GET)
	public String UserChoice() {

		return "UserChoice";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(HttpSession session) {
		if (session.getAttribute("user") != null)
			return "redirect:/UserChoice";
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("user") User user, BindingResult br) {
		System.out.println(user);
		ModelAndView mav = new ModelAndView();
		userService.register(user);
		mav.addObject("msg", "You have sucessfully registered");
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("jwtreq") JwtRequest jr, BindingResult br, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		String token = userService.login(jr);
		if (token != null) {
			session.setAttribute("token", token);
			session.setAttribute("user", userService.getUserByUname(jr.getUsername(), (String) token));
			mav.setViewName("UserChoice");
			return mav;
		} else {
			mav.setViewName("login");
			return mav;
		}
	}

	@RequestMapping(value = "/inbox", method = RequestMethod.GET)
	public ModelAndView inbox(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		if (session.getAttribute("user") == null) {
			mav.setViewName("index");
			return mav;
		}

		String token = (String) session.getAttribute("token");
		if (token != null) {
			User u = (User) session.getAttribute("user");
			String listMess = userService.inbox(u.getId(), token);
			if (listMess.equalsIgnoreCase("<ul><li></li></ul>")) {
				mav.addObject("msg", "No Message in Inbox");
				mav.setViewName("Inbox");
				System.out.println("NO messages");
				return mav;
			}
			mav.addObject("flist", listMess);
			mav.setViewName("Inbox");
			System.out.println("Some messages");
			return mav;
		} else {
			mav.setViewName("login");
			return mav;
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutUser(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	@RequestMapping(value = "/sentMessage", method = RequestMethod.GET)
	public ModelAndView sentm(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		if (session.getAttribute("user") == null) {
			mav.setViewName("index");
			return mav;
		}

		String token = (String) session.getAttribute("token");
		if (token != null) {
			User u = (User) session.getAttribute("user");
			String listMess = userService.sentMess(u.getId(), token);
			if (listMess.equalsIgnoreCase("<ul><li></li></ul>")) {
				mav.addObject("msg", "No Message sent");
				mav.setViewName("SentMessages");
				System.out.println("NO messages");
				return mav;
			}
			mav.addObject("flist", listMess);
			mav.setViewName("SentMessages");
			System.out.println("Some messages");
			return mav;
		} else {
			mav.setViewName("login");
			return mav;
		}

	}

	@RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
	public String sendMessages(HttpSession session) {
		if (session.getAttribute("user") == null)
			return "redirect:/index";
		return "SendMessages";
	}

	@RequestMapping(value = "/send-message", method = RequestMethod.POST)	@ResponseBody
	public ModelAndView sendMessage(@ModelAttribute Message msg,  HttpSession session) {
		ModelAndView mav = new ModelAndView();

		if (session.getAttribute("user") == null) {
			mav.setViewName("index");
			return mav;
		}
		String token = (String) session.getAttribute("token");
		User u = (User) session.getAttribute("user");
		msg.setSenderid(u.getId());
		userService.sendMessage(msg,token);
		mav.addObject("msg", "Message Sent");
		mav.setViewName("SendMessages");
		return mav;
	}

	
}
