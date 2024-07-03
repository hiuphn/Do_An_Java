package com.NgocHieu.Buoi22.controller;
import com.NgocHieu.Buoi22.service.CategoryService;
import com.NgocHieu.Buoi22.service.LoaiThuocService;
import com.NgocHieu.Buoi22.model.Category;
import com.NgocHieu.Buoi22.model.LoaiThuoc;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    final CategoryService loaiThuocService;

    @GetMapping
    public  String ShowallLoaiThuoc(@NotNull Model model){
        model.addAttribute("categories",loaiThuocService.getAllLoaiThuoc());

        return"category/list";

    }

    @GetMapping("/add")
    public String addLoaiThuoc(@NotNull Model model){
        model.addAttribute("category",new Category());
        return "category/add";
    }

    @PostMapping("/add")
    public String addLoaiThuoc(@ModelAttribute("category") Category category, BindingResult bindingResult, Model model    ){
        if(bindingResult.hasErrors()){
            return "category/add";
        }
        loaiThuocService.addLoaithuoc(category);
        return "redirect:/categories";

    }
    @GetMapping("/edit/{id}")
    public String editLoaiThuoc(@NotNull Model model, @PathVariable Long id){
        var loaithuoc = loaiThuocService.getLoaiThuoc(id).orElse(null);
        model.addAttribute("category",loaithuoc!=null?loaithuoc:new LoaiThuoc());
        return "category/edit";
    }
    @PostMapping("/edit")
    public String editLoaiThuoc(@ModelAttribute("category") Category category){
        loaiThuocService.updateLoaithuoc(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteLoaiThuoc(@NotNull Model model, @PathVariable Long id){
        if(loaiThuocService.getLoaiThuoc(id).isPresent())
            loaiThuocService.deleteLoaithuoc(id);
        return "redirect:/categories";
    }

}
