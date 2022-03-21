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
import org.springframework.data.relational.core.mapping.Column;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRate {

    @Id
    private Integer id;

    @Column("sourceCurrencyId")
    private Integer sourceCurrencyId;

    @Column("targetCurrencyId")
    private Integer targetCurrencyId;

    private BigDecimal value;

    private ExchangeRateType type;

    @CreatedBy
    @Column("createdBy")
    @JsonIgnore
    private Integer createdBy;

    @CreatedDate
    @Column("createdAt")
    @JsonIgnore
    private LocalDateTime createdAt;

    @LastModifiedBy
    @Column("modifiedBy")
    @JsonIgnore
    private Integer modifiedBy;

    @LastModifiedDate
    @Column("modifiedAt")
    @JsonIgnore
    private LocalDateTime modifiedAt;
}
