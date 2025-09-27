package com.filflo.modal;

import com.filflo.domain.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSummary {


    private PaymentType type; // CASH, CARD, UPI

    private Double totalAmount;
    private int transactionCount;
    private double percentage;


}
