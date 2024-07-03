package com.NgocHieu.Buoi22.repository;

import com.NgocHieu.Buoi22.model.LoaiThuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IloaiThuoc extends JpaRepository<LoaiThuoc, Long> {
}
