package com.nextturn.repository;

import com.nextturn.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartnerRepository extends JpaRepository<Partner, Long> {

    Optional<Partner> findByPartnerId(String partnerId);

    boolean existsByPartnerId(String partnerId);
}
