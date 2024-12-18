package com.beauty4u.backend.inform.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InformListResDTO {

    List<InformListDTO> informList;
    private Long totalCount;
}
