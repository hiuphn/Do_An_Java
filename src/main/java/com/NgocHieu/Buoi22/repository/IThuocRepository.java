package com.NgocHieu.Buoi22.repository;

import com.NgocHieu.Buoi22.model.Thuoc;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IThuocRepository extends PagingAndSortingRepository<Thuoc, Long>, JpaRepository<Thuoc, Long> {
    default List<Thuoc> findAllBooks(Integer pageNo,
                                    Integer pageSize,
                                    String sortBy) {
        return findAll(PageRequest.of(pageNo,
                pageSize,
                Sort.by(sortBy)))
                .getContent();
    }
    @Query("""
            SELECT b FROM Thuoc b
            WHERE b.Tenthuoc LIKE %?1%
            OR b.nhaSanXuat.TenNSX LIKE %?1%
            OR b.loaithuoc.tenLoai LIKE %?1%
            """)
    List<Thuoc> searchBook(String keyword);
}