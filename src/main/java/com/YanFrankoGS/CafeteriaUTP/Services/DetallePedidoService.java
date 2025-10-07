package com.YanFrankoGS.CafeteriaUTP.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YanFrankoGS.CafeteriaUTP.DTO.DetallePedidoDTO;
import com.YanFrankoGS.CafeteriaUTP.Model.DetallePedido;
import com.YanFrankoGS.CafeteriaUTP.Model.Menus;
import com.YanFrankoGS.CafeteriaUTP.Model.Pedido;
import com.YanFrankoGS.CafeteriaUTP.Repository.DetallePedidoRepository;
import com.YanFrankoGS.CafeteriaUTP.Repository.MenusRepository;
import com.YanFrankoGS.CafeteriaUTP.Repository.PedidoRepository;

@Service
public class DetallePedidoService {
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private MenusRepository menusRepository;

    public List<DetallePedidoDTO> listarDetalles() {
        return detallePedidoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public Optional<DetallePedidoDTO> obtenerDetallePorId(Long id) {
        return detallePedidoRepository.findById(id)
                .map(this::convertirADTO);
    }

    public DetallePedidoDTO crearDetalle(DetallePedidoDTO dto) {
        DetallePedido detalle = convertirAEntidad(dto);
        Pedido pedido = pedidoRepository.findById(dto.getIdPedido())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + dto.getIdPedido()));
        Menus menu = menusRepository.findById(dto.getIdMenu())
                .orElseThrow(() -> new RuntimeException("Menú no encontrado con ID: " + dto.getIdMenu()));
        detalle.setPedido(pedido);
        detalle.setMenu(menu);
        DetallePedido guardado = detallePedidoRepository.save(detalle);
        return convertirADTO(guardado);
    }

    public DetallePedidoDTO actualizarDetalle(Long id, DetallePedidoDTO dto) {
        DetallePedido detalleExistente = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado con ID: " + id));
        detalleExistente.setCantidad(dto.getCantidad());
        detalleExistente.setSubtotal(dto.getSubtotal());
        if (dto.getIdPedido() != null) {
            Pedido pedido = pedidoRepository.findById(dto.getIdPedido())
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + dto.getIdPedido()));
            detalleExistente.setPedido(pedido);
        }
        if (dto.getIdMenu() != null) {
            Menus menu = menusRepository.findById(dto.getIdMenu())
                    .orElseThrow(() -> new RuntimeException("Menú no encontrado con ID: " + dto.getIdMenu()));
            detalleExistente.setMenu(menu);
        }
        DetallePedido actualizado = detallePedidoRepository.save(detalleExistente);
        return convertirADTO(actualizado);
    }

    public void eliminarDetalle(Long id) {
        if (!detallePedidoRepository.existsById(id)) {
            throw new RuntimeException("El detalle de pedido con ID " + id + " no existe.");
        }
        detallePedidoRepository.deleteById(id);
    }

    private DetallePedidoDTO convertirADTO(DetallePedido detalle) {
        return new DetallePedidoDTO(
                detalle.getIdDetallePedido(),
                detalle.getPedido() != null ? detalle.getPedido().getIdPedido() : null,
                detalle.getMenu() != null ? detalle.getMenu().getIdMenu() : null,
                detalle.getCantidad(),
                detalle.getSubtotal()
        );
    }

    private DetallePedido convertirAEntidad(DetallePedidoDTO dto) {
        DetallePedido detalle = new DetallePedido();
        detalle.setIdDetallePedido(dto.getIdDetallePedido());
        detalle.setCantidad(dto.getCantidad());
        detalle.setSubtotal(dto.getSubtotal());
        return detalle;
    }
}
