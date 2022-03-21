package com.exchanger.api.exchangerapi.entity.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currency {

    @Id
    private Integer id;

    private String code;

    private String symbol;

    private String name;

    private Boolean obsolete;
}
