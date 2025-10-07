package com.YanFrankoGS.CafeteriaUTP.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long idUsuario;
    private String codigoUsuario;
    private String nombre;
    private String apellido;
    private String correo;
    private Set<String> roles; 
}
