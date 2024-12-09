package com.beauty4u.backend.customer.query.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFilterRequest {

    private String name;
    private String grade;
    private String code;
    private String gender;
    private Integer ageGroup;
    private String sort = "name";
    private String order = "asc";
    private Long page = 1L;
    private Long count = 10L;
}