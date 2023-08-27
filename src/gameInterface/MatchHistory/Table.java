package gameInterface.MatchHistory;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class Table extends JTable {

    private TableDarkHeader header;
    private TableDarkCell cell;

    public Table() {
        header = new TableDarkHeader();
        cell = new TableDarkCell();
        getTableHeader().setDefaultRenderer(header);
        getTableHeader().setPreferredSize(new Dimension(0, 45));
        setDefaultRenderer(Object.class, cell);
        setRowHeight(130);
    }

    public void setColumnAlignment(int column, int align) {
        header.setAlignment(column, align);
    }

    public void setCellAlignment(int column, int align) {
        cell.setAlignment(column, align);
    }

    public void setColumnWidth(int column, int width) {
        getColumnModel().getColumn(column).setPreferredWidth(width);
        getColumnModel().getColumn(column).setMinWidth(width);
        getColumnModel().getColumn(column).setMaxWidth(width);
        getColumnModel().getColumn(column).setMinWidth(10);
        getColumnModel().getColumn(column).setMaxWidth(10000);
    }

    public void fixTable(JScrollPane scroll) {
        scroll.setVerticalScrollBar(new ScrollBar());
        JPanel panel = new JPanel();
        panel.setBackground(new Color(251, 160, 227)); //boja onog headera iznad scorlla
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
        scroll.getViewport().setBackground(new Color(251, 160, 227));//ispod redova prostor
        scroll.setBorder(BorderFactory.createLineBorder(new Color(251, 160, 227))); // border celog jtable
    }

    private class TableDarkHeader extends DefaultTableCellRenderer {

        private Map<Integer, Integer> alignment = new HashMap<>();

        public void setAlignment(int column, int align) {
            alignment.put(column, align);
        }

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
            Component com = super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
            com.setBackground(new Color(251, 160, 227)); //pozadina headera
            com.setForeground(new Color(128,0,128)); //boja slova
            com.setFont(com.getFont().deriveFont(Font.BOLD, 20));
            if (alignment.containsKey(i1)) {
                setHorizontalAlignment(alignment.get(i1));
            } else {
                setHorizontalAlignment(JLabel.LEFT);
            }
            return com;
        }
    }

    private class TableDarkCell extends DefaultTableCellRenderer {

        private Map<Integer, Integer> alignment = new HashMap<>();

        public void setAlignment(int column, int align) {
            alignment.put(column, align);
        }

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int row, int column) {
            Component com = super.getTableCellRendererComponent(jtable, o, bln, bln1, row, column);
            if (isCellSelected(row, column)) {
                if (row % 2 == 0) {
                    com.setBackground(new Color(147,112,219));//redovi selektovani
                } else {
                    com.setBackground(new Color(65,105,225));
                }
            } else {
            	if((int)jtable.getModel().getValueAt(row, 4)==1) {
            		com.setBackground(Color.decode("#52B2BF")); //nisu selektovani a pobedili
            	}
            	else com.setBackground(Color.decode("#9932CC"));
            }
            
            com.setForeground(new Color(200, 200, 200));// boja slova redova
            setBorder(new EmptyBorder(0, 5, 0, 5));
            setFont(new Font("Serif", Font.BOLD, 20)); //fon i velicina
            if (alignment.containsKey(column)) {
                setHorizontalAlignment(alignment.get(column));
            } else {
                setHorizontalAlignment(JLabel.LEFT);
            }
            return com;
        }
    }
}
