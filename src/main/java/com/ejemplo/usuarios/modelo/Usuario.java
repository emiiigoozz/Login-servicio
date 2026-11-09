/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejemplo.usuarios.modelo;

import jakarta.persistence.*;


@Entity 
@Table(name = "usuarios")

public class Usuario {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String nombre;
   private String correo;
   
   @Column(name = "contrasena_hash")
   private String contrasenaHash;
   
   public Integer getId() {
       return id;
   }
   public String getNombre() {
       return nombre;
     
   }
   public void setNombre(String nombre){
       this.nombre = nombre;
       
   }
   public String getCorreo(){
       return correo;
       
   }
   public void setCorreo(String correo){
       this.correo = correo;
       
   }
   public String getContrasenaHash(){
       return contrasenaHash;
       
   }
   public void setContrasenaHash(String contrasenaHash){
       this.contrasenaHash = contrasenaHash;
   }
}
