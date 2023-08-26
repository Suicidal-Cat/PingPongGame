package gameData;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gameInterface.FirstFrame;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Forma extends JFrame {

	
	private JPanel contentPane;
	private JTextField user;
	private JTextField gmail;
	private JTextField username;
	private JPasswordField pass;
	private JPasswordField password;
	private ImageIcon pozadinaArcade;
	private ImageIcon forma;

	JLabel myLabel;
	FirstFrame f;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Forma frame = new Forma();
//					frame.setVisible(true);
//					
//					
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	
	public Forma(FirstFrame f) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 437, 480);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setLocationRelativeTo(f);
//		forma=new ImageIcon("src/resources/images/4736288.jpg");
//		
//		myLabel=new JLabel(forma);

		
		this.f=f;
		f.setVisible(false);

		
		setContentPane(contentPane);
		contentPane.setLayout(null);
//		contentPane.add(myLabel);
		
		JLabel lblNewLabel = new JLabel("PRIJAVA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(164, 10, 96, 16);
		contentPane.add(lblNewLabel);
		
		user = new JTextField();
		user.setBounds(203, 44, 112, 19);
		contentPane.add(user);
		user.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("korisnicko ime");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(97, 47, 79, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("lozinka");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(97, 82, 79, 13);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblRegistracija = new JLabel("REGISTRACIJA");
		lblRegistracija.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRegistracija.setBounds(128, 176, 174, 23);
		contentPane.add(lblRegistracija);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("gmail");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(97, 226, 79, 13);
		contentPane.add(lblNewLabel_1_1_1);
		
		gmail = new JTextField();
		gmail.setColumns(10);
		gmail.setBounds(203, 224, 112, 19);
		contentPane.add(gmail);
		
		JButton btnNewButton = new JButton("Registruj se"); 
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/pingponggame","root","");
					PreparedStatement ps=conn.prepareStatement("insert into tblogin(UserName,Password,gmail) values (?,?,?)");
					ps.setString(1, username.getText());
					ps.setString(2, password.getText());
					ps.setString(3, gmail.getText());
					int x=ps.executeUpdate();
					if(x>0) {
						JOptionPane.showMessageDialog(null,"Uspesno ste se registrovali!\nMolim vas prijavite se!");
						username.setText("");
						password.setText("");
						gmail.setText("");
					}else
						JOptionPane.showMessageDialog(null,"Registracija nije uspela");
					
					
					
				}catch(Exception e1) {
					System.out.println(e1);
					
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(150, 360, 125, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Prijavi se");
		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pingponggame","root","");
					Statement stmt=con.createStatement();
					String sql="Select * from tbLogin where UserName='"+user.getText()+"' and Password='"+pass.getText().toString()+"'";
					ResultSet rs=stmt.executeQuery(sql);
					FirstFrame.u.userName=user.getText();
					FirstFrame.u.password=pass.getText();
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Uspesno ste se prijavili");
						f.setLocationRelativeTo(contentPane);
						f.setVisible(true);
						dispose();
					}
					else { 
						JOptionPane.showMessageDialog(null, "Korisnicko ime i/ili lozinka nije tacno");
						FirstFrame.u.userName=null;
						FirstFrame.u.password=null;
					}
					con.close();

				}catch(Exception e1) {
					System.out.print(e1);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setBounds(138, 131, 125, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("korisnicko ime");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1.setBounds(97, 270, 79, 13);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		username = new JTextField();
		username.setColumns(10);
		username.setBounds(203, 268, 112, 19);
		contentPane.add(username);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("lozinka");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_1_2.setBounds(97, 310, 79, 13);
		contentPane.add(lblNewLabel_1_1_1_2);
		
		pass = new JPasswordField();
		pass.setBounds(203, 80, 112, 19);
		contentPane.add(pass);
		
		password = new JPasswordField();
		password.setBounds(203, 308, 112, 19);
		contentPane.add(password);
		this.setVisible(true);
	}
}
