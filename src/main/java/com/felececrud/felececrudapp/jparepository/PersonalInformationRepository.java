package com.felececrud.felececrudapp.jparepository;

import com.felececrud.felececrudapp.entity.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalInformationRepository extends JpaRepository<PersonalInformation,Integer> {
    boolean existsByNationalId(String email);
}
