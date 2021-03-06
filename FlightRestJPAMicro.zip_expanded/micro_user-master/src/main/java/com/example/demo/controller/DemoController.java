package com.example.demo.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Booking;
import com.example.demo.model.Flight;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class DemoController {

	@Autowired
	UserService us;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showIndexPage(ModelMap m, HttpSession session) {
		if (session.getAttribute("token") != null)
			return "redirect:/UserHome";
		User u = new User();
		m.addAttribute("u", u);
		return "index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegisterPage(HttpSession session) {
		if (session.getAttribute("token") != null)
			return "redirect:/UserHome";
		return "register";
	}

	@RequestMapping(value = "/UserHome", method = RequestMethod.GET)
	public String UserHome() {

		return "UserHome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(HttpSession session) {
		if (session.getAttribute("token") != null)
			return "redirect:/UserHome";
		return "login";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(HttpSession session) {
		if (session.getAttribute("token") == null)
			return "redirect:/login";
		return "SearchFlight";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchFlight(@ModelAttribute("flight") Flight flight, BindingResult br, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if (session.getAttribute("token") == null) {
			mav.addObject("error", "Please login first");
			mav.setViewName("login");
			return mav;
		}
		
		Flight fl = us.searchFlights(flight.getSource(), flight.getDestination(),(String) session.getAttribute("token"));

		if (fl != null) {
			mav.addObject("flist", fl);
			mav.setViewName("SearchFlight");
			return mav;
		} else {
			mav.addObject("msg", "No Flights Available");
			mav.setViewName("SearchFlight");

			return mav;
		}

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		User u2 =us.register(user);
		mav.addObject("msg", "You have sucessfully registered");
		mav.setViewName("index");
		return mav;
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user") User user, BindingResult br, HttpSession session) {
		System.out.println("Email:" + user.getUname() + " Password:" + user.getPassword());
		
		ModelAndView mav = new ModelAndView();
		String token = us.login(user.getUname(), user.getPassword());
		if (token != null) {
			mav.addObject("email", user.getUname());
			// mav.addObject("id",us.login(user.getEmail(),user.getPassword()).getId());
			session.setAttribute("token", token);
			session.setAttribute("user", us.getUserByUname(user.getUname(), (String) token));
			mav.setViewName("UserHome");
			return mav;
		} else {
			mav.addObject("msg", "Invalid Email Id/Password");
			mav.setViewName("login");
			return mav;
		}

	}

	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String bookticket(HttpSession session) {
		if (session.getAttribute("token") == null)
			return "redirect:/login";
		return "BookTicket";
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public ModelAndView bookTicket(@ModelAttribute("id") int flightId, @ModelAttribute("noOfSeats") int nof,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if (session.getAttribute("token") == null) {
			mav.addObject("error", "Booking can only be acessed after logging in");
			mav.setViewName("login");
			return mav;
		}
		User u=(User)session.getAttribute("user");
		Booking booking = us.bookTicket(u.getId(),nof,flightId,(String) session.getAttribute("token"));
		mav.addObject("amt", booking.getTotalAmount());
		mav.addObject("msg", "Ticket Booked Sucessfully");
		mav.setViewName("BookTicket");
		return mav;

	}

	@RequestMapping(value = "/cancelFlight", method = RequestMethod.GET)
	public String cancelbooking(HttpSession session) {
		if (session.getAttribute("token") == null)
			return "redirect:/login";
		return "CancelBooking";
	}

	@RequestMapping(value = "/cancelFlight", method = RequestMethod.POST)
	public ModelAndView cancelBooking(@ModelAttribute("id") int id, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		if (session.getAttribute("token") == null) {
			mav.addObject("error", "Bookings cancellation can be accessed only after logging in");
			mav.setViewName("login");
			return mav;
		}
		String msg = us.cancelBooking(id,(String)session.getAttribute("token"));
		mav.addObject("msg", "Booking with id " + id + " cancelled successfully");
		mav.setViewName("CancelBooking");
		return mav;
	}

//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "redirect:/index";
//	}

}
