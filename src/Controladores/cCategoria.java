/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

/**
 * Controlador de categoria *
 * @author Josue Malla
 * @date 2022/09/03
 * @version 1.0
 */

import Modelos.Categoria;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

public class cCategoria {

    //arreglo dinamico de objetos. Arraylist es una coleccion predefinida en JAVA
    ArrayList<Categoria> Lista = new ArrayList<>();
    

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
    public void nuevo(Categoria e) throws IOException {
        int pos = localizar(e.Id_categoria);
        if (pos == -1) {//si cedula no esta registrada, se agrega nuevo estudiante
            Lista.add(e);
        } else {
            throw new RuntimeException("# ID ya asignado a otra categoria");
        }
    }

    /**
     * Modificar datos de una categoria existente
     *
     * @param e
     * @param id
     * @param ced
     * @throws java.io.IOException
     */
    public void modificar(Categoria e, String id) throws IOException {
        int pos = localizar(id);
        if (pos > -1) {//si estudiante esta registrado se modifica
            Lista.set(pos, e);
        } else {
            throw new RuntimeException("No existe una categoria registrada con la ID ingresada");
        }
    }

    /**
     * Eliminar una categoria
     *
     * @param Id_categoria
     * @throws java.io.IOException
     */
    public void eliminar(String Id_categoria) throws IOException {
        int pos = localizar(Id_categoria);
        if (pos > -1) {//si categoria esta registrado se elimina
            Lista.remove(pos);
        } else {
            throw new RuntimeException("No existe una categoria registrada con el ID ingresado");
        }
    }

    /**
     * Lista datos en un defaultablemodel para presentar en una tabla
     *
     * @return
     */
    public DefaultTableModel getTabla() {
        //encabezados de columnas de la tabla
        String[] columnName = {"No.", "ID Categoria", "Categoria principal", "Categoria secundaria"};        
        DefaultTableModel tabla = new DefaultTableModel(columnName, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (int i = 0; i < Lista.size(); i++) {
            Categoria e = getCategoria(i);
            Object[] row = {
                (i + 1), e.Id_categoria, e.Categoria_principal, e.Sub_categoria
            };
            tabla.addRow(row);
        }
        return tabla;
    }

    /**
     * Algoritmo de busqueda secuencial en el arreglo Lista
     *
     * @param ID
     * @return posicion en el arreglo del estudiante encontrado
     */
    public int localizar(String ID) {
        int pos = -1; //se retorna -1 si no se encuentra en el arreglo
        for (int i = 0; i < Lista.size(); i++) {
            Categoria e = getCategoria(i);
            if (ID.equals(e.Id_categoria)) {
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
     * @param ID
     * @return cEstudiante
     * @throws IOException
     */
    public cCategoria buscar_id(String ID) throws IOException {
        cCategoria ob = new cCategoria();
        for (int i = 0; i < Lista.size(); i++) {
            Categoria e = getCategoria(i);
            if (e.Id_categoria.toLowerCase().startsWith(ID)) {
                ob.nuevo(e);
            }
        }
        return ob;
    }

    /**
     * Algoritmo de busqueda secuencial mediante criterio apellido parcial en el
     * arreglo Lista
     *
     * @param categoria_principal
     * @return cEstudiante
     * @throws IOException
     */
    public cCategoria buscar_categoriaprincipal(String categoria_principal) throws IOException {
        cCategoria ob = new cCategoria();
        for (int i = 0; i < Lista.size(); i++) {
            Categoria e = getCategoria(i);
            if (e.Categoria_principal.toLowerCase().startsWith(categoria_principal)) {
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
    public Categoria getCategoria(int pos) {
        Categoria ob = null;
        if (pos >= 0 && pos < Lista.size()) {
            ob = Lista.get(pos);
        }
        return ob;
    }

    public static final String SEPARADOR = ";";
    public static final String QUOTE = "\"";
    //nombre del archivo csv
    public String path = Global.getPath() + "\\Data\\dataCategoria.csv";

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
                Categoria ob = new Categoria();
                ob.Id_categoria = row[0];
                ob.Categoria_principal = row[1];
                ob.Sub_categoria = row[2];
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
            file.append("ID categoria").append(SEPARADOR);
            file.append("Categoria principal").append(SEPARADOR);
            file.append("Categoria secundaria").append(NEXT_LINE);

            for (int i = 0; i < Lista.size(); i++) {
                Categoria ob = (Categoria) Lista.get(i);
                file.append(ob.Id_categoria).append(SEPARADOR);
                file.append(ob.Categoria_principal).append(SEPARADOR);
                file.append(ob.Sub_categoria).append(NEXT_LINE);
            }
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }
}


