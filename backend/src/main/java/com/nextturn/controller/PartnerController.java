package com.nextturn.controller;

import com.nextturn.model.Partner;
import com.nextturn.service.PartnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partners")
@CrossOrigin
public class PartnerController {

    private final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Partner> registerPartner(@RequestBody Partner partner) {
        try {
            return ResponseEntity.ok(partnerService.registerPartner(partner));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{partnerId}")
    public ResponseEntity<Partner> getPartner(@PathVariable String partnerId) {
        return partnerService.getPartnerById(partnerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
