package com.NgocHieu.Buoi22.repository;

import com.NgocHieu.Buoi22.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdocterRepository extends JpaRepository<Doctor,Long> {
}
