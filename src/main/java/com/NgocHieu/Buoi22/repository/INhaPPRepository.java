package com.NgocHieu.Buoi22.repository;

import com.NgocHieu.Buoi22.model.NhaPhanPhoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INhaPPRepository extends JpaRepository<NhaPhanPhoi, Long> {


}
