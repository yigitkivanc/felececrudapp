package com.felececrud.felececrudapp.jparepository;

import com.felececrud.felececrudapp.entity.OtherInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtherInformationRepository extends JpaRepository<OtherInformation,Integer> {
    boolean existsByIban(String iban);
}
