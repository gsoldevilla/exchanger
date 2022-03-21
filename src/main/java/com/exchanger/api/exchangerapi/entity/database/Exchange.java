package com.exchanger.api.exchangerapi.entity.database;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Exchange {

    @Id
    private Integer id;

    @Column("exchangeRateId")
    private Integer exchangeRateId;

    @Column("sourceAmount")
    private BigDecimal sourceAmount;

    @CreatedBy
    @Column("createdBy")
    @JsonIgnore
    private String createdBy;

    @CreatedDate
    @Column("creationDate")
    @JsonIgnore
    private Long creationDate;

    @LastModifiedBy
    @Column("modifiedBy")
    @JsonIgnore
    private String modifiedBy;

    @LastModifiedDate
    @Column("modificationDate")
    @JsonIgnore
    private Long modificationDate;
}
