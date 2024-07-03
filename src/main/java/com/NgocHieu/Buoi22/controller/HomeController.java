package com.NgocHieu.Buoi22.controller;


import com.NgocHieu.Buoi22.model.Blog;
import com.NgocHieu.Buoi22.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private BlogService blogService;
    @RequestMapping("/")
    public String welcome(Model model) {
        List<Blog> blogs = blogService.getAllBlogs();
        model.addAttribute("blogs", blogs);
        return "/index1";
    }
}
