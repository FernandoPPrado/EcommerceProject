package com.example.demo.model;

public enum PurchaseStatus {
    PENDING,        // pending
    APPROVED,       // approved
    AUTHORIZED,     // authorized
    IN_PROCESS,     // in_process
    IN_MEDIATION,   // in_mediation
    REJECTED,       // rejected
    CANCELLED,      // cancelled
    REFUNDED,       // refunded
    CHARGED_BACK;   // charged_back   <-- repare no ponto e vírgula aqui!

    public static PurchaseStatus fromMercadoPagoStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status nulo recebido do Mercado Pago");
        }
        return switch (status.toLowerCase()) {
            case "pending" -> PENDING;
            case "approved" -> APPROVED;
            case "authorized" -> AUTHORIZED;
            case "in_process" -> IN_PROCESS;
            case "in_mediation" -> IN_MEDIATION;
            case "rejected" -> REJECTED;
            case "cancelled" -> CANCELLED;
            case "refunded" -> REFUNDED;
            case "charged_back" -> CHARGED_BACK;
            default -> throw new IllegalArgumentException("Status desconhecido: " + status);
        };
    }
}