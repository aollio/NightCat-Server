package com.nightcat.repository;

import com.nightcat.entity.ProjImage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjImagesRepository extends AbstractRepository<ProjImage> {
    public List<ProjImage> findByProjId(String proj_id) {
        return findBy("proj_id", proj_id);
    }
}
