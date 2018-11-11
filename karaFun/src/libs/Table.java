/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libs;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tran Tuan Anh
 */
public class Table{
    public void addValue(JTable table, Object[][] data, Object[] columns){
        
        table.setDefaultRenderer(Object.class, new Render());
        
        DefaultTableModel d = new DefaultTableModel(data,columns){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table.setModel(d);
        table.setRowHeight(25);
    }
    
}
