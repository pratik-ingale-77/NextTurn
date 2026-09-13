package com.nextturn.controller;

import com.nextturn.model.Partner;
import com.nextturn.repository.PartnerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partner-login")
@CrossOrigin
public class PartnerLoginController {

    private final PartnerRepository partnerRepository;

    public PartnerLoginController(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @PostMapping
    public ResponseEntity<Partner> login(@RequestBody Partner loginPartner) {

        return partnerRepository.findByPartnerId(loginPartner.getPartnerId())
                .filter(partner ->
                        partner.getPassword().equals(loginPartner.getPassword()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }
}
