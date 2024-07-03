package com.NgocHieu.Buoi22.service;

import com.NgocHieu.Buoi22.model.Thuoc;
import com.NgocHieu.Buoi22.repository.IThuocRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.SERIALIZABLE,
        rollbackFor = {Exception.class, Throwable.class})
public class ThuocService {

    final IThuocRepository thuocRepository;
    public Optional<Thuoc> getThuocById(Long id) {
        return thuocRepository.findById(id);
    }

    public List<Thuoc> getAllMedicines(Integer pageNo,
                                   Integer pageSize,
                                   String sortBy){
        return thuocRepository.findAllBooks(pageNo,pageSize,sortBy);
    }

    public Optional<Thuoc>getMedicineById(Long  id){
        return thuocRepository.findById(id);
    }

    public void addMedicine( Thuoc thuoc){
        thuocRepository.save(thuoc);
    }

    public void updateMedicine(@NotNull Thuoc thuoc){

        Thuoc existingThuoc = thuocRepository.findById(thuoc.getIdthuoc()).orElse(null);
            Objects.requireNonNull(existingThuoc).setCachdung(thuoc.getCachdung());

            existingThuoc.setGiaban(thuoc.getGiaban());
            existingThuoc.setBaoquan(thuoc.getBaoquan());
            existingThuoc.setChuY(thuoc.getChuY());
            existingThuoc.setCongdung(thuoc.getCongdung());

            existingThuoc.setHansudung(thuoc.getHansudung());
            existingThuoc.setTenthuoc(thuoc.getTenthuoc());

            existingThuoc.setPhantacdung(thuoc.getPhantacdung());
            existingThuoc.setThanhPhan(thuoc.getThanhPhan());
            existingThuoc.setDonViTinh(thuoc.getDonViTinh());
            existingThuoc.setLoaithuoc(thuoc.getLoaithuoc());
            existingThuoc.setNhaSanXuat(thuoc.getNhaSanXuat());

            thuocRepository.save(existingThuoc);




    }
    public void deleteMedicineById(Long  id){
        thuocRepository.deleteById(id);
    }


    public List<Thuoc> searchBook(String keyword) {
        return thuocRepository.searchBook(keyword);
    }

    public List<Thuoc> getAllProducts() {
        return thuocRepository.findAll();
    }
    public List<Thuoc> getProductsByPage(int page, int pageSize) {
        // Tính toán vị trí bắt đầu của danh sách sản phẩm
        int startIndex = (page - 1) * pageSize;

        // Lấy danh sách sản phẩm từ vị trí bắt đầu và kích thước trang
        List<Thuoc> allProducts = getAllProducts();
        List<Thuoc> thuocs = new ArrayList<>();

        for (int i = startIndex; i < startIndex + pageSize && i < allProducts.size(); i++) {
            thuocs.add(allProducts.get(i));
        }

        return thuocs;
    }

    public int getTotalPages(int pageSize) {
        List<Thuoc> allProducts = getAllProducts();
        return (int) Math.ceil((double) allProducts.size() / pageSize);
    }
}
