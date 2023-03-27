package com.batuhankiltac.paymentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payment")
@ToString
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "paymentId")
    private Integer paymentId;

    @Column(name = "paymentStatus")
    private String status;

    @Column(name = "paymentCardNumber")
    private String cardNumber;

    @Column(name = "paymentAmount")
    private BigDecimal amount;

    @Column(name = "paymentDate")
    private LocalDateTime date;

    @Column(name = "paymentCardFamily")
    private String cardFamily;

    @Column(name = "paymentInstallmentNo")
    private Integer installmentNo;

    @Column(name = "paymentInstallmentCount")
    private Integer installmentCount;

    @Column(name = "paymentErrorMessage")
    private String errorMessage;

    @Column(name = "paymentErrorGroup")
    private String errorGroup;
}
