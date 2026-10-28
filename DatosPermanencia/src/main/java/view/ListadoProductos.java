/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.util.ArrayList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import model.Producto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author pv-alumno 
 */
public class ListadoProductos extends javax.swing.JFrame {
    ArrayList<Producto> productos=new ArrayList<>();
    /**
     * Creates new form ListadoProductos
     */
    public ListadoProductos() {
        initComponents();

        // Asignar el escuchador de cambios a la tabla
    TablaProductos.getModel().addTableModelListener(new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent e) {
            // Validar que el cambio sea una actualización de una celda
            if (e.getType() == TableModelEvent.UPDATE) {
                int fila = e.getFirstRow();
                int columna = e.getColumn();
                
                // Evitar desbordamientos si la columna es inválida (-1)
                if (columna == -1) return; 

                DefaultTableModel modelo = (DefaultTableModel) e.getSource();
                
                // 1. Obtener el nuevo valor que el usuario escribió
                Object nuevoValor = modelo.getValueAt(fila, columna);
                
                // 2. Aquí obtienes el objeto original de tu lista (debes tener acceso a tu 'listaProductos')
                // Por ejemplo, si tu lista es una variable global de la clase:
                Producto prodModificado = productos.get(fila);
                
                // 3. Identificar qué columna cambió y actualizar el objeto
                switch (columna) {
                    case 0: // Columna Nombre
                        prodModificado.setNombre((String) nuevoValor);
                        break;
                    case 1: // Columna Precio
                        // Nota: Si es un campo numérico, asegúrate de castearlo o parsearlo correctamente
                        prodModificado.setPrecio(Integer.parseInt(nuevoValor.toString()));
                        break;
                    case 2: // Columna Cantidad
                        prodModificado.setCant(Integer.parseInt(nuevoValor.toString()));
                        break;
                }
                
                // 4. (Opcional) Aquí puedes llamar a tu método para guardar en la Base de Datos
                // de ser necesario, pasando el 'prodModificado'.
                System.out.println("Producto actualizado: " + prodModificado.getNombre());
            }
        }
        
    });
        
      

        cargaDatosJson();
        cargarDatosTabla(productos);
    }
    
    public void creaJson(){
            
        Gson gson = new Gson();
        String jsonText = gson.toJson(productos);

        // Imprimir en la consola de NetBeans
        System.out.println(jsonText);
        
        String nombreArchivo = "productos.json";

        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write(jsonText);
            System.out.println("¡Archivo guardado con éxito en: " + nombreArchivo + "!");
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
            e.printStackTrace();
        }
                
             
    }
      
    public void cargaDatosJson(){
    String nombreArchivo = "productos.json";

    try (FileReader reader = new FileReader(nombreArchivo)) {
        Gson gson = new Gson();

        // 1. Definir el tipo exacto de la lista (ej: ArrayList de Producto)
        // Reemplaza "Producto" por el nombre exacto de tu clase
        java.lang.reflect.Type tipoLista = new TypeToken<ArrayList<Producto>>(){}.getType();

        // 2. Leer el archivo y transformar el JSON de vuelta al ArrayList
        productos = gson.fromJson(reader, tipoLista);

        System.out.println("¡ArrayList actualizado con éxito desde el archivo JSON!");

    } catch (IOException e) {
        System.out.println("Error al leer el archivo: " + e.getMessage());
        e.printStackTrace();
    }
        }
    
    public void cargarDatosTabla(ArrayList<Producto> listaProductos) {
    // 1. Obtener el modelo predeterminado de la JTable y castearlo a DefaultTableModel
    DefaultTableModel modelo = (DefaultTableModel) TablaProductos.getModel();
    
    // 2. Limpiar la tabla por si ya tenía filas cargadas previamente
    modelo.setRowCount(0);
    
    // 3. Definir los títulos de las columnas (opcional si ya los configuraste visualmente)
    String[] columnas = { "Nombre", "Precio", "Cant"};
    modelo.setColumnIdentifiers(columnas);
    
    // 4. Recorrer la lista de objetos y añadirlos fila por fila
    for (Producto prod : listaProductos) {
        // Creamos un array de Object donde cada posición corresponde a una columna de la tabla
        Object[] fila = new Object[3]; 
        fila[0] = prod.getNombre();
        fila[1] = prod.getPrecio();
        fila[2] = prod.getCant();
        // Agregar el array como una nueva fila en el modelo
        modelo.addRow(fila); 
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        nombre_p = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        precio_p = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cant_p = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Precio", "Cant"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaProductos);

        jLabel1.setText("Nombre");

        jLabel2.setText("Precio");

        jLabel3.setText("Cantidad");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        editar.setText("Actualizar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(jLabel1)
                            .addComponent(nombre_p))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(precio_p, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(cant_p, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(216, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nombre_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(precio_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cant_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(editar)
                    .addComponent(eliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String n = nombre_p.getText();
        String p = precio_p.getText();
        String c = cant_p.getText();
        
        int pre=Integer.parseInt(p);
        int cant=Integer.parseInt(c);
        
        Producto prod1=new Producto(n, pre, cant);
        productos.add(prod1);
        cargarDatosTabla(productos);
        creaJson();
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
        productos.remove(TablaProductos.getSelectedRow());
        cargarDatosTabla(productos);
        creaJson();
        
    }//GEN-LAST:event_eliminarActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        // TODO add your handling code here:
        creaJson();
    }//GEN-LAST:event_editarActionPerformed
    
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
            java.util.logging.Logger.getLogger(ListadoProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListadoProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListadoProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListadoProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListadoProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaProductos;
    private javax.swing.JTextField cant_p;
    private javax.swing.JButton editar;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre_p;
    private javax.swing.JTextField precio_p;
    // End of variables declaration//GEN-END:variables
}
