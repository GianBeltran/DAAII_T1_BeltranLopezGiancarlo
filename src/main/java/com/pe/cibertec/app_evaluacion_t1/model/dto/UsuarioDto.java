package com.pe.cibertec.app_evaluacion_t1.model.dto;

import lombok.Data;

@Data
public class UsuarioDto {
    private Integer idusuario;
    private String nomusuario;
    private String nombres;
    private String apellidos;
    private String password;
    private Boolean activo;
    private String email;
}
