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
import org.springframework.data.annotation.Transient;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exchange {

    @Id
    private Integer id;

    private Integer exchangeRateId;

    private BigDecimal amount;

    @Transient
    private BigDecimal finalAmount;

    @CreatedBy
    @JsonIgnore
    private Integer createdBy;

    @CreatedDate
    @JsonIgnore
    private LocalDateTime createdAt;
}
