package com.nightcat.repository;

import com.nightcat.entity.ProjectImage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectImagesRepository extends AbstractRepository<ProjectImage> {
    public List<ProjectImage> findByProjId(String proj_id) {
        return findBy("proj_id", proj_id);
    }
}
