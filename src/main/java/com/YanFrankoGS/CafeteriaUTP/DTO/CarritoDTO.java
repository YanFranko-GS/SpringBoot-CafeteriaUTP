package com.YanFrankoGS.CafeteriaUTP.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


import java.util.List;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class CarritoDTO {

    private Long idCarrito;
    private Long idUsuario; 
    private List<CarritoItemDTO> items; 

    
}
