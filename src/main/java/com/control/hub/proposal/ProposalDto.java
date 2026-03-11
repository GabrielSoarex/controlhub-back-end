package com.control.hub.proposal;

import java.time.Instant;

public record ProposalDto(
    String id,
    String title,
    String status,
    String description,
    Instant createdAt
) {}
