package com.YanFrankoGS.CafeteriaUTP.DTO;

import com.YanFrankoGS.CafeteriaUTP.Model.Enums.OpcionPago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {

    private Long idPago;
    private OpcionPago metodoPago;

    private String codigoPago;
    private LocalDateTime fecha;
    private double montoTotal;
    private Long idPedido; 
}
