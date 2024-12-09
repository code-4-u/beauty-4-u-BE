package com.beauty4u.backend.customer.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.YnType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
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
    @Enumerated(EnumType.STRING)
    private CustomerGrade customerGrade;

    @NotNull
    @Column(name = "customer_created_date", nullable = false)
    private LocalDateTime customerCreatedDate;

    @NotNull
    @Column(name = "customer_updated_date")
    private LocalDateTime customerUpdatedDate;

    @NotNull
    @Column(name = "privacy_consent_yn", nullable = false)
    @Enumerated(EnumType.STRING)
    private YnType privacyConsentYn;
}