package com.YanFrankoGS.CafeteriaUTP.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YanFrankoGS.CafeteriaUTP.DTO.PagoDTO;
import com.YanFrankoGS.CafeteriaUTP.Model.Pagos;
import com.YanFrankoGS.CafeteriaUTP.Model.Pedido;
import com.YanFrankoGS.CafeteriaUTP.Repository.PagosRepository;
import com.YanFrankoGS.CafeteriaUTP.Repository.PedidoRepository;

@Service
public class PagosService {
    @Autowired
    private PagosRepository pagosRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<PagoDTO> listarPagos() {
        return pagosRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public Optional<PagoDTO> obtenerPagoPorId(Long idPago) {
        return pagosRepository.findById(idPago)
                .map(this::convertirADTO);
    }

    public PagoDTO crearPago(PagoDTO pagoDTO) {
        Pagos pago = convertirAEntidad(pagoDTO);
        Pedido pedido = pedidoRepository.findById(pagoDTO.getIdPedido())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + pagoDTO.getIdPedido()));
        pago.setPedido(pedido);
        Pagos pagoGuardado = pagosRepository.save(pago);
        return convertirADTO(pagoGuardado);
    }

    public PagoDTO actualizarPago(Long idPago, PagoDTO pagoDTO) {
        Pagos pagoExistente = pagosRepository.findById(idPago)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + idPago));
                
        pagoExistente.setMetodoPago(pagoDTO.getMetodoPago());
        pagoExistente.setCodigoPago(pagoDTO.getCodigoPago());
        pagoExistente.setFecha(pagoDTO.getFecha());
        pagoExistente.setMontoTotal(pagoDTO.getMontoTotal());
        if (pagoDTO.getIdPedido() != null) {
            Pedido pedido = pedidoRepository.findById(pagoDTO.getIdPedido())
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + pagoDTO.getIdPedido()));
            pagoExistente.setPedido(pedido);
        }
        Pagos pagoActualizado = pagosRepository.save(pagoExistente);
        return convertirADTO(pagoActualizado);
    }

    public void eliminarPago(Long idPago) {
        pagosRepository.deleteById(idPago);
    }

    private PagoDTO convertirADTO(Pagos pago) {
        return new PagoDTO(
                pago.getIdPago(),
                pago.getMetodoPago(),
                pago.getCodigoPago(),
                pago.getFecha(),
                pago.getMontoTotal(),
                pago.getPedido() != null ? pago.getPedido().getIdPedido() : null
        );
    }

    private Pagos convertirAEntidad(PagoDTO dto) {
        Pagos pago = new Pagos();
        pago.setIdPago(dto.getIdPago());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setCodigoPago(dto.getCodigoPago());
        pago.setFecha(dto.getFecha());
        pago.setMontoTotal(dto.getMontoTotal());
        return pago;
    }
}
