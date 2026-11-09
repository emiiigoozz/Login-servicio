/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejemplo.usuarios.servicio;

import com.ejemplo.usuarios.dto.UsuarioDatos;
import com.ejemplo.usuarios.modelo.Usuario;
import com.ejemplo.usuarios.repositorio.UsuarioRepository;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
/**
 *
 * @author Emilio
 */
public class UsuarioService {
    private final UsuarioRepository repositorio;
    private final BCryptPasswordEncoder cifrado=new BCryptPasswordEncoder();
    
   public UsuarioService(UsuarioRepository repositorio){
       this.repositorio=repositorio;
   }
   public Usuario registrar(UsuarioDatos datos) {
       if(vacio(datos.getNombre()) || vacio (datos.getCorreo())|| vacio(datos.getContrasena())) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nombre, correo y contrasena son obligatorios");
       }
   
   String correo = datos.getCorreo().trim().toLowerCase();
   
   if (repositorio.findByCorreo(correo).isPresent()){
       throw new ResponseStatusException(HttpStatus.CONFLICT, "El correo ya se registro");
   }
   Usuario usuario = new Usuario();
   usuario.setNombre(datos.getNombre().trim());
   usuario.setCorreo(correo);
   usuario.setContrasenaHash(cifrado.encode(datos.getContrasena()));
   

   return repositorio.save(usuario);
   }
   public Usuario login(UsuarioDatos datos) {
       if(vacio(datos.getCorreo())|| vacio(datos.getContrasena())){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CORREO Y CONTRASENA SON DE A HUEVO");
       }
       
       String correo = datos.getCorreo().trim().toLowerCase();
       Optional<Usuario> encontrado = repositorio.findByCorreo(correo);
       
       if (encontrado.isEmpty()
               || !cifrado.matches(datos.getContrasena(), encontrado.get().getContrasenaHash())){
           throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "CORREO O CONTRASENA SON INCORRECT");
       }
       return encontrado.get();
   }
   
   private boolean vacio (String texto){
       return texto == null || texto.isBlank();
   }
}
