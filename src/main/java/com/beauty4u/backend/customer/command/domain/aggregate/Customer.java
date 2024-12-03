package com.beauty4u.backend.customer.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Size(max = 20)
    @Column(name = "customer_code", nullable = false, length = 20)
    private String customerCode;

    @Size(max = 20)
    @NotNull
    @Column(name = "customer_name", nullable = false, length = 20)
    private String customerName;

    @Size(max = 100)
    @NotNull
    @Column(name = "customer_email", nullable = false, length = 100)
    private String customerEmail;

    @Size(max = 20)
    @NotNull
    @Column(name = "customer_phone", nullable = false, length = 20)
    private String customerPhone;

    @NotNull
    @Column(name = "customer_age", nullable = false)
    private Integer customerAge;

    @NotNull
    @Lob
    @Column(name = "customer_gender", nullable = false)
    private String customerGender;

    @Size(max = 20)
    @NotNull
    @Column(name = "customer_skintype", nullable = false, length = 20)
    private String customerSkintype;

    @NotNull
    @Lob
    @Column(name = "customer_grade", nullable = false)
    private String customerGrade;

    @NotNull
    @Column(name = "customer_created_date", nullable = false)
    private Instant customerCreatedDate;

    @Column(name = "customer_updated_date")
    private Instant customerUpdatedDate;

    @NotNull
    @Column(name = "privacy_consent_yn", nullable = false)
    private Character privacyConsentYn;

}