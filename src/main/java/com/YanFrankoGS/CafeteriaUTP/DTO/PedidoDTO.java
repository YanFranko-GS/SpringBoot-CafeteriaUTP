package com.YanFrankoGS.CafeteriaUTP.DTO;

import com.YanFrankoGS.CafeteriaUTP.Model.Enums.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private Long idPedido;
    private Long idUsuario;
    private Long idCarrito;
    private LocalDate fecha;
    private LocalTime hora;
    private Double total;
    private EstadoPedido estado;
    private List<DetallePedidoDTO> detallePedido;
    private List<PagoDTO> pago;
}
