package com.YanFrankoGS.CafeteriaUTP.Services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YanFrankoGS.CafeteriaUTP.DTO.UsuarioDTO;

import com.YanFrankoGS.CafeteriaUTP.Model.Usuario;
import com.YanFrankoGS.CafeteriaUTP.Repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> obtenerUsuarioPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .map(this::convertirADTO);
    }

    public UsuarioDTO crearUsuario(Usuario usuario) {
        Usuario nuevo = usuarioRepository.save(usuario);
        return convertirADTO(nuevo);
    }

    public UsuarioDTO actualizarUsuario(Long idUsuario, Usuario usuarioActualizado) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));

        usuario.setCodigoUsuario(usuarioActualizado.getCodigoUsuario());
        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setApellido(usuarioActualizado.getApellido());
        usuario.setCorreo(usuarioActualizado.getCorreo());
        usuario.setRoles(usuarioActualizado.getRoles());

        Usuario actualizado = usuarioRepository.save(usuario);
        return convertirADTO(actualizado);
    }

    public void eliminarUsuario(Long idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + idUsuario);
        }
        usuarioRepository.deleteById(idUsuario);
    }

    private UsuarioDTO convertirADTO(Usuario usuario) {
        Set<String> roles = usuario.getRoles()
                .stream()
                .map(rol -> rol.getNombre().name()) 
                .collect(Collectors.toSet());

        return new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getCodigoUsuario(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getCorreo(),
                roles
        );
    }
}
