package com.felececrud.felececrudapp.jparepository;

import com.felececrud.felececrudapp.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProjectRepository extends JpaRepository<Project, Integer>, JpaSpecificationExecutor<Project> {
    boolean existsByProjectName(String projectName);
    boolean existsByVpnUsername(String vpnUsername);


}
