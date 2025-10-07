package com.YanFrankoGS.CafeteriaUTP.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoDTO {

    private Long idDetallePedido;
    private Long idPedido;  
    private Long idMenu;    
    private int cantidad;
    private BigDecimal subtotal;
}
