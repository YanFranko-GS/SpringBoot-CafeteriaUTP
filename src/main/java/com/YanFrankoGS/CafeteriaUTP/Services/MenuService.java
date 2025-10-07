package com.YanFrankoGS.CafeteriaUTP.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YanFrankoGS.CafeteriaUTP.DTO.MenusDTO;
import com.YanFrankoGS.CafeteriaUTP.Model.Menus;
import com.YanFrankoGS.CafeteriaUTP.Repository.MenusRepository;

@Service
public class MenuService {
    @Autowired
    private MenusRepository menusRepository;

    public List<MenusDTO> obtenerTodos() {
        return menusRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public MenusDTO obtenerPorId(Long id) {
        Optional<Menus> menu = menusRepository.findById(id);
        return menu.map(this::convertirADTO).orElse(null);
    }

    public MenusDTO guardarMenu(Menus menu) {
        Menus menuGuardado = menusRepository.save(menu);
        return convertirADTO(menuGuardado);
    }

    public void eliminarMenu(Long id) {
        menusRepository.deleteById(id);
    }

    private MenusDTO convertirADTO(Menus menu) {
        MenusDTO dto = new MenusDTO();
        dto.setIdMenu(menu.getIdMenu());
        dto.setNombre(menu.getNombre());
        dto.setDescripcion(menu.getDescripcion());
        dto.setPrecio(menu.getPrecio());
        dto.setCategoria(menu.getCategoria());
        dto.setDisponibilidad(menu.getDisponibilidad());
        dto.setImagenUrl(menu.getImagen_Url());
        return dto;
    }
}
