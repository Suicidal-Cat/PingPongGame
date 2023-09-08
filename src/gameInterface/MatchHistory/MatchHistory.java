/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameInterface.MatchHistory;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import gameData.Database;
import gameData.Match;
import gameInterface.FirstFrame;

import java.awt.Dimension;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class MatchHistory extends javax.swing.JFrame {

	Database base=new Database();
	FirstFrame frame;
	ImageIcon image1=new ImageIcon("src/resources/images/arcade1.png");
	
    public MatchHistory(FirstFrame f) {
    	frame=f;
    	f.setVisible(true);
    	setPreferredSize(new Dimension(1100, 611));
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(image1.getImage());
        this.setTitle("Pong Game");
        getContentPane().setBackground(Color.WHITE);
        table.fixTable(jScrollPane1);
        table.setColumnAlignment(0, JLabel.CENTER);
        table.setCellAlignment(0, JLabel.CENTER);
        table.setColumnAlignment(1, JLabel.CENTER);
        table.setCellAlignment(1, JLabel.CENTER);
        table.setColumnAlignment(2, JLabel.CENTER);
        table.setCellAlignment(2, JLabel.CENTER);
        table.setColumnAlignment(3, JLabel.CENTER);
        table.setCellAlignment(3, JLabel.CENTER);
        table.setColumnWidth(0, 130);//sirina kolona
        table.setColumnWidth(1, 220);
        table.setColumnWidth(3, 220);
        table.setColumnWidth(4, 0);
        DefaultTableModel mode = (DefaultTableModel) table.getModel();
        
        LinkedList<Match>matches=base.getAllMatches(FirstFrame.u.userName);
        if(matches==null) {
        	JOptionPane.showMessageDialog(null,"Trenutno nemate ni jedan odigran mec! Sta cekate?");
        	this.setVisible(false);
        	frame.setVisible(true);
        	this.dispose();
        }
        else {
        	for (int i = 0; i < matches.size(); i++) {
                mode.addRow(matches.get(i).getDataMatch());
            }
            this.setVisible(true);
        }
        
        
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "GameMode", "You", "Score", "Opponent","Result"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        
        JButton btnBack = new JButton(back);
        btnBack.setPreferredSize(new Dimension(0, 0));
        btnBack.setBackground(Color.decode("#6C74C6"));
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.setVisible(true);
        		dispose();
        	}
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(19)
        			.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(925, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 505, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        			.addGap(18))
        );
        getContentPane().setLayout(layout);

        pack();
        setLocationRelativeTo(null);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private Table table;
    private Icon back = new ImageIcon("src/resources/images/backarrow.png");
}
