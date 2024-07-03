package com.NgocHieu.Buoi22.controller;

import com.NgocHieu.Buoi22.model.Blog;
import com.NgocHieu.Buoi22.model.Comment;
import com.NgocHieu.Buoi22.service.BlogService;
import com.NgocHieu.Buoi22.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @GetMapping
    public String getAllComments(Model model) {
        List<Comment> comments = commentService.getAllComments();
        List<Blog> blogs = blogService.getAllBlogs();
        model.addAttribute("comments", comments);
        model.addAttribute("blogs", blogs);

        return "/comments/admin-comments";
    }

    @PostMapping("/toggle/{commentId}")
    public String toggleComment(@PathVariable int commentId) {
        Comment comment = commentService.getCommentById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.toggleHide();
        commentService.updateComment(comment);
        return "redirect:/comments";
    }
}
