package com.pe.cibertec.app_evaluacion_t1.service;

import com.pe.cibertec.app_evaluacion_t1.model.bd.Rol;
import com.pe.cibertec.app_evaluacion_t1.model.bd.Usuario;
import com.pe.cibertec.app_evaluacion_t1.repository.RolRepository;
import com.pe.cibertec.app_evaluacion_t1.repository.UsuarioRepository;
import com.pe.cibertec.app_evaluacion_t1.util.RandomPassword;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService implements IUsuarioService{

    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarUsuarioXNomUsuario(String nomusuario) {
        return usuarioRepository.findByNomusuario(nomusuario);
    }

    @Override
    public Usuario buscarUsuarioXIdUsuario(Integer idusuario) {
        return usuarioRepository.findById(idusuario).orElse(null);
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuario.setActivo(true);
        Rol usuarioRol = rolRepository.findByNomrol("ADMIN");
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioRepository.actualizarUsuario(
                usuario.getNombres(),usuario.getApellidos(),
                usuario.getActivo(),usuario.getIdusuario()
        );
    }

    @Override
    public void actualizarPassword(String nomusuario, String nuevaPassword) {
        Usuario usuario = usuarioRepository.findByNomusuario(nomusuario);
        if (usuario != null) {
            String regla = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
            if (!nuevaPassword.matches(regla)) {
                throw new IllegalArgumentException("La contraseña no cumple con los requisitos.");
            }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordCifrada = passwordEncoder.encode(nuevaPassword);
            usuario.setPassword(passwordCifrada);
            usuarioRepository.save(usuario);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado: " + nomusuario);
        }
    }


}
