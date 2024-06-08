package com.pe.cibertec.app_evaluacion_t1.controller;

import com.pe.cibertec.app_evaluacion_t1.model.bd.Usuario;
import com.pe.cibertec.app_evaluacion_t1.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class LoginController {

    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(){
        return "auth/frmlogin";
    }

    @GetMapping("/login-success")
    public String loginSuccess(){
        return "redirect:/auth/dashboard";
    }

    //
    @PostMapping("/guardar-usuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario){
        usuarioService.guardarUsuario(usuario);
        return "/auth/frmlogin";
    }

    @GetMapping("/register")
    public String register(Model modelo){
        Usuario usuario = new Usuario();
        modelo.addAttribute("usuario", usuario);
        return "auth/frmregistro";
    }

    @GetMapping("/cambiar-password")
    public String mostrarCambiarPassword(Model model) {
        return "auth/frmpassword";
    }

    @PostMapping("/cambiar-password")
    public String cambiarPassword(@RequestParam("newPassword") String nuevaPassword, Authentication authentication, RedirectAttributes redirectAttributes) {
        String nomusuario = authentication.getName();
        try {
            usuarioService.actualizarPassword(nomusuario, nuevaPassword);
            redirectAttributes.addFlashAttribute("success", "Contraseña cambiada exitosamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/auth/cambiar-password";
    }
        //

    @GetMapping("/dashboard")
    public String dashboard(){
        return "auth/home";
    }
}