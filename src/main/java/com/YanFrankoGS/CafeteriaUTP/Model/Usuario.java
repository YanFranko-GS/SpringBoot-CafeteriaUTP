package com.YanFrankoGS.CafeteriaUTP.Model;

import jakarta.persistence.GenerationType;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;


import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;

@AllArgsConstructor
@Data

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idUsuario;

    String codigoUsuario;
    String nombre;
    String apellido;
    String correo;
    String contraseña;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_roles",
        joinColumns = @JoinColumn(name = "idUsuario"),
        inverseJoinColumns = @JoinColumn(name = "idRol")
    )

    private Set<Rol> roles = new HashSet<>();

    // no te olvides de la autorizacion eso lo ves mas adelante 
    // en seguridad de spring por ahora solo el modelo


}
