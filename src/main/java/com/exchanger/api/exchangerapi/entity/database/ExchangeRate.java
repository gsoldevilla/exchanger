package com.exchanger.api.exchangerapi.entity.database;

import com.exchanger.api.exchangerapi.type.ExchangeRateType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRate {

    @Id
    private Integer id;

    private Integer sourceCurrencyId;

    private Integer targetCurrencyId;

    private BigDecimal value;

    private ExchangeRateType type;

    @CreatedBy
    @JsonIgnore
    private Integer createdBy;

    @CreatedDate
    @JsonIgnore
    private LocalDateTime createdAt;

    @LastModifiedBy
    @JsonIgnore
    private Integer modifiedBy;

    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime modifiedAt;
}
