/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author negri
 */
public class Cliente {
    public String Cedula;
    public String Nombre;
    public String Apellido;
    public String Celular;
    public String Direccion;
    public String Email;
    public String Ciudad;
    
    public Cliente(){}
    public Cliente(String cedula, String nombre, String apellido, String celular, String direccion, String email, String ciudad){
        Cedula = cedula;
        Nombre = nombre;
        Apellido = apellido;
        Celular = celular;
        Direccion = direccion;
        Email = email;
        Ciudad = ciudad;
    }
}
