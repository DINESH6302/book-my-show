package org.lld.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.lld.bookmyshow.models.enums.PaymentType;

@Getter
@Setter
@Entity
public abstract class Payment extends BaseModel {
    private long refId;
    private double amount;
    private PaymentType paymentType;

    public abstract void processPayment();
}
