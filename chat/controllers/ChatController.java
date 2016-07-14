package fr.insalyon.telecom.chat.controllers;

import fr.insalyon.telecom.chat.model.Post;
import fr.insalyon.telecom.chat.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ChatController {


    @Autowired
    private MessageBoard messageBoard;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private GistService gistService;

    @RequestMapping("/")
    public ModelAndView index(HttpSession session) {
        if (session.getAttribute("login") == null) {
            return new ModelAndView("login");
        }
        return new ModelAndView("main")
                .addObject("posts", messageBoard.getPosts())
                .addObject("user", session.getAttribute("login"));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(
            HttpSession session,
            @RequestParam("login") String login,
            @RequestParam("password") String password) {
        if(authenticationService.authenticate(login,password))
            session.setAttribute("login", login);
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String post(HttpSession session, @RequestParam("message") String message) {
        messageBoard.post(new Post(session.getAttribute("login").toString(), message));
        return "redirect:/";
    }

    @RequestMapping("/publish-logs")
    public String publishLogs(HttpSession session){
        GistResponse gistResponse = gistService.publish(session.getAttribute("login").toString(), messageBoard);

        messageBoard.post(new Post(session.getAttribute("login").toString(), "published the log to "+ gistResponse.getUrl()));
        return "redirect:/";
    }

}