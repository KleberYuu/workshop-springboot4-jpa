package com.estudosjava.curso.dto;

import com.estudosjava.curso.entities.Payment;

import java.time.Instant;

public class PaymentResponseDTO {

    private Instant moment;

    public PaymentResponseDTO() {
    }

    public PaymentResponseDTO(Payment payment) {
        this.moment = payment.getMoment();
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }
}
