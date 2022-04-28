/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package design;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MultiLineTableCellRenderer extends JList<String> implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //make multi line where the cell value is String[]
        if (value instanceof String[]) {
            setListData((String[]) value);
        }
        
        if (table.isCellSelected(row, column)) {
            setForeground(Color.blue);
            setBackground(Color.gray);
        } else if (table.isRowSelected(row)) {
            setForeground(Color.black);
            setBackground(Color.white);
        } else if (table.isColumnSelected(column)) {
            setForeground(Color.black);
            setBackground(Color.white);
        } else {
            setForeground(Color.black);
            setBackground(Color.white);
        }

        return this;
    }
}
