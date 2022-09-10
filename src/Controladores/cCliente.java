/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

/**
 * Controlador de clientes *
 * @author Josue Malla
 * @date 2022/09/03
 * @version 1.0
 */

import Modelos.Cliente;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

public class cCliente {

    //arreglo dinamico de objetos. Arraylist es una coleccion predefinida en JAVA
    ArrayList<Cliente> Lista = new ArrayList<>();
    

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
     * Registra un nuevo cliente
     *
     * @param e
     * @throws java.io.IOException
     */
    public void nuevo(Cliente e) throws IOException {
        int pos = localizar(e.Cedula);
        if (pos == -1) {//si cedula no esta registrada, se agrega nuevo cliente
            Lista.add(e);
        } else {
            throw new RuntimeException("# de C�dula ya asignado a otro cliente");
        }
    }

    /**
     * Modificar datos de un cliente existente
     *
     * @param e
     * @param ced
     * @throws java.io.IOException
     */
    public void modificar(Cliente e, String ced) throws IOException {
        int pos = localizar(ced);
        if (pos > -1) {//si el cliente esta registrado se modifica
            Lista.set(pos, e);
        } else {
            throw new RuntimeException("No existe un cliente registrado con la cedula ingresada");
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
        if (pos > -1) {//si cliente esta registrado se elimina
            Lista.remove(pos);
        } else {
            throw new RuntimeException("No existe un cliente registrado con la cedula ingresada");
        }
    }

    /**
     * Lista datos en un defaultablemodel para presentar en una tabla
     *
     * @return
     */
    public DefaultTableModel getTabla() {
        //encabezados de columnas de la tabla
        String[] columnName = {"No.", "Cedula", "Nombres", "Apellidos", "Celular", "Email", "Direccion", "Ciudad"};        
        DefaultTableModel tabla = new DefaultTableModel(columnName, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (int i = 0; i < Lista.size(); i++) {
            Cliente e = getCliente(i);
            Object[] row = {
                (i + 1), e.Cedula, e.Nombre, e.Apellido, e.Celular,
                e.Email, e.Direccion, e.Ciudad
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
    public int localizar(String cedula) {
        int pos = -1; //se retorna -1 si no se encuentra en el arreglo
        for (int i = 0; i < Lista.size(); i++) {
            Cliente e = getCliente(i);
            if (cedula.equals(e.Cedula)) {
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
    public cCliente buscar_cedula(String cedula) throws IOException {
        cCliente ob = new cCliente();
        for (int i = 0; i < Lista.size(); i++) {
            Cliente e = getCliente(i);
            if (e.Cedula.toLowerCase().startsWith(cedula)) {
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
    public cCliente buscar_apellido(String apellido) throws IOException {
        cCliente ob = new cCliente();
        for (int i = 0; i < Lista.size(); i++) {
            Cliente e = getCliente(i);
            if (e.Apellido.toLowerCase().startsWith(apellido)) {
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
    public Cliente getCliente(int pos) {
        Cliente ob = null;
        if (pos >= 0 && pos < Lista.size()) {
            ob = Lista.get(pos);
        }
        return ob;
    }

    public static final String SEPARADOR = ";";
    public static final String QUOTE = "\"";
    //nombre del archivo csv
    public String path = Global.getPath() + "\\Data\\dataCliente.csv";

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
                Cliente ob = new Cliente();
                ob.Cedula = row[0];
                ob.Nombre = row[1];
                ob.Apellido = row[2];
                ob.Celular = row[3];
                ob.Direccion = row[4];
                ob.Email = row[5];
                ob.Ciudad = row[6];
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
            file.append("Cedula").append(SEPARADOR);
            file.append("Nombre").append(SEPARADOR);
            file.append("Apellido").append(SEPARADOR);
            file.append("Celular").append(SEPARADOR);
            file.append("Email").append(SEPARADOR);
            file.append("Direccion").append(SEPARADOR);
            file.append("Ciudad").append(NEXT_LINE);

            for (int i = 0; i < Lista.size(); i++) {
                Cliente ob = (Cliente) Lista.get(i);
                file.append(ob.Cedula).append(SEPARADOR);
                file.append(ob.Nombre).append(SEPARADOR);
                file.append(ob.Apellido).append(SEPARADOR);
                file.append(ob.Celular).append(SEPARADOR);
                file.append(ob.Direccion).append(SEPARADOR);
                file.append(ob.Email).append(SEPARADOR);
                file.append(ob.Ciudad).append(NEXT_LINE);
            }
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }
}

