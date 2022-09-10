/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

/**
 * Controlador de estudiantes *
 * @author Bertha Mazon
 * @date 2018/08/04
 * @version 1.0
 */

import Modelos.Factura;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

public class cFactura {

    //arreglo dinamico de objetos. Arraylist es una coleccion predefinida en JAVA
    ArrayList<Factura> Lista = new ArrayList<>();
    

    /**
     * Retorna la cantidad de objetos del arreglo
     *
     * @return
     */
    public int Count() {
        return Lista.size();
    }

    /**
     * Borrar todos los elementos del arreglo
     */
    public void Clear() {
        Lista.clear();
    }

    /**
     * Registra un nuevo estudiante
     *
     * @param e
     * @throws java.io.IOException
     */
    public void nuevo(Factura e) throws IOException {
        int pos = localizar(e.Num_fact);
        if (pos == -1) {
            Lista.add(e);
        } else {
            throw new RuntimeException("# de C�dula ya asignado a otro estudiante");
        }
    }

    public void Cal_subtotal(Factura e) throws Exception{
        
    }
    /**
     * Modificar datos de un estudiante existente
     *
     * @param e
     * @param ced
     * @throws java.io.IOException
     */
    public void modificar(Factura e, String ced) throws IOException {
        int pos = localizar(ced);
        if (pos > -1) {//si estudiante esta registrado se modifica
            Lista.set(pos, e);
        } else {
            throw new RuntimeException("No existe un estudiante registrado con la cedula ingresada");
        }
    }

    /**
     * Eliminar un estudiante
     *
     * @param cedula
     * @throws java.io.IOException
     */
    public void eliminar(String cedula) throws IOException {
        int pos = localizar(cedula);
        if (pos > -1) {//si estudiante esta registrado se elimina
            Lista.remove(pos);
        } else {
            throw new RuntimeException("No existe un estudiante registrado con la cedula ingresada");
        }
    }

    /**
     * Lista datos en un defaultablemodel para presentar en una tabla
     *
     * @return
     */
    public DefaultTableModel getTabla() {
        //encabezados de columnas de la tabla
        String[] columnName = {"No.", "N. Factura", "ID", "Codigo Producto", "Cedula", "Fecha", "Cantidad", "Descripcion", "Precio Unitario", "IVA", "Descuento", "Subtotal", "Total"};        
        DefaultTableModel tabla = new DefaultTableModel(columnName, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (int i = 0; i < Lista.size(); i++) {
            Factura e = getFactura(i);
            Object[] row = {
                (i + 1), e.Num_fact, e.ID, e.Codigo, e.Cedula_cliente,
                e.Fecha_emision, e.Cantidad, e.Descripcion, e.Precio, e.Precio, e.Iva, e.Descuento, e.Subtotal, e.Total_pago
            };
            tabla.addRow(row);
        }
        return tabla;
    }

    /**
     * Algoritmo de busqueda secuencial en el arreglo Lista
     *
     * @param cedula
     * @return posicion en el arreglo del estudiante encontrado
     */
    public int localizar(String num_fac) {
        int pos = -1; //se retorna -1 si no se encuentra en el arreglo
        for (int i = 0; i < Lista.size(); i++) {
            Factura e = getFactura(i);
            if (num_fac.equals(e.Num_fact)) {
                pos = i; //posicion encontrada
                break; //finaliza el ciclo for
            }
        }
        return pos;
    }

    /**
     * Algoritmo de busqueda secuencial mediante criterio de cedula parcial en
     * el arreglo Lista
     *
     * @param cedula
     * @return cEstudiante
     * @throws IOException
     */
    public cFactura buscar_numerofac(String num_fac) throws IOException {
        cFactura ob = new cFactura();
        for (int i = 0; i < Lista.size(); i++) {
            Factura e = getFactura(i);
            if (e.Num_fact.toLowerCase().startsWith(num_fac)) {
                ob.nuevo(e);
            }
        }
        return ob;
    }

    /**
     * Algoritmo de busqueda secuencial mediante criterio apellido parcial en el
     * arreglo Lista
     *
     * @param apellido
     * @return cEstudiante
     * @throws IOException
     */
    public cFactura buscar_codigoproducto(String cod_pro) throws IOException {
        cFactura ob = new cFactura();
        for (int i = 0; i < Lista.size(); i++) {
            Factura e = getFactura(i);
            if (e.Codigo.toLowerCase().startsWith(cod_pro)) {
                ob.nuevo(e);
            }
        }
        return ob;
    }

    /**
     * Retornar un objeto del arreglo Lista
     *
     * @param pos es la posicion del objeto en el arreglo
     * @return objeto encontrado
     */
    public Factura getFactura(int pos) {
        Factura ob = null;
        if (pos >= 0 && pos < Lista.size()) {
            ob = Lista.get(pos);
        }
        return ob;
    }

    public static final String SEPARADOR = ";";
    public static final String QUOTE = "\"";
    //nombre del archivo csv
    public String path = Global.getPath() + "\\Data\\dataFactura.csv";

    public void leer() throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            System.out.println("Datos del archivo: ");
            String line = br.readLine();
            System.out.println(line);
            Clear(); //limpiar lista de objetos del arreglo
            line = br.readLine();
            while (line != null) {
                String[] row = line.split(SEPARADOR);
                removeTrailingQuotes(row);
                Factura ob = new Factura();
                ob.Num_fact = row[0];
                ob.ID = row[1];
                ob.Codigo = row[2];
                ob.Cedula_cliente = row[3];
                ob.Fecha_emision = row[4];
                ob.Cantidad = Integer.valueOf(row[5]);
                ob.Descripcion = String.valueOf(row[6]);
                ob.Precio = Double.valueOf(row[7]);
                ob.Iva = Double.valueOf(row[8]);
                ob.Descuento = Double.valueOf(row[9]);
                ob.Subtotal = Double.valueOf(row[10]);
                ob.Total_pago = Double.valueOf(row[11]);
                nuevo(ob);//agregar a la lista	           
                System.out.println(Arrays.toString(row));
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.print(e.getMessage());
        } finally {
            if (null != br) {
                br.close();
            }
        }
    }

    //eliminar comillas
    private static String[] removeTrailingQuotes(String[] fields) {
        String result[] = new String[fields.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = fields[i].replaceAll("^" + QUOTE, "").replaceAll(QUOTE + "$", "");
        }
        return result;
    }

    public void guardar() throws IOException {
        FileWriter file;
        try {
            file = new FileWriter(path);
            final String NEXT_LINE = "\n";
            file.append("N. Factura").append(SEPARADOR);
            file.append("ID").append(SEPARADOR);
            file.append("Codigo producto").append(SEPARADOR);
            file.append("Cedula").append(SEPARADOR);
            file.append("Fecha").append(SEPARADOR);
            file.append("Cantidad").append(SEPARADOR);
            file.append("Descripcion").append(SEPARADOR);
            file.append("Precio Unitario").append(SEPARADOR);
            file.append("IVA").append(SEPARADOR);
            file.append("Descuento").append(SEPARADOR);
            file.append("Subtotal").append(SEPARADOR);
            file.append("Total").append(NEXT_LINE);

            for (int i = 0; i < Lista.size(); i++) {
                Factura ob = (Factura) Lista.get(i);
                file.append(ob.Num_fact).append(SEPARADOR);
                file.append(ob.ID).append(SEPARADOR);
                file.append(ob.Codigo).append(SEPARADOR);
                file.append(ob.Cedula_cliente).append(SEPARADOR);
                file.append(ob.Fecha_emision).append(SEPARADOR);
                file.append(String.valueOf(ob.Cantidad)).append(SEPARADOR);
                file.append(String.valueOf(ob.Descripcion)).append(SEPARADOR);
                file.append(String.valueOf(ob.Precio)).append(SEPARADOR);
                file.append(String.valueOf(ob.Iva)).append(SEPARADOR);
                file.append(String.valueOf(ob.Descuento)).append(SEPARADOR);
                file.append(String.valueOf(ob.Subtotal)).append(SEPARADOR);
                file.append(String.valueOf(ob.Total_pago)).append(NEXT_LINE);
            }
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }
}
