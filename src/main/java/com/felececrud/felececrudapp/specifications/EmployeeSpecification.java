package com.felececrud.felececrudapp.specifications;

import com.felececrud.felececrudapp.filterRequest.EmployeeFilterRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import com.felececrud.felececrudapp.entity.Employee;

public class EmployeeSpecification {

    public static Specification<Employee> filter(EmployeeFilterRequest filterRequest) {
        return new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                if (filterRequest.getFirstName() != null) {
                    predicate = cb.and(predicate, cb.equal(root.get("firstName"), filterRequest.getFirstName()));
                }

                if (filterRequest.getLastName() != null) {
                    predicate = cb.and(predicate, cb.equal(root.get("lastName"), filterRequest.getLastName()));
                }

                if (filterRequest.getPhoneNumber() != null) {
                    predicate = cb.and(predicate, cb.equal(root.get("phoneNumber"), filterRequest.getPhoneNumber()));
                }

                if (filterRequest.getEmail() != null) {
                    predicate = cb.and(predicate, cb.equal(root.get("email"), filterRequest.getEmail()));
                }

                if (filterRequest.getBirthDate() != null) {
                    predicate = cb.and(predicate, cb.equal(root.get("birthDate"), filterRequest.getBirthDate()));
                }

                if (filterRequest.getLevel() != null) {
                    predicate = cb.and(predicate, cb.equal(root.get("level"), filterRequest.getLevel()));
                }

                if (filterRequest.getWorkType() != null) {
                    predicate = cb.and(predicate, cb.equal(root.get("workType"), filterRequest.getWorkType()));
                }

                if (filterRequest.getContractType() != null) {
                    predicate = cb.and(predicate, cb.equal(root.get("contractType"), filterRequest.getContractType()));
                }

                if (filterRequest.getTeam() != null) {
                    predicate = cb.and(predicate, cb.equal(root.get("team"), filterRequest.getTeam()));
                }

                if (filterRequest.getStartDate() != null) {
                    predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("startDate"), filterRequest.getStartDate()));
                }

                if (filterRequest.getEndDate() != null) {
                    predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("endDate"), filterRequest.getEndDate()));
                }

                return predicate;
            }
        };
    }
}
