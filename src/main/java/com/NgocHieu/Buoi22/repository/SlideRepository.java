package com.NgocHieu.Buoi22.repository;

import com.NgocHieu.Buoi22.model.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {
}
