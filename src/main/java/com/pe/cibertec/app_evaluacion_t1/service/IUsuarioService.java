package com.pe.cibertec.app_evaluacion_t1.service;

import com.pe.cibertec.app_evaluacion_t1.model.bd.Usuario;
import java.util.List;

public interface IUsuarioService {
    List<Usuario> listarUsuario();
    Usuario buscarUsuarioXNomUsuario(String nomusuario);
    Usuario buscarUsuarioXIdUsuario(Integer idusuario);
    Usuario guardarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void actualizarPassword(String nomusuario, String nuevaPassword);
}
