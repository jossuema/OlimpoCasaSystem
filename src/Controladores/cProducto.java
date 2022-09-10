/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

/**
 * Controlador de productos *
 * @author Josue Malla
 * @date 2022/09/03
 * @version 1.0
 */

import Modelos.Producto;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

public class cProducto {

    //arreglo dinamico de objetos. Arraylist es una coleccion predefinida en JAVA
    ArrayList<Producto> Lista = new ArrayList<>();
    

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
     * Registra un nuevo producto
     *
     * @param e
     * @throws java.io.IOException
     */
    public void nuevo(Producto e) throws IOException {
        int pos = localizar(e.Codigo_producto);
        if (pos == -1) {//si cedula no esta registrada, se agrega nuevo estudiante
            Lista.add(e);
        } else {
            throw new RuntimeException("# Codigo de producto ya asignado a otro producto");
        }
    }

    /**
     * Modificar datos de un producto existente
     *
     * @param e
     * @param Codigo_producto
     * @param ced
     * @throws java.io.IOException
     */
    public void modificar(Producto e, String Codigo_producto) throws IOException {
        int pos = localizar(Codigo_producto);
        if (pos > -1) {//si estudiante esta registrado se modifica
            Lista.set(pos, e);
        } else {
            throw new RuntimeException("No existe un producto registrado con el codigo ingresado");
        }
    }

    /**
     * Eliminar un producto
     *
     * @param Codigo_producto
     * @throws java.io.IOException
     */
    public void eliminar(String Codigo_producto) throws IOException {
        int pos = localizar(Codigo_producto);
        if (pos > -1) {//si categoria esta registrado se elimina
            Lista.remove(pos);
        } else {
            throw new RuntimeException("No existe un producto registrado con el Codigo ingresado");
        }
    }

    /**
     * Lista datos en un defaultablemodel para presentar en una tabla
     *
     * @return
     */
    public DefaultTableModel getTabla() {
        //encabezados de columnas de la tabla
        String[] columnName = {"No.", "Codigo", "ID Producto", "Tipo Prenda", "Marca", "Descripcion", "Precio", "Stock"};        
        DefaultTableModel tabla = new DefaultTableModel(columnName, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (int i = 0; i < Lista.size(); i++) {
            Producto e = getProducto(i);
            Object[] row = {
                (i + 1), e.Codigo_producto, e.Id_categoria ,e.Tipo_prenda, e.Marca, e.Descripcion, e.Precio_venta, e.Stock
            };
            tabla.addRow(row);
        }
        return tabla;
    }

    /**
     * Algoritmo de busqueda secuencial en el arreglo Lista
     *
     * @param codigo
     * @return posicion en el arreglo del producto encontrado
     */
    public int localizar(String codigo) {
        int pos = -1; //se retorna -1 si no se encuentra en el arreglo
        for (int i = 0; i < Lista.size(); i++) {
            Producto e = getProducto(i);
            if (codigo.equals(e.Codigo_producto)) {
                pos = i; //posicion encontrada
                break; //finaliza el ciclo for
            }
        }
        return pos;
    }

    /**
     * Algoritmo de busqueda secuencial mediante criterio de codigo parcial en
     * el arreglo Lista
     *
     * @param codigo
     * @return cProducto
     * @throws IOException
     */
    public cProducto buscar_codigo(String codigo) throws IOException {
        cProducto ob = new cProducto();
        for (int i = 0; i < Lista.size(); i++) {
            Producto e = getProducto(i);
            if (e.Id_categoria.toLowerCase().startsWith(codigo)) {
                ob.nuevo(e);
            }
        }
        return ob;
    }

    /**
     * Algoritmo de busqueda secuencial mediante criterio ID PRODUCTO parcial en el
     * arreglo Lista
     *
     * @param categoria_principal
     * @return cProducto
     * @throws IOException
     */
    public cProducto buscar_idproducto(String idproducto) throws IOException {
        cProducto ob = new cProducto();
        for (int i = 0; i < Lista.size(); i++) {
            Producto e = getProducto(i);
            if (e.Id_categoria.toLowerCase().startsWith(idproducto)) {
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
    public Producto getProducto(int pos) {
        Producto ob = null;
        if (pos >= 0 && pos < Lista.size()) {
            ob = Lista.get(pos);
        }
        return ob;
    }

    public static final String SEPARADOR = ";";
    public static final String QUOTE = "\"";
    //nombre del archivo csv
    public String path = Global.getPath() + "\\Data\\dataProducto.csv";

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
                Producto ob = new Producto();
                ob.Tipo_prenda = row[0];
                ob.Codigo_producto = row[1];
                ob.Id_categoria = row[2];
                ob.Marca = row[3];
                ob.Descripcion = row[4];
                ob.Precio_venta = Double.valueOf(row[5]);
                ob.Stock = Integer.valueOf(row[6]);
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
            file.append("Codigo").append(SEPARADOR);
            file.append("ID Producto").append(SEPARADOR);
            file.append("Tipo Prenda").append(SEPARADOR);
            file.append("Marca").append(SEPARADOR);
            file.append("Descripcion").append(SEPARADOR);
            file.append("Precio").append(SEPARADOR);
            file.append("Stock").append(NEXT_LINE);

            for (int i = 0; i < Lista.size(); i++) {
                Producto ob = (Producto) Lista.get(i);
                file.append(ob.Codigo_producto).append(SEPARADOR);
                file.append(ob.Id_categoria).append(SEPARADOR);
                file.append(ob.Tipo_prenda).append(SEPARADOR);
                file.append(ob.Marca).append(SEPARADOR);
                file.append(ob.Descripcion).append(SEPARADOR);
                file.append(String.valueOf(ob.Precio_venta)).append(SEPARADOR);
                file.append(String.valueOf(ob.Stock)).append(NEXT_LINE);
            }
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }
}
