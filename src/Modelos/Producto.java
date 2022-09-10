/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author negri
 */
public class Producto {
    public String Codigo_producto;
    public String Tipo_prenda;
    public String Marca;
    public String Descripcion;
    public double Precio_venta;
    public int Stock;
    public String Id_categoria;
    
    public Producto(){
    }
    public Producto(String codigo_producto, String tipo_prenda, String marca, String descripcion, 
            double precio_venta, int stock, String id_categoria){
        super();
        Codigo_producto=codigo_producto;
        Tipo_prenda=tipo_prenda;
        Marca=marca;
        Descripcion=descripcion;
        Precio_venta=precio_venta;
        Stock=stock;
        Id_categoria=id_categoria;
    }
}
