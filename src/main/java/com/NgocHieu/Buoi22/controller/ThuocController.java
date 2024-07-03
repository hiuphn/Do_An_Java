package com.NgocHieu.Buoi22.controller;

import com.NgocHieu.Buoi22.model.Item;
import com.NgocHieu.Buoi22.service.*;
//import com.NgocHieu.Buoi22.model.Item;
import com.NgocHieu.Buoi22.model.Thuoc;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


@RequestMapping("/thuocs")
@Controller
@RequiredArgsConstructor
public class ThuocController {
    private final ThuocService thuocService;
    private final LoaiThuocService loaiThuocService;
    private final NhaSanXuatService nhaSanXuatService;
    private final DonVTService donVTService;

    private final CartService cartService;
    private final CategoryService categoryService;

    @GetMapping
    public String ShowAllMedicine(@NotNull Model model,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "2") int pageSize,
                                  @RequestParam(defaultValue = "Idthuoc") String sortby,
                                  @RequestParam(defaultValue = "") String direction) {

        if (direction.equals("next")) {
            page++;
        } else if (direction.equals("previous")) {
            page--;
        }
        List<Thuoc> thuocs = thuocService.getProductsByPage(page, pageSize);
        // Thêm danh sách sản phẩm vào model
        model.addAttribute("thuocs", thuocs);

        // Thêm thông tin phân trang vào model
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", thuocService.getTotalPages(pageSize));

        return "thuocs/list";
    }
    @GetMapping("/thuocAdmin")
    public String ShowAllMedicineAdmin(@NotNull Model model,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "5") int pageSize,
                                  @RequestParam(defaultValue = "Idthuoc") String sortby,
                                  @RequestParam(defaultValue = "") String direction) {

        if (direction.equals("next")) {
            page++;
        } else if (direction.equals("previous")) {
            page--;
        }
        List<Thuoc> thuocs = thuocService.getProductsByPage(page, pageSize);
        // Thêm danh sách sản phẩm vào model
        model.addAttribute("thuocs", thuocs);

        // Thêm thông tin phân trang vào model
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", thuocService.getTotalPages(pageSize));

        return "thuocs/listAdmin";
    }

    @GetMapping("/add")
    public String AddMedicine(@NotNull Model model) {
        model.addAttribute("thuoc", new Thuoc());
        model.addAttribute("loaithuocs", loaiThuocService.getAllLoaiThuoc());
        model.addAttribute("nhasanxuats", nhaSanXuatService.getAllNSX());
        model.addAttribute("donvitinhs", donVTService.getAllDVT());
        model.addAttribute("categories", categoryService.getAllLoaiThuoc());
        return "thuocs/add";
    }

    @PostMapping("/add")
    public String AddMedicine(@ModelAttribute("thuoc") Thuoc thuoc,
                              @RequestParam("imageFile") MultipartFile imageFile, Model model) {
        try {
            String fileName = imageFile.getOriginalFilename();
            String uploadDir = "uploads";  // Đường dẫn thư mục bên ngoài
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = imageFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                thuoc.setImage("/uploads/" + fileName); // Đường dẫn tương đối
            } catch (IOException ioe) {
                throw new IOException("Không thể lưu tệp hình ảnh: " + fileName, ioe);
            }

            thuocService.addMedicine(thuoc);

            return "redirect:/thuocs";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/errorPage";
        }
    }

    @GetMapping("/uploads/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("uploads").resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }



    @GetMapping("/search")
    public String searchBook(
            @NotNull Model model,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "idthuoc") String sortBy) {
        model.addAttribute("thuocs", thuocService.searchBook(keyword));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages",
                thuocService
                        .getAllMedicines(pageNo, pageSize, sortBy)
                        .size() / pageSize);
        model.addAttribute("categories",
                loaiThuocService.getAllLoaiThuoc());

        return "thuocs/list";





    }
    @GetMapping("/detail/{id}")
    public String showProductDetail(@PathVariable Long id, Model model) {
        Thuoc thuoc = thuocService.getThuocById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("thuoc", thuoc);
        return "/thuocs/detail";
    }












}
