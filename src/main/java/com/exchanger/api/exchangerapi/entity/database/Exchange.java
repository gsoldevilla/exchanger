package com.exchanger.api.exchangerapi.entity.database;

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
import org.springframework.data.relational.core.mapping.Column;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exchange {

    @Id
    private Integer id;

    @Column("exchangeRateId")
    private Integer exchangeRateId;

    private BigDecimal amount;

    @CreatedBy
    @Column("createdBy")
    @JsonIgnore
    private Integer createdBy;

    @CreatedDate
    @Column("createdAt")
    @JsonIgnore
    private LocalDateTime createdAt;
}
