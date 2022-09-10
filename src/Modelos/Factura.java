/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author negri
 */
public class Factura {
    public String Num_fact;
    public String Cedula_cliente;
    public String Fecha_emision;
    public String Descripcion;
    public String ID;
    public String Codigo;
    public int Cantidad;
    public double Precio;
    public double Subtotal;
    public double Descuento;
    public double Iva;
    public double Total_pago;
    public Factura(){
    }
    public Factura(String num_fact, String cedula_cliente, String fecha_emision, String descripcion, String id, String codigo,
            int cantidad, double subtotal, double precio,
            double descuento, double iva, double total_pago){
        super();
        Num_fact=num_fact;
        Cedula_cliente=cedula_cliente;
        Fecha_emision=fecha_emision;
        Descripcion=descripcion;
        ID=id;
        Codigo=codigo;
        Cantidad=cantidad;
        Precio=precio;
        Subtotal=subtotal;
        Descuento=descuento;
        Iva=iva;
        Total_pago=total_pago;
    }
}
