package com.control.hub.proposal;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ProposalService {

    private static final List<ProposalDto> PROPOSALS = List.of(
            new ProposalDto(
                    "1",
                    "Integração API de pagamentos",
                    "active",
                    "Proposta para integrar gateway de pagamento ao sistema principal.",
                    Instant.parse("2025-03-01T10:00:00Z")
            ),
            new ProposalDto(
                    "2",
                    "Refatoração módulo de relatórios",
                    "draft",
                    "Melhorar performance e manutenibilidade do módulo de relatórios.",
                    Instant.parse("2025-03-05T14:30:00Z")
            ),
            new ProposalDto(
                    "3",
                    "Dashboard em tempo real",
                    "active",
                    "Implementar dashboard com atualização em tempo real via WebSocket.",
                    Instant.parse("2025-03-08T09:15:00Z")
            ),
            new ProposalDto(
                    "4",
                    "Migração de dados legados",
                    "closed",
                    "Plano de migração dos dados do sistema legado para a nova base.",
                    Instant.parse("2025-02-20T11:00:00Z")
            ),
            new ProposalDto(
                    "5",
                    "Autenticação em dois fatores",
                    "draft",
                    "Adicionar 2FA para contas administrativas.",
                    Instant.parse("2025-03-09T16:45:00Z")
            )
    );

    public List<ProposalDto> findAll(String status, String q) {
        Stream<ProposalDto> stream = PROPOSALS.stream();
        if (status != null && !status.isBlank()) {
            stream = stream.filter(p -> status.equalsIgnoreCase(p.status()));
        }
        if (q != null && !q.isBlank()) {
            String lower = q.toLowerCase();
            stream = stream.filter(p ->
                    p.title().toLowerCase().contains(lower) ||
                            (p.description() != null && p.description().toLowerCase().contains(lower))
            );
        }
        return stream.toList();
    }

    public Optional<ProposalDto> findById(String id) {
        return PROPOSALS.stream()
                .filter(p -> p.id().equals(id))
                .findFirst();
    }
}
