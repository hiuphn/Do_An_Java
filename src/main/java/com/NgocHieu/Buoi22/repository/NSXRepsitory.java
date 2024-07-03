package com.NgocHieu.Buoi22.repository;

import com.NgocHieu.Buoi22.model.NhaSanXuat;
import com.NgocHieu.Buoi22.model.Thuoc;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface NSXRepsitory extends PagingAndSortingRepository<NhaSanXuat, Long>, JpaRepository<NhaSanXuat, Long> {
    default List<NhaSanXuat> findAllBooks(Integer pageNo,
                                     Integer pageSize,
                                     String sortBy) {
        return findAll(PageRequest.of(pageNo,
                pageSize,
                Sort.by(sortBy)))
                .getContent();
    }
}
