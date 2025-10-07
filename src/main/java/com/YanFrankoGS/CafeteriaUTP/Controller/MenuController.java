package com.YanFrankoGS.CafeteriaUTP.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.YanFrankoGS.CafeteriaUTP.DTO.MenusDTO;
import com.YanFrankoGS.CafeteriaUTP.Model.Menus;
import com.YanFrankoGS.CafeteriaUTP.Services.MenuService;

@RestController
@RequestMapping("/api/v1/menus")
//@CrossOrigin(origins = "*") // Permitir CORS desde cualquier origen, opcional
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<MenusDTO> listarMenus() {
        return menuService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenusDTO> obtenerMenu(@PathVariable Long id) {
        MenusDTO menu = menuService.obtenerPorId(id);
        if (menu != null) {
            return ResponseEntity.ok(menu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<MenusDTO> crearMenu(@RequestBody Menus menu) {
        MenusDTO menuGuardado = menuService.guardarMenu(menu);
        return ResponseEntity.ok(menuGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenusDTO> actualizarMenu(@PathVariable Long id, @RequestBody Menus menu) {
        MenusDTO menuExistente = menuService.obtenerPorId(id);
        if (menuExistente == null) {
            return ResponseEntity.notFound().build();
        }
        menu.setIdMenu(id); // asegurarse que se actualiza el menú correcto
        MenusDTO menuActualizado = menuService.guardarMenu(menu);
        return ResponseEntity.ok(menuActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMenu(@PathVariable Long id) {
        menuService.eliminarMenu(id);
        return ResponseEntity.noContent().build();
    }
}
