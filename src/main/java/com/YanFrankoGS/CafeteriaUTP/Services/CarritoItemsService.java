package com.YanFrankoGS.CafeteriaUTP.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YanFrankoGS.CafeteriaUTP.DTO.CarritoItemDTO;
import com.YanFrankoGS.CafeteriaUTP.Model.Carrito;
import com.YanFrankoGS.CafeteriaUTP.Model.Carrito_items;
import com.YanFrankoGS.CafeteriaUTP.Model.Menus;
import com.YanFrankoGS.CafeteriaUTP.Repository.CarritoItemsRepository;
import com.YanFrankoGS.CafeteriaUTP.Repository.CarritoRepository;
import com.YanFrankoGS.CafeteriaUTP.Repository.MenusRepository;

@Service
public class CarritoItemsService {
    @Autowired
    private CarritoItemsRepository carritoItemsRepository;
    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private MenusRepository menusRepository;

    public List<CarritoItemDTO> listarItems() {
        return carritoItemsRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public Optional<CarritoItemDTO> obtenerItemPorId(Long idItem) {
        return carritoItemsRepository.findById(idItem)
                .map(this::convertirADTO);
    }

    public CarritoItemDTO agregarItem(Long idCarrito, Long idMenu, int cantidad) {
        Carrito carrito = carritoRepository.findById(idCarrito)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con ID: " + idCarrito));

        Menus menu = menusRepository.findById(idMenu)
                .orElseThrow(() -> new RuntimeException("Menú no encontrado con ID: " + idMenu));

        Carrito_items item = new Carrito_items();
        item.setCarrito(carrito);
        item.setMenu(menu);
        item.setCantidad(cantidad);
        item.setSubtotal(menu.getPrecio() * cantidad);

        Carrito_items guardado = carritoItemsRepository.save(item);
        return convertirADTO(guardado);
    }

    public CarritoItemDTO actualizarCantidad(Long idItem, int nuevaCantidad) {
        Carrito_items item = carritoItemsRepository.findById(idItem)
                .orElseThrow(() -> new RuntimeException("Item no encontrado con ID: " + idItem));

        item.setCantidad(nuevaCantidad);
        item.setSubtotal(item.getMenu().getPrecio() * nuevaCantidad);
        carritoItemsRepository.save(item);

        return convertirADTO(item);
    }

    public void eliminarItem(Long idItem) {
        if (!carritoItemsRepository.existsById(idItem)) {
            throw new RuntimeException("Item no encontrado con ID: " + idItem);
        }
        carritoItemsRepository.deleteById(idItem);
    }

    public List<CarritoItemDTO> listarItemsPorCarrito(Long idCarrito) {
        Carrito carrito = carritoRepository.findById(idCarrito)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con ID: " + idCarrito));

        return carrito.getItems().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private CarritoItemDTO convertirADTO(Carrito_items items) {
    return new CarritoItemDTO(
        items.getIdCarrito_items(),
        items.getCarrito() != null ? items.getCarrito().getIdCarrito() : null, 
        items.getMenu() != null ? items.getMenu().getIdMenu() : null,         
        items.getCantidad(),
        items.getSubtotal()
    );
}
}
