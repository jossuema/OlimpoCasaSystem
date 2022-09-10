/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vistas;
import Controladores.cProducto;
import Modelos.Producto;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author negri
 */
public class panelProducto extends javax.swing.JPanel {

    //datos globales
    public cProducto list = new cProducto();
    String clave = ""; //guarda el campo clave para editar

    //*******************METODOS PERSONALIZADOS *******************
    /**
     * Leer datos del formulario y guardar en un objeto
     * @return Objeto Estudiante
     */
    
    public Producto leer() {
        Producto ob = null;
        if (form_validado()) {
            ob = new Producto();
            ob.Tipo_prenda = cbTipoPrenda.getSelectedItem().toString();
            ob.Codigo_producto = txtCodigo.getText();
            ob.Id_categoria = txtID.getText();
            ob.Marca = txtMarca.getText();
            ob.Descripcion = txtDescripcion.getText();
            ob.Precio_venta = Double.valueOf(txtPrecio.getText());
            ob.Stock = Integer.valueOf(txtStock.getText());
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
        
        if (!Validaciones.esPrenda(cbTipoPrenda)) {
            ok = false;
            men += ", Tipo de prenda";
        }
        
        if (!Validaciones.esCedula(txtCodigo)) {
            ok = false;
            men += ", Codigo";
        }
        
        if (!Validaciones.esNumero(txtID)) {
            ok = false;
            men += ", ID Categoria";
        }
        
        if (!Validaciones.esLetras(txtMarca)) {
            ok = false;
            men += ", Marca";
        }
        
        if (!Validaciones.esLetras(txtDescripcion)) {
            ok = false;
            men += ", Descripcion";
        }
        
        if (!Validaciones.esFlotante(txtPrecio)) {
            ok = false;
            men += ", Precio";
        }
        
        if (!Validaciones.esNumero(txtStock)) {
            ok = false;
            men += ", Stock";
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
        cbTipoPrenda.setSelectedItem("-----------------");
        txtCodigo.setText("");
        txtID.setText("");
        txtMarca.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txtCodigo.requestFocus();  //envia curso o enfoque a la caja de texto cedula
        frmPrincipal.lbMensaje.setText("");
    }
    
    /*
     * quitar validaciones
     */
    public void quitar_validaciones() {
        Validaciones.pinta_text(cbTipoPrenda);
        Validaciones.pinta_text(txtCodigo);
        Validaciones.pinta_text(txtID);
        Validaciones.pinta_text(txtMarca);
        Validaciones.pinta_text(txtDescripcion);
        Validaciones.pinta_text(txtPrecio);
        Validaciones.pinta_text(txtStock);
        Validaciones.pinta_text(txtPrecio);
        frmPrincipal.lbMensaje.setText("");
    }
    
    /*
     * Ver registro
     */
    public final void ver_registro(int pos) {
        if (pos >= 0 && pos < list.Count()) {
            Producto ob = list.getProducto(pos);
            
            cbTipoPrenda.setSelectedItem(ob.Tipo_prenda);
            txtCodigo.setText(ob.Codigo_producto);
            txtID.setText(ob.Id_categoria);
            txtMarca.setText(ob.Marca);
            txtDescripcion.setText(ob.Descripcion);
            txtPrecio.setText(String.valueOf(ob.Precio_venta));
            txtStock.setText(String.valueOf(ob.Stock));
        }
    }
    
    /*
     * Buscar datos segun cedula
     */
    public void buscar() {
        try {
            int pos = list.localizar(txtID.getText());
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
        Producto ob = leer();
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
            cProducto p = list.buscar_codigo(txtDato.getText()); //busca por cedula
            if (p.Count() == 0) {
                p = list.buscar_idproducto(txtDato.getText()); //buscar por apellido
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
            list.eliminar(txtID.getText());
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
        cbTipoPrenda.setEditable(ok);
        txtCodigo.setEditable(ok);
        txtID.setEditable(ok);
        txtMarca.setEditable(ok);
        txtDescripcion.setEditable(ok);
        txtPrecio.setEditable(ok);
        txtStock.setEditable(ok);
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
    public panelProducto() {
        initComponents();
        try {
            list.leer();
        } catch (IOException ex) {
            Logger.getLogger(panelProducto.class.getName()).log(Level.SEVERE, null, ex);
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cbTipoPrenda = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btEditar = new javax.swing.JButton();
        btNuevo = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();
        btListar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btGuardar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        txtDato = new javax.swing.JTextField();
        btBuscar = new javax.swing.JButton();

        jLabel10.setText("Tipo de prenda");

        cbTipoPrenda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "------------------", "Camiseta", "Chompa", "Pantalones", "Zapatos", " " }));
        cbTipoPrenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoPrendaActionPerformed(evt);
            }
        });
        cbTipoPrenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbTipoPrendaKeyReleased(evt);
            }
        });

        jLabel1.setText("Codigo");

        jLabel2.setText("ID Producto");

        jLabel3.setText("Marca");

        jLabel4.setText("Descripcion");

        jLabel5.setText("Precio");

        jLabel6.setText("Stock");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        txtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIDKeyReleased(evt);
            }
        });

        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMarcaKeyReleased(evt);
            }
        });

        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });

        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioKeyReleased(evt);
            }
        });

        txtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStockKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(76, 76, 76)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbTipoPrenda, 0, 130, Short.MAX_VALUE)
                    .addComponent(txtCodigo)
                    .addComponent(txtID)
                    .addComponent(txtMarca)
                    .addComponent(txtDescripcion)
                    .addComponent(txtPrecio)
                    .addComponent(txtStock))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbTipoPrenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel7.setText("DATO A BUSCAR");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "ID Categoria", "Tipo de prenda", "Marca", "Descripcion", "Precio", "Stock"
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(28, 28, 28)
                            .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(btBuscar))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btBuscar)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbTipoPrendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoPrendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoPrendaActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        if (!txtCodigo.getText().trim().equals("")) {
            clave = txtCodigo.getText().trim(); //captura el codigo antes de modificar
            txtCodigo.requestFocus();  //envia curso o enfoque a la caja de texto cedula
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
        if (!txtCodigo.getText().trim().equals("")) {
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

    private void txtDatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDatoActionPerformed

    private void cbTipoPrendaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbTipoPrendaKeyReleased
        if (Validaciones.esPrenda(cbTipoPrenda))
        Validaciones.pinta_text(cbTipoPrenda);
    }//GEN-LAST:event_cbTipoPrendaKeyReleased

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (Validaciones.esCedula(txtCodigo))
            Validaciones.pinta_text(txtCodigo);
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDKeyReleased
        if (Validaciones.esNumero(txtID))
            Validaciones.pinta_text(txtID);
    }//GEN-LAST:event_txtIDKeyReleased

    private void txtMarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyReleased
        if (Validaciones.esLetras(txtMarca))
            Validaciones.pinta_text(txtMarca);
    }//GEN-LAST:event_txtMarcaKeyReleased

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        if (Validaciones.esLetras(txtDescripcion))
            Validaciones.pinta_text(txtDescripcion);
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void txtPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyReleased
        if (Validaciones.esFlotante(txtPrecio))
            Validaciones.pinta_text(txtPrecio);
    }//GEN-LAST:event_txtPrecioKeyReleased

    private void txtStockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyReleased
        if (Validaciones.esNumero(txtStock))
            Validaciones.pinta_text(txtStock);
    }//GEN-LAST:event_txtStockKeyReleased

    private void tablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyReleased
        int pos = tabla.getSelectedRow();
        if (pos >= 0 && pos < list.Count())
            ver_registro(pos);
    }//GEN-LAST:event_tablaKeyReleased

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        int pos = tabla.getSelectedRow();
        if (pos >= 0 && pos < list.Count())
            ver_registro(pos);// TODO add your handling code here:
    }//GEN-LAST:event_tablaMouseClicked

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        buscar_varios();
    }//GEN-LAST:event_btBuscarActionPerformed

    private void txtDatoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatoKeyReleased
        buscar_varios();
    }//GEN-LAST:event_txtDatoKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btEliminar;
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btListar;
    private javax.swing.JButton btNuevo;
    private javax.swing.JComboBox<String> cbTipoPrenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDato;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
