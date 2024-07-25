package com.felececrud.felececrudapp.specifications;

import com.felececrud.felececrudapp.filterRequest.ProjectFilterRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import com.felececrud.felececrudapp.entity.Project;

public class ProjectSpecification {

    public static Specification<Project> filter(ProjectFilterRequest filterRequest) {
        return new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                if (filterRequest.getProjectName() != null) {
                    predicate = cb.and(predicate, cb.like(cb.lower(root.get("projectName")), "%" + filterRequest.getProjectName().toLowerCase() + "%"));
                }

                if (filterRequest.getProjectType() != null) {
                    predicate = cb.and(predicate, cb.equal(root.get("projectType"), filterRequest.getProjectType()));
                }

                if (filterRequest.getDepartment() != null) {
                    predicate = cb.and(predicate, cb.like(cb.lower(root.get("department")), "%" + filterRequest.getDepartment().toLowerCase() + "%"));
                }

                if (filterRequest.getVpnUsername() != null) {
                    predicate = cb.and(predicate, cb.equal(root.get("vpnUsername"), filterRequest.getVpnUsername()));
                }

                if (filterRequest.getVpnPassword() != null) {
                    predicate = cb.and(predicate, cb.equal(root.get("vpnPassword"), filterRequest.getVpnPassword()));
                }

                if (filterRequest.getEnvironmentDetails() != null) {
                    predicate = cb.and(predicate, cb.like(cb.lower(root.get("environmentDetails")), "%" + filterRequest.getEnvironmentDetails().toLowerCase() + "%"));
                }

                if (filterRequest.getEmployeeId() != null) {
                    predicate = cb.and(predicate, cb.equal(root.get("employeeId"), filterRequest.getEmployeeId()));
                }

                if (filterRequest.getManagerId() != null) {
                    predicate = cb.and(predicate, cb.equal(root.get("managerId"), filterRequest.getManagerId()));
                }

                return predicate;
            }
        };
    }
}
