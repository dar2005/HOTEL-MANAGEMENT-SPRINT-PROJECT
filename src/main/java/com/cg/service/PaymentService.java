package com.cg.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public void processPayment(
        double amount
    ) {

        if (amount <= 0) {
            throw new BadRequestException(
                "Invalid payment amount"
            );
        }

        if (amount > 100000) {
            throw new UnauthorizedException(
                "Payment limit exceeded"
            );
        }
    }
}
