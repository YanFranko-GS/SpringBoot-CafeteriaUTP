package com.YanFrankoGS.CafeteriaUTP.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoItemDTO {

    private Long idCarrito_items; 
    private Long idCarrito; 
    private Long idMenu;  
    private int cantidad;
    private double subtotal;


    
}
