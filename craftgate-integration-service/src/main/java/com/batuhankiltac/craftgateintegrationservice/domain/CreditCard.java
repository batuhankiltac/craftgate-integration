package com.batuhankiltac.craftgateintegrationservice.domain;

import com.batuhankiltac.craftgateintegrationservice.model.enums.CardAssociation;
import com.batuhankiltac.craftgateintegrationservice.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "creditcard")
@ToString
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "cardHolderName")
    private String cardHolderName;

    @Column(name = "cardUserKey")
    private String cardUserKey;

    @Column(name = "cardToken")
    private String cardToken;

    @Column(name = "cardFamily")
    private String cardFamily;

    @Column(name = "cardStatus")
    private String status;

    @Column(name = "cardBankName")
    private String bankName;

    @Column(name = "cardType")
    @Enumerated(value = EnumType.STRING)
    private CardType cardType;

    @Column(name = "cardAssociation")
    @Enumerated(value = EnumType.STRING)
    private CardAssociation cardAssociation;
}
