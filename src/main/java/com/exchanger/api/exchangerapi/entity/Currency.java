package com.exchanger.api.exchangerapi.entity;

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
public class Currency {

    @Id
    private Integer id;

    private String code;

    private String symbol;

    private String name;

    private Boolean obsolete;

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
