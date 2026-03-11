package com.control.hub.proposal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proposals")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProposalController {

    private final ProposalService proposalService = new ProposalService();

    @GetMapping
    public ResponseEntity<List<ProposalDto>> list(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String q
    ) {
        List<ProposalDto> proposals = proposalService.findAll(status, q);
        return ResponseEntity.ok(proposals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProposalDto> getById(@PathVariable String id) {
        return proposalService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
