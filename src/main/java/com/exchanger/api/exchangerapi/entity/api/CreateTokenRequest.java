package com.exchanger.api.exchangerapi.entity.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTokenRequest {

    private String username;

    private String password;
}
