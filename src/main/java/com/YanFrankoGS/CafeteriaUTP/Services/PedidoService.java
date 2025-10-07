package com.YanFrankoGS.CafeteriaUTP.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YanFrankoGS.CafeteriaUTP.DTO.PedidoDTO;
import com.YanFrankoGS.CafeteriaUTP.Model.Pedido;
import com.YanFrankoGS.CafeteriaUTP.Repository.PedidoRepository;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<PedidoDTO> obtenerTodos() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public PedidoDTO obtenerPorId(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.map(this::convertirADTO).orElse(null);
    }

    public PedidoDTO guardarPedido(Pedido pedido) {
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        return convertirADTO(pedidoGuardado);
    }

    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    private PedidoDTO convertirADTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setIdPedido(pedido.getIdPedido());
        dto.setIdUsuario(pedido.getUsuario().getIdUsuario());
        dto.setIdCarrito(pedido.getCarrito().getIdCarrito());
        dto.setFecha(pedido.getFecha());
        dto.setHora(pedido.getHora());
        dto.setTotal(pedido.getTotal());
        dto.setEstado(pedido.getEstado());
        return dto;
    }
}
