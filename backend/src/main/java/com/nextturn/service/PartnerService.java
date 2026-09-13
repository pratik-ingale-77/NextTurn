package com.nextturn.service;

import com.nextturn.model.Partner;
import com.nextturn.repository.PartnerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartnerService {

    private final PartnerRepository partnerRepository;

    public PartnerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    public Partner registerPartner(Partner partner) {
        if (partnerRepository.existsByPartnerId(partner.getPartnerId())) {
            throw new RuntimeException("Partner ID already registered");
        }

        return partnerRepository.save(partner);
    }

    public Optional<Partner> getPartnerById(String partnerId) {
        return partnerRepository.findByPartnerId(partnerId);
    }
}
