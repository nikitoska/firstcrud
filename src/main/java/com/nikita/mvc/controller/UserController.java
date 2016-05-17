package com.nikita.mvc.controller;

import com.nikita.mvc.model.User;
import com.nikita.mvc.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private UserService userService;



    @Autowired(required = true)
    @Qualifier("userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(@RequestParam(value = "page",required = false) Long page, Model model) {
        if(null == page) {
            page = 1L;
        }
        model.addAttribute("user", new User());
        model.addAttribute("searchuser", new User());
        model.addAttribute("listUsers", this.userService.getUsers(page));
        model.addAttribute("page", page);
        return "users";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        if(user.getId() == 0) {
            this.userService.addUser(user);
        } else {
            this.userService.updateUser(user);
        }

        return "redirect:/";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        this.userService.removeUser(id);
        return "redirect:/";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, @RequestParam(value = "page", required = false) Long page, Model model) {
        if(null == page) {
            page = 1L;
        }

        model.addAttribute("user", this.userService.getUser(id));
        model.addAttribute("searchuser", new User());
        model.addAttribute("listUsers", this.userService.getUsers(page));
        model.addAttribute("page", page);
        return "users";
    }

    @RequestMapping(value = "searchresults", method = RequestMethod.POST)
    public String searchResults(@ModelAttribute("searchuser") User user, Model model) {
        List<User> searchResult = this.userService.getUsers(user.getName());
        model.addAttribute("listUsers", searchResult);
        return "searchresults";
    }
}