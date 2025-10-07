package com.YanFrankoGS.CafeteriaUTP.DTO.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthResponse {
    private String token;
    private String tipoToken = "Bearer";
}
