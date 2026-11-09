/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejemplo.usuarios.controlador;
import com.ejemplo.usuarios.dto.UsuarioDatos;
import com.ejemplo.usuarios.modelo.Usuario;
import com.ejemplo.usuarios.servicio.UsuarioService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")

/**
 *
 * @author Emilio
 */
public class UsuarioController {
    private final UsuarioService servicio;
    
    public UsuarioController(UsuarioService servicio) {
        this.servicio = servicio;
    }
    @PostMapping("/registro")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> registrar(@RequestBody UsuarioDatos datos){
        Usuario usuario = servicio.registrar(datos);
        return respuesta(usuario, "Usuario registrado");
    }
     @PostMapping("/login")
     public Map<String, Object> login(@RequestBody UsuarioDatos datos) {
         Usuario usuario=servicio.login(datos);
         return respuesta(usuario, "inicio de sesion correto");
     }
     
     private Map<String, Object> respuesta(Usuario usuario, String mensaje){
         Map<String, Object> datos = new HashMap<>();
         datos.put("id", usuario.getId());
         datos.put("nombre", usuario.getNombre());
         datos.put("correo", usuario.getCorreo());
         datos.put("mensaje", mensaje);
         return datos;

     }


}
