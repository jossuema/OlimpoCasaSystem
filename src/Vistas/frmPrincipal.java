/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import Controladores.Global;
import java.awt.CardLayout;
import javax.swing.ImageIcon;

/**
 *
 * @author negri
 */
public class frmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form frmPrincipal
     */
    public frmPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCentral = new javax.swing.JPanel();
        panelFondo = new javax.swing.JPanel();
        lbFondo = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        btCliente = new javax.swing.JButton();
        btHome = new javax.swing.JButton();
        btProducto = new javax.swing.JButton();
        btCategoria = new javax.swing.JButton();
        btFactura = new javax.swing.JButton();
        panelPie = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbMensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Olimpo Casa");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelCentral.setBackground(new java.awt.Color(255, 255, 255));
        panelCentral.setLayout(new java.awt.CardLayout());

        panelFondo.setBackground(new java.awt.Color(255, 255, 255));
        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbFondo.setBackground(new java.awt.Color(255, 255, 255));
        lbFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondo1.png"))); // NOI18N
        panelFondo.add(lbFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2266, 750));

        panelCentral.add(panelFondo, "Fondo");

        getContentPane().add(panelCentral, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 2266, 750));

        panelMenu.setBackground(new java.awt.Color(0, 0, 0));
        panelMenu.setPreferredSize(new java.awt.Dimension(250, 420));
        panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btCliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCliente.setForeground(new java.awt.Color(255, 255, 255));
        btCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/botonamarillo.png"))); // NOI18N
        btCliente.setText("Clientes");
        btCliente.setBorder(null);
        btCliente.setContentAreaFilled(false);
        btCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCliente.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/botonamarillooscuro.png"))); // NOI18N
        btCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClienteActionPerformed(evt);
            }
        });
        panelMenu.add(btCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 310, 160, 40));

        btHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/olimpopng.png"))); // NOI18N
        btHome.setBorderPainted(false);
        btHome.setContentAreaFilled(false);
        btHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHomeActionPerformed(evt);
            }
        });
        panelMenu.add(btHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 170, 170));

        btProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btProducto.setForeground(new java.awt.Color(255, 255, 255));
        btProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/botonamarillo.png"))); // NOI18N
        btProducto.setText("Producto");
        btProducto.setBorder(null);
        btProducto.setContentAreaFilled(false);
        btProducto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btProducto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/botonamarillooscuro.png"))); // NOI18N
        btProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProductoActionPerformed(evt);
            }
        });
        panelMenu.add(btProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, 160, 40));

        btCategoria.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCategoria.setForeground(new java.awt.Color(255, 255, 255));
        btCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/botonamarillo.png"))); // NOI18N
        btCategoria.setText("Categoria");
        btCategoria.setBorder(null);
        btCategoria.setContentAreaFilled(false);
        btCategoria.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCategoria.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/botonamarillooscuro.png"))); // NOI18N
        btCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCategoriaActionPerformed(evt);
            }
        });
        panelMenu.add(btCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 160, 40));

        btFactura.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btFactura.setForeground(new java.awt.Color(255, 255, 255));
        btFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/botonamarillo.png"))); // NOI18N
        btFactura.setText("Factura");
        btFactura.setBorder(null);
        btFactura.setContentAreaFilled(false);
        btFactura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btFactura.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/botonamarillooscuro.png"))); // NOI18N
        btFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFacturaActionPerformed(evt);
            }
        });
        panelMenu.add(btFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 160, 40));

        getContentPane().add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 750));

        panelPie.setBackground(new java.awt.Color(51, 153, 0));
        panelPie.setPreferredSize(new java.awt.Dimension(1400, 50));
        panelPie.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Copyright UTMACH 2021");
        panelPie.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 180, 50));

        lbMensaje.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbMensaje.setForeground(new java.awt.Color(0, 0, 255));
        panelPie.add(lbMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 5, 800, 40));

        getContentPane().add(panelPie, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 750, 2516, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClienteActionPerformed
        try{
            CardLayout cl = (CardLayout)(panelCentral.getLayout());
            cl.show(panelCentral, "Cliente");
        }catch(Exception ex){}
        panelCentral.repaint();
    }//GEN-LAST:event_btClienteActionPerformed

    private void btHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHomeActionPerformed
        try{
            CardLayout cl = (CardLayout)(panelCentral.getLayout());
            cl.show(panelCentral, "Fondo");
        }catch(Exception ex){}
        panelCentral.repaint();
    }//GEN-LAST:event_btHomeActionPerformed

    private void btProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProductoActionPerformed
        try{
            CardLayout cl = (CardLayout)(panelCentral.getLayout());
            cl.show(panelCentral, "Producto");
        }catch(Exception ex){}
        panelCentral.repaint();
    }//GEN-LAST:event_btProductoActionPerformed

    private void btCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCategoriaActionPerformed
        try{
            CardLayout cl = (CardLayout)(panelCentral.getLayout());
            cl.show(panelCentral, "Categoria");
        }catch(Exception ex){}
        panelCentral.repaint();
    }//GEN-LAST:event_btCategoriaActionPerformed

    private void btFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFacturaActionPerformed
        try{
            CardLayout cl = (CardLayout)(panelCentral.getLayout());
            cl.show(panelCentral, "DetalleFactura");
        }catch(Exception ex){}
        panelCentral.repaint();
    }//GEN-LAST:event_btFacturaActionPerformed
    
    public void DetalleFactura(){
        try{
            CardLayout cl = (CardLayout)(panelCentral.getLayout());
            cl.show(panelCentral, "Factura");
        }catch(Exception ex){}
        panelCentral.repaint();
    }
    
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        String path=Global.getPath();
        //Icono de la aplicación
        ImageIcon icon = new ImageIcon(path+ "\\Recursos\\olimpopng.png");
        setIconImage(icon.getImage());  
        setExtendedState(this.getExtendedState() | frmPrincipal.MAXIMIZED_BOTH); //maximizar
        //agregar todos los paneles creados
        panelCentral.add(new panelCliente(), "Cliente");
        panelCentral.add(new panelCategoria(), "Categoria");
        panelCentral.add(new panelProducto(), "Producto");
        panelCentral.add(new panelFactura(), "DetalleFactura");
        panelCentral.add(new panelDetalleFactura(), "Factura");
    }//GEN-LAST:event_formWindowActivated

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCategoria;
    private javax.swing.JButton btCliente;
    private javax.swing.JButton btFactura;
    private javax.swing.JButton btHome;
    private javax.swing.JButton btProducto;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbFondo;
    public static javax.swing.JLabel lbMensaje;
    private javax.swing.JPanel panelCentral;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelPie;
    // End of variables declaration//GEN-END:variables
}
