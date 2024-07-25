package com.felececrud.felececrudapp.specifications;

import com.felececrud.felececrudapp.enums.ContractType;
import com.felececrud.felececrudapp.enums.Level;
import com.felececrud.felececrudapp.enums.Team;
import com.felececrud.felececrudapp.enums.WorkType;
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
                    predicate = cb.and(predicate, cb.like(cb.lower(root.get("firstName")), "%" + filterRequest.getFirstName().toLowerCase() + "%"));
                }

                if (filterRequest.getLastName() != null) {
                    predicate = cb.and(predicate, cb.like(cb.lower(root.get("lastName")), "%" + filterRequest.getLastName().toLowerCase() + "%"));
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
                    Level level = Level.valueOf(filterRequest.getLevel());
                    predicate = cb.and(predicate, cb.equal(root.get("level"), level));
                }

                if (filterRequest.getWorkType() != null) {
                    WorkType workType = WorkType.valueOf(filterRequest.getWorkType());
                    predicate = cb.and(predicate, cb.equal(root.get("workType"), workType));
                }

                if (filterRequest.getContractType() != null) {
                    ContractType contractType = ContractType.valueOf(filterRequest.getContractType());
                    predicate = cb.and(predicate, cb.equal(root.get("contractType"), contractType));
                }

                if (filterRequest.getTeam() != null) {
                    Team team = Team.valueOf(filterRequest.getTeam());
                    predicate = cb.and(predicate, cb.equal(root.get("team"), team));
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
