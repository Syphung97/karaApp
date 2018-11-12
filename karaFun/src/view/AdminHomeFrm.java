/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.SongDAO;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import model.Song;

/**
 *
 * @author Hau Nguyen
 */
public class AdminHomeFrm extends javax.swing.JFrame {

    public ArrayList<JButton> listUpdateBtn;
    public ArrayList<JButton> listDeleteBtn;

    public ArrayList<Song> listSong;
    private SongDAO songDAO;

    /**
     * Creates new form AdminHomeFrm
     */
    public AdminHomeFrm() {
        initComponents();
        listUpdateBtn = new ArrayList<>();
        listDeleteBtn = new ArrayList<>();
        listSong = new ArrayList<>();
        songDAO = new SongDAO();

        initList();
        
        jtbSong.setModel(new AdminHomeTabelModel());
        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        jtbSong.getColumn("Update").setCellRenderer(buttonRenderer);
        jtbSong.getColumn("Delete").setCellRenderer(buttonRenderer);

        jtbSong.addMouseListener(new JTableButtonMouseListener(jtbSong));
        jtbSong.setRowHeight(25);

    }
    
    public void updateTable() {
        ((AbstractTableModel) jtbSong.getModel()).fireTableDataChanged();
    }
    
    public void showMessage(String msg){
        JOptionPane.showMessageDialog(this, msg);
    }
    
    
    
    public void addJtableMouseListener(MouseListener a) {
        jtbSong.addMouseListener(a);
    }
    
    public void addLogoutBtnListener(ActionListener a) {
        jbLogout.addActionListener(a);
    }
    
    public void addSearchBtnListener(ActionListener a) {
        jbSearch.addActionListener(a);
    }
    
    public void addAddSongBtnListener(ActionListener a) {
        jbAdd.addActionListener(a);
    }
    
    public String getSearchName() {
        if(jtSearch.getText().equals("")) {
            return null;
        }
        return jtSearch.getText();
    }
    
    public void initList() {
        listSong = songDAO.getListSong();

        for (int i = 0; i < listSong.size(); i++) {
            JButton btnUpdate = new JButton("Update");
            JButton btnDelete = new JButton("Delete");
            listUpdateBtn.add(btnUpdate);
            listDeleteBtn.add(btnDelete);
        }
    }

    class AdminHomeTabelModel extends AbstractTableModel {

        private String[] columnNames = {"ID", "Name", "Author", "Singer", "Update", "Delete"};
        private final Class<?>[] columnTypes = new Class<?>[]{Integer.class, String.class, String.class, String.class, JButton.class, JButton.class};

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return listSong.size();
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnTypes[columnIndex];
        }

        @Override
        public Object getValueAt(final int rowIndex, final int columnIndex) {
            /*Adding components*/
            switch (columnIndex) {
                case 0:
                    return listSong.get(rowIndex).getID();
                case 1:
                    return listSong.get(rowIndex).getName();
                case 2:
                    return listSong.get(rowIndex).getAuthor();
                case 3:
                    return listSong.get(rowIndex).getSinger();
                case 4:
                    return listUpdateBtn.get(rowIndex);
                case 5:
                    return listDeleteBtn.get(rowIndex);
                default:
                    return "Error";
            }
        }
    }

    public class JTableButtonRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            JButton button = (JButton) value;
            return button;
        }
    }

    public class JTableButtonMouseListener extends MouseAdapter {

        private final JTable table;

        public JTableButtonMouseListener(JTable table) {
            this.table = table;
        }

        public void mouseClicked(MouseEvent e) {
            int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
            int row = e.getY() / table.getRowHeight(); //get the row of the button

            //*Checking the row or column is valid or not
            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                Object value = table.getValueAt(row, column);
                if (value instanceof JButton) {
                    //perform a click event
                    ((JButton) value).doClick();
                }
            }
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

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jbLogout = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbSong = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jtSearch = new javax.swing.JTextField();
        jbSearch = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jbAdd = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Admin Home");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Hello: Admin");

        jbLogout.setText("Logout");
        jbLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLogoutActionPerformed(evt);
            }
        });

        jtbSong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Author", "Singer", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtbSong);

        jLabel3.setText("Search song's name:");

        jbSearch.setText("Search");
        jbSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSearchActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Songs:");

        jbAdd.setText("Add Song");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbAdd))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jbLogout)))
                        .addGap(34, 34, 34))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jbLogout))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSearch)
                    .addComponent(jbAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLogoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbLogoutActionPerformed

    private void jbSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSearchActionPerformed

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
            java.util.logging.Logger.getLogger(AdminHomeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminHomeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminHomeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminHomeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminHomeFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jbAdd;
    private javax.swing.JButton jbLogout;
    private javax.swing.JButton jbSearch;
    private javax.swing.JTextField jtSearch;
    private javax.swing.JTable jtbSong;
    // End of variables declaration//GEN-END:variables

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        JButton btnClicked = (JButton) e.getSource();
//        for (int i = 0; i < listUpdateBtn.size(); i++) {
//            if (btnClicked.equals(listUpdateBtn.get(i))) {
//
//                System.out.println("click update" + i);
//            }
//        }
//
//        for (int i = 0; i < listDeleteBtn.size(); i++) {
//            if (btnClicked.equals(listDeleteBtn.get(i))) {
//
//                System.out.println("click delete" + i);
//            }
//        }
//
//    }
}
