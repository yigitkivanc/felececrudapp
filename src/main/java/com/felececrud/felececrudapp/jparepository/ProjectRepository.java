package com.felececrud.felececrudapp.jparepository;

import com.felececrud.felececrudapp.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
