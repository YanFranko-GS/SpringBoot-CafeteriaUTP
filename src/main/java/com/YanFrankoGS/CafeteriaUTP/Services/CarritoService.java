package com.YanFrankoGS.CafeteriaUTP.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YanFrankoGS.CafeteriaUTP.DTO.CarritoDTO;
import com.YanFrankoGS.CafeteriaUTP.DTO.CarritoItemDTO;
import com.YanFrankoGS.CafeteriaUTP.Model.Carrito;
import com.YanFrankoGS.CafeteriaUTP.Model.Carrito_items;
import com.YanFrankoGS.CafeteriaUTP.Model.Menus;
import com.YanFrankoGS.CafeteriaUTP.Model.Usuario;
import com.YanFrankoGS.CafeteriaUTP.Repository.CarritoRepository;
import com.YanFrankoGS.CafeteriaUTP.Repository.CarritoItemsRepository;
import com.YanFrankoGS.CafeteriaUTP.Repository.MenusRepository;
import com.YanFrankoGS.CafeteriaUTP.Repository.UsuarioRepository;

@Service
public class CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private CarritoItemsRepository carritoItemsRepository;
    @Autowired
    private MenusRepository menusRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<CarritoDTO> listarCarritos() {
        return carritoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public Optional<CarritoDTO> obtenerCarritoPorId(Long idCarrito) {
        return carritoRepository.findById(idCarrito)
                .map(this::convertirADTO);
    }

    public CarritoDTO crearCarrito(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));

        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);
        carrito.setItems(List.of()); 

        Carrito nuevo = carritoRepository.save(carrito);
        return convertirADTO(nuevo);
    }

    public CarritoDTO agregarItem(Long idCarrito, Long idMenu, int cantidad) {
        Carrito carrito = carritoRepository.findById(idCarrito)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con ID: " + idCarrito));

        Menus menu = menusRepository.findById(idMenu)
                .orElseThrow(() -> new RuntimeException("Menú no encontrado con ID: " + idMenu));

        Optional<Carrito_items> itemExistente = carrito.getItems().stream()
                .filter(i -> i.getMenu().getIdMenu().equals(idMenu))
                .findFirst();

        if (itemExistente.isPresent()) {
            Carrito_items item = itemExistente.get();
            item.setCantidad(item.getCantidad() + cantidad);
            item.setSubtotal(item.getCantidad() * menu.getPrecio());
            carritoItemsRepository.save(item);
        } else {
            Carrito_items nuevoItem = new Carrito_items();
            nuevoItem.setCarrito(carrito);
            nuevoItem.setMenu(menu);
            nuevoItem.setCantidad(cantidad);
            nuevoItem.setSubtotal(menu.getPrecio() * cantidad);
            carritoItemsRepository.save(nuevoItem);
        }

        return convertirADTO(carritoRepository.findById(idCarrito).get());
    }

    public CarritoDTO eliminarItem(Long idCarrito, Long idMenu) {
        Carrito carrito = carritoRepository.findById(idCarrito)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con ID: " + idCarrito));

        carrito.getItems().removeIf(item -> item.getMenu().getIdMenu().equals(idMenu));
        carritoRepository.save(carrito);

        return convertirADTO(carrito);
    }

    public void vaciarCarrito(Long idCarrito) {
        Carrito carrito = carritoRepository.findById(idCarrito)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con ID: " + idCarrito));

        carritoItemsRepository.deleteByCarrito(carrito);
    }

    private CarritoDTO convertirADTO(Carrito carrito) {
    CarritoDTO dto = new CarritoDTO();
    dto.setIdCarrito(carrito.getIdCarrito());
    dto.setIdUsuario(carrito.getUsuario() != null ? carrito.getUsuario().getIdUsuario() : null);

    if (carrito.getItems() != null) {
        List<CarritoItemDTO> itemsDTO = carrito.getItems().stream()
            .map(items -> {
                CarritoItemDTO itemDTO = new CarritoItemDTO();
                itemDTO.setIdCarrito_items(items.getIdCarrito_items());
                itemDTO.setIdMenu(items.getMenu() != null ? items.getMenu().getIdMenu() : null);
                itemDTO.setCantidad(items.getCantidad());
                itemDTO.setSubtotal(items.getSubtotal());
                return itemDTO;
            })
            .collect(Collectors.toList());
        dto.setItems(itemsDTO);
    } else {
        dto.setItems(null);
    }

    return dto;
}


}
