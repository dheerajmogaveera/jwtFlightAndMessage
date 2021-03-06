package com.cts.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cts.Services.MessageService;
import com.cts.Services.UserService;
import com.cts.model.Message;
import com.cts.model.User;

@Controller
public class ControllerMessage {

	@Autowired
	private UserService userService;
//	@Autowired
//	private MessageService ms;
	
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String showIndexPage(ModelMap m)
	{
		User u=new User();
		m.addAttribute("u",u);
		return "index";
	}
	
	@RequestMapping(value="/register",method = RequestMethod.GET)
	public String showRegisterPage(HttpSession session)
	{
		if(session.getAttribute("user")!=null)
			return "redirect:/UserChoice";
		return "register";
	}
	@RequestMapping(value="/UserChoice",method = RequestMethod.GET)
	public String UserChoice()
	{
		
		return "UserChoice";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String showLoginPage(HttpSession session)
	{
		if(session.getAttribute("user")!=null)
			return "redirect:/UserChoice";
		return "login";
	}
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("user") User user,BindingResult br)
	{
		ModelAndView mav=new ModelAndView();
		userService.register(user);
		mav.addObject("msg","You have sucessfully registered");
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user") User user,BindingResult br,HttpSession session)
	{
		System.out.println("Email:"+user.getMobileNumber()+" Password:"+user.getPassword());
		
		ModelAndView mav=new ModelAndView();
		if(userService.login(user.getMobileNumber(),user.getPassword())!=null)
		{
		session.setAttribute("user", userService.login(user.getMobileNumber(),user.getPassword()));
//		mav.addObject("mobileNumber",user.getMobileNumber());
		mav.setViewName("UserChoice");
		return mav;
		}
		else
		{
			mav.setViewName("login");
			return mav;
		}	
	}
//	@RequestMapping(value="/inbox",method = RequestMethod.GET)
//	public String inbox()
//	{
//		return "Inbox";
//	}
	@RequestMapping(value="/inbox",method = RequestMethod.GET)
	public ModelAndView inbox(HttpSession session)
	{
		ModelAndView mav=new ModelAndView();
		
		User u = (User)session.getAttribute("user");
		if(u!=null)
		{
			if(u.getReceiverMessage().size()==0) {
				mav.addObject("msg","No Message in Inbox");
				System.out.println("NO messages");
				return mav;
			}
			mav.addObject("flist",userService.inbox(u));
			mav.setViewName("Inbox");
			System.out.println("Some messages");
			return mav;
		}
		else {
			mav.setViewName("login");
			return mav;
		}
		
	}
	
	@RequestMapping(value="/sentMessage",method = RequestMethod.GET)
	public ModelAndView sentMessage(HttpSession session)
	{
		ModelAndView mav=new ModelAndView();
		
		User u = (User)session.getAttribute("user");
		if(u!=null)
		{
			if(u.getSenderMessage().size()==0) {
				mav.addObject("msg","No Message sent");
				System.out.println("NO messages");
				return mav;
			}
			mav.addObject("flist",userService.sentMessage(u));
			mav.setViewName("SentMessages");
			System.out.println("Some messages sent");
			return mav;
		}
		else {
			mav.setViewName("login");
			return mav;
		}
		
	}
//	@RequestMapping(value="/sendMessage",method = RequestMethod.GET)
//	public String sendMessages(HttpSession session)
//	{
//		if(session.getAttribute("user")!=null)
//			return "redirect:/UserChoice";
//		return "SendMessages";
//	}
//	@RequestMapping(value="/sendMessage",method = RequestMethod.POST)
//	public ModelAndView sendMessage(HttpSession session)
//	{
//		ModelAndView mav=new ModelAndView();
//		
//		User u = (User)session.getAttribute("user");
//		if(userService.login(u.getMobileNumber())!=null)
//		{
////		if(u!=null)
////		{
////			if(u.getSenderMessage().size()==0) {
////				mav.addObject("msg","No Message sent");
////				System.out.println("NO messages");
////				return mav;
////			}
////			mav.addObject("flist",userService.sentMessage(u));
////			mav.setViewName("SentMessages");
////			System.out.println("Some messages sent");
////			return mav;
////		}
////		else {
////			mav.setViewName("login");
////			return mav;
////		}
////		
	}

