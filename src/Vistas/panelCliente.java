/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vistas;

import Controladores.cCliente;
import Modelos.Cliente;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author negri
 */
public class panelCliente extends javax.swing.JPanel {

    //datos globales
    public cCliente list = new cCliente();
    String clave = ""; //guarda el campo clave para editar

    //*******************METODOS PERSONALIZADOS *******************
    /**
     * Leer datos del formulario y guardar en un objeto
     * @return Objeto Estudiante
     */
    
    public Cliente leer() {
        Cliente ob = null;
        if (form_validado()) {
            ob = new Cliente();
            ob.Cedula = txtCedula.getText();
            ob.Nombre = txtNombre.getText();
            ob.Apellido = txtApellido.getText();
            ob.Celular = txtCelular.getText();
            ob.Email = txtEmail.getText();
            ob.Direccion = txtDireccion.getText();
            ob.Ciudad = cbCiudad.getSelectedItem().toString();
            System.out.print(ob.toString());
        }
        return ob;
    }    
    
    /*
     * Validar formulario
     */
    public boolean form_validado() {
        boolean ok = true;
        String men = "Campos con errores";
        //validar requerido
        if (!Validaciones.esCedula(txtCedula)) {
            ok = false;
            men += ", Cedula";
        }

        if (!Validaciones.esLetras(txtNombre)) {
            ok = false;
            men += ", Nombre";
        }

        if (!Validaciones.esLetras(txtApellido)) {
            ok = false;
            men += ", Apellido";
        }
        
        if (!Validaciones.esTelefono(txtCelular)) {
            ok = false;
            men += ", Celular";
        }
        
        if (!Validaciones.esEmail(txtEmail)) {
            ok = false;
            men += ", Email";
        }
        
        if (!Validaciones.esRequerido(txtDireccion)) {
            ok = false;
            men += ", Dirección";
        }
        
        if (!Validaciones.esTelefono(txtCelular)) {
            ok = false;
            men += ", Celular";
        }
        
        if (!Validaciones.esCiudad(cbCiudad)) {
            ok = false;
            men += ", Ciudad";
        }
        
        if (!ok) {
            frmPrincipal.lbMensaje.setText(men);
        } else {
            frmPrincipal.lbMensaje.setText("");
        }
        //validar más controles
        return ok;
    }
    
    /*
     * Metodo para limpiar cajas de texto
     */
    public void limpiar_textos() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCelular.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
        cbCiudad.setSelectedItem("-----------------");
        txtCedula.requestFocus();  //envia curso o enfoque a la caja de texto cedula
        frmPrincipal.lbMensaje.setText("");
    }
    
    /*
     * quitar validaciones
     */
    public void quitar_validaciones() {
        Validaciones.pinta_text(txtCedula);
        Validaciones.pinta_text(txtNombre);
        Validaciones.pinta_text(txtApellido);
        Validaciones.pinta_text(txtCelular);
        Validaciones.pinta_text(txtEmail);
        Validaciones.pinta_text(txtDireccion);
        Validaciones.pinta_text(cbCiudad);
        frmPrincipal.lbMensaje.setText("");
    }
    
    /*
     * Ver registro
     */
    public final void ver_registro(int pos) {
        if (pos >= 0 && pos < list.Count()) {
            Cliente ob = list.getCliente(pos);
            txtCedula.setText(ob.Cedula);
            txtNombre.setText(ob.Nombre);
            txtApellido.setText(ob.Apellido);
            txtCelular.setText(ob.Celular);
            txtDireccion.setText(ob.Direccion);
            txtEmail.setText(ob.Email);
            cbCiudad.setSelectedItem(ob.Ciudad);
            /*if (ob.Genero.equals("Femenino")) {
                rbFemenino.setSelected(true);
            } else {
                rbMasculino.setSelected(true);
            }*/
        }
    }
    
    /*
     * Buscar datos segun cedula
     */
    public void buscar() {
        try {
            int pos = list.localizar(txtCedula.getText());
            if (pos > -1) {
                ver_registro(pos);
            } else {
                frmPrincipal.lbMensaje.setText("Registro no encontrado");
            }
        } catch (Exception ex) {
            frmPrincipal.lbMensaje.setText(ex.getMessage());
        }
    }
    
    /*
     * Guardar estudiante, cuando es nuevo o se modifica un existente
     */
    public void guardar() {
        Cliente ob = leer();
        try {
            if (ob != null) {
                if (clave.equals("")) {//guardar un nuevo estudiante
                    list.nuevo(ob);
                } else {//guardar datos de estudiante editado
                    list.modificar(ob, clave);
                }
                frmPrincipal.lbMensaje.setText("Registro guardado exitosamente");
                list.guardar(); //guarda en el archivo csv
                tabla.setModel(list.getTabla());
                //deshabilitar textos
                habilitar_textos(false);
                //habilitar botones
                habilitar_botones(true);
            }
        } catch (IOException ex) {
            frmPrincipal.lbMensaje.setText(ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
    
    /*
     * Buscar datos por criterios cedula y apellido
     */
    public void buscar_varios() {
        try {
            cCliente p = list.buscar_cedula(txtDato.getText()); //busca por cedula
            if (p.Count() == 0) {
                p = list.buscar_apellido(txtDato.getText()); //buscar por apellido
            }
            tabla.setModel(p.getTabla());
        } catch (IOException ex) {
            frmPrincipal.lbMensaje.setText(ex.getMessage());
        }
    }
    
    /*
     * Eliminar datos de un cliente
     */
    public void eliminar() {
        try {
            list.eliminar(txtCedula.getText());
            list.guardar();
            tabla.setModel(list.getTabla());
            frmPrincipal.lbMensaje.setText("Registro eliminado");
        } catch (IOException ex) {
            frmPrincipal.lbMensaje.setText(ex.getMessage());
        }
    }
    
    /*
     * Habilitar o desabilitar textos
     */
    public final void habilitar_textos(Boolean ok) {
        txtCedula.setEditable(ok);
        txtNombre.setEditable(ok);
        txtApellido.setEditable(ok);
        txtCelular.setEditable(ok);
        txtEmail.setEditable(ok);
        txtDireccion.setEditable(ok);
        cbCiudad.setEditable(ok);
    }
    
    /*
     * Habilitar o desabilitar botones
     */
    public final void habilitar_botones(Boolean ok) {
        btNuevo.setEnabled(ok);
        btEditar.setEnabled(ok);
        //btBuscar.setEnabled(ok);
        btEliminar.setEnabled(ok);

        //hacen lo contrario de los otros botones
        btGuardar.setEnabled(!ok);
        btCancelar.setEnabled(!ok);
    }
    
    /**
     * Creates new form panelEstudiante
     */
    public panelCliente() {
        initComponents();
        try {
            list.leer();
        } catch (IOException ex) {
            Logger.getLogger(panelCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
		//formato de la tabla
	tabla.getTableHeader().setBackground(new Color(100, 200, 200));
        tabla.getTableHeader().setForeground(Color.BLACK);
        //cargar datos en la tabla
        tabla.setModel(list.getTabla());
        if (list.Count()>0)
            ver_registro(1); //muestra el primer registro u objeto del arreglo
        //desabilitar textos
        habilitar_textos(false);
        //habilitar botones
        habilitar_botones(true);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Cliente = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        cbCiudad = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btEditar = new javax.swing.JButton();
        btNuevo = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();
        btListar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtDato = new javax.swing.JTextField();
        btBuscar = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Cedula");

        jLabel2.setText("Ciudad");

        jLabel3.setText("Direccion");

        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedulaKeyReleased(evt);
            }
        });

        cbCiudad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-----------------", "Machala", "Quito", "Pasaje", "Cuenca" }));
        cbCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCiudadActionPerformed(evt);
            }
        });
        cbCiudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbCiudadKeyReleased(evt);
            }
        });

        jLabel5.setText("Nombre");

        jLabel6.setText("Apellidos");

        jLabel7.setText("Celular");

        jLabel8.setText("Email");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        txtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoActionPerformed(evt);
            }
        });
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidoKeyReleased(evt);
            }
        });

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        txtCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCelularActionPerformed(evt);
            }
        });
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCelularKeyReleased(evt);
            }
        });

        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/modificar.png"))); // NOI18N
        btEditar.setToolTipText("Editar");
        btEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btEditar.setMaximumSize(new java.awt.Dimension(80, 80));
        btEditar.setMinimumSize(new java.awt.Dimension(80, 80));
        btEditar.setPreferredSize(new java.awt.Dimension(80, 80));
        btEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

        btNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/nuevo.png"))); // NOI18N
        btNuevo.setToolTipText("Nuevo");
        btNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btNuevo.setMaximumSize(new java.awt.Dimension(80, 80));
        btNuevo.setMinimumSize(new java.awt.Dimension(80, 80));
        btNuevo.setPreferredSize(new java.awt.Dimension(80, 80));
        btNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevoActionPerformed(evt);
            }
        });

        btEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/eliminar.png"))); // NOI18N
        btEliminar.setToolTipText("Eliminar");
        btEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btEliminar.setMaximumSize(new java.awt.Dimension(80, 80));
        btEliminar.setMinimumSize(new java.awt.Dimension(80, 80));
        btEliminar.setPreferredSize(new java.awt.Dimension(80, 80));
        btEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarActionPerformed(evt);
            }
        });

        btListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/tabla.png"))); // NOI18N
        btListar.setToolTipText("Listar");
        btListar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btListar.setMaximumSize(new java.awt.Dimension(80, 80));
        btListar.setMinimumSize(new java.awt.Dimension(80, 80));
        btListar.setPreferredSize(new java.awt.Dimension(80, 80));
        btListar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btListarActionPerformed(evt);
            }
        });

        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/undo.png"))); // NOI18N
        btCancelar.setToolTipText("Cancelar");
        btCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCancelar.setMaximumSize(new java.awt.Dimension(80, 80));
        btCancelar.setMinimumSize(new java.awt.Dimension(80, 80));
        btCancelar.setPreferredSize(new java.awt.Dimension(80, 80));
        btCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/save.png"))); // NOI18N
        btGuardar.setToolTipText("Guardar");
        btGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btGuardar.setMaximumSize(new java.awt.Dimension(80, 80));
        btGuardar.setMinimumSize(new java.awt.Dimension(80, 80));
        btGuardar.setPreferredSize(new java.awt.Dimension(80, 80));
        btGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btListar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btListar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Categoria", "Categoria Principal", "Subcategoria"
            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jLabel4.setText("DATO A BUSCAR:");

        txtDato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDatoActionPerformed(evt);
            }
        });
        txtDato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDatoKeyReleased(evt);
            }
        });

        btBuscar.setBackground(new java.awt.Color(204, 255, 153));
        btBuscar.setText("Buscar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ClienteLayout = new javax.swing.GroupLayout(Cliente);
        Cliente.setLayout(ClienteLayout);
        ClienteLayout.setHorizontalGroup(
            ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClienteLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ClienteLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btBuscar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ClienteLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        ClienteLayout.setVerticalGroup(
            ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ClienteLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btBuscar)
                    .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        add(Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 705, 575));
    }// </editor-fold>//GEN-END:initComponents

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed

    }//GEN-LAST:event_txtCedulaActionPerformed

    private void txtCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyReleased
        if (Validaciones.esCedula(txtCedula))
        Validaciones.pinta_text(txtCedula);
    }//GEN-LAST:event_txtCedulaKeyReleased

    private void cbCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCiudadActionPerformed

    }//GEN-LAST:event_cbCiudadActionPerformed

    private void cbCiudadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbCiudadKeyReleased
        if (Validaciones.esCiudad(cbCiudad))
        Validaciones.pinta_text(cbCiudad);
    }//GEN-LAST:event_cbCiudadKeyReleased

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed

    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        if (Validaciones.esLetras(txtNombre))
        Validaciones.pinta_text(txtNombre);
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed

    }//GEN-LAST:event_txtApellidoActionPerformed

    private void txtApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyReleased
        if (Validaciones.esLetras(txtApellido))
        Validaciones.pinta_text(txtApellido);
    }//GEN-LAST:event_txtApellidoKeyReleased

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed

    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        if (Validaciones.esEmail(txtEmail))
        Validaciones.pinta_text(txtEmail);
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCelularActionPerformed

    }//GEN-LAST:event_txtCelularActionPerformed

    private void txtCelularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyReleased
        if (Validaciones.esTelefono(txtCelular))
        Validaciones.pinta_text(txtCelular);
    }//GEN-LAST:event_txtCelularKeyReleased

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed

    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        if (Validaciones.esRequerido(txtDireccion))
        Validaciones.pinta_text(txtDireccion);
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        if (!txtCedula.getText().trim().equals("")) {
            clave = txtCedula.getText().trim(); //captura la cédula antes de modificar
            txtCedula.requestFocus();  //envia curso o enfoque a la caja de texto cedula
            frmPrincipal.lbMensaje.setText("");
            //habilitar textos
            habilitar_textos(true);
            //deshabilitar botones
            habilitar_botones(false);
            quitar_validaciones();
        } else
        frmPrincipal.lbMensaje.setText("Seleccione o busque un registro a editar");
    }//GEN-LAST:event_btEditarActionPerformed

    private void btNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoActionPerformed
        quitar_validaciones();
        limpiar_textos();
        clave = ""; //nuevo
        //habilitar textos
        habilitar_textos(true);
        //deshabilitar botones
        habilitar_botones(false);
    }//GEN-LAST:event_btNuevoActionPerformed

    private void btEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminarActionPerformed
        if (!txtCedula.getText().trim().equals("")) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminiar este registro?", "Sistema Académico", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                eliminar();
            }
        } else
        frmPrincipal.lbMensaje.setText("Seleccione el registro a eliminar");
    }//GEN-LAST:event_btEliminarActionPerformed

    private void btListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btListarActionPerformed
        tabla.setModel(list.getTabla());
        tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
        frmPrincipal.lbMensaje.setText("");
    }//GEN-LAST:event_btListarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        //quitar validaciones
        quitar_validaciones();
        //desabilitar textos
        habilitar_textos(false);
        //habilitar botones
        habilitar_botones(true);
        //cargar registro anterior a la modificación
        int pos = list.localizar(clave);
        if (pos >= 0)
        ver_registro(pos);
        else
        limpiar_textos();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btGuardarActionPerformed

    private void tablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyReleased
        int pos = tabla.getSelectedRow();
        if (pos >= 0 && pos < list.Count())
            ver_registro(pos);
    }//GEN-LAST:event_tablaKeyReleased

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        int pos = tabla.getSelectedRow();
        if (pos >= 0 && pos < list.Count())
            ver_registro(pos);
    }//GEN-LAST:event_tablaMouseClicked

    private void txtDatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDatoActionPerformed

    private void txtDatoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatoKeyReleased
        buscar_varios();
    }//GEN-LAST:event_txtDatoKeyReleased

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        buscar_varios();
    }//GEN-LAST:event_btBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cliente;
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btEliminar;
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btListar;
    private javax.swing.JButton btNuevo;
    private javax.swing.JComboBox<String> cbCiudad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtDato;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
