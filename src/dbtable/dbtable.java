package dbtable;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class dbtable {

	private JFrame frame;
	private JTable table;
	
	DefaultTableModel modelim = new DefaultTableModel();
	
	Object[] kolonlar = {"No", "Soyisim", "Ýsim", "Veli"};
	Object[] satirlar = new Object[4];
	private JTextField txt_no;
	private JTextField txt_ad;
	private JTextField txt_soyad;
	private JTextField txt_veli;
	private JTextField txt_adsorgu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dbtable window = new dbtable();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public dbtable() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 729, 415);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(10, 33, 367, 237);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		modelim.setColumnIdentifiers(kolonlar);
		
		/*table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		)); */
		table.setBounds(68, 185, 190, 121);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("listele");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				modelim.setRowCount(0);
				
				ResultSet myRs = baglanti.yap();
				
				try {
					while(myRs.next()) {
						satirlar[0] = myRs.getString("ogr_no");
						satirlar[1] = myRs.getString("ogr_soyad");
						satirlar[2] = myRs.getString("ogr_ad");
						satirlar[3] = myRs.getString("ogr_veli");
						modelim.addRow(satirlar);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				table.setModel(modelim);
				
			}
		});
		btnNewButton.setBounds(552, 45, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		txt_no = new JTextField();
		txt_no.setBounds(540, 107, 86, 20);
		frame.getContentPane().add(txt_no);
		txt_no.setColumns(10);
		
		txt_ad = new JTextField();
		txt_ad.setBounds(540, 138, 86, 20);
		frame.getContentPane().add(txt_ad);
		txt_ad.setColumns(10);
		
		txt_soyad = new JTextField();
		txt_soyad.setBounds(540, 169, 86, 20);
		frame.getContentPane().add(txt_soyad);
		txt_soyad.setColumns(10);
		
		txt_veli = new JTextField();
		txt_veli.setBounds(540, 202, 86, 20);
		frame.getContentPane().add(txt_veli);
		txt_veli.setColumns(10);
		
		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//INSERT INTO ogrenci (ogr_no, ogr_ad, ogr_soyad, ogr_veli) VALUES ('6','Hasan', 'Harman', 'Hüseyin')
				
				String no, ad, soyad, veli, sql_sorgu;
				no = txt_no.getText();
				ad = txt_ad.getText();
				soyad = txt_soyad.getText();
				veli = txt_veli.getText();
				
				sql_sorgu = "INSERT INTO ogrenci (ogr_no, ogr_soyad, ogr_ad, ogr_veli) VALUES (" + 
				            no + ",'" + soyad + "'," + "'" + ad + "'," + "'" + veli + "')";
				
				baglanti.ekle(sql_sorgu);
			}
		});
		btnKaydet.setBounds(413, 247, 89, 23);
		frame.getContentPane().add(btnKaydet);
		
		JLabel lblNewLabel = new JLabel("No");
		lblNewLabel.setBounds(456, 110, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Soyad");
		lblNewLabel_1.setBounds(456, 141, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ad");
		lblNewLabel_2.setBounds(456, 172, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Veli");
		lblNewLabel_3.setBounds(456, 205, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//UPDATE tablo_ismi Set alan1 ='', alan2 = 45 WHERE numara = 5
				
				String no, ad, soyad, veli, sql_sorgu;
				no = txt_no.getText();
				ad = txt_ad.getText();
				soyad = txt_soyad.getText();
				veli = txt_veli.getText();
				
				sql_sorgu = "UPDATE ogrenci SET ogr_no="+no+","+
				            "ogr_ad='"+ad+"',ogr_soyad='"+soyad+
				            "',ogr_veli='"+veli+"' WHERE ogr_no="+no;
				
				//System.out.println(sql_sorgu);
				
				baglanti.update(sql_sorgu);
			}
		});
		btnUpdate.setBounds(522, 247, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String no, sql_sorgu;
				no = txt_no.getText();
				
				//DELETE FROM ogrenci WHERE ogr_no = no
				sql_sorgu = "DELETE FROM ogrenci WHERE ogr_no="+no;
				
				baglanti.delete(sql_sorgu);
			}
		});
		btnNewButton_1.setBounds(624, 247, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		txt_adsorgu = new JTextField();
		txt_adsorgu.setBounds(34, 314, 86, 20);
		frame.getContentPane().add(txt_adsorgu);
		txt_adsorgu.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Alan");
		lblNewLabel_4.setBounds(20, 281, 64, 22);
		frame.getContentPane().add(lblNewLabel_4);
		

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Ad", "Soyad", "Numara", "Veli ismi"}));
		comboBox.setBounds(93, 281, 89, 22);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton_2 = new JButton("Sorgula");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				modelim.setRowCount(0);
				String sql_sorgu = null;
				String alan = txt_adsorgu.getText();
				ResultSet myRs = null;
				
				int secilen = comboBox.getSelectedIndex();
				
				if(secilen == 0) {
				 sql_sorgu = "select * from ogrenci WHERE ogr_ad like '"+ alan + "%'";
				} 
				else if(secilen == 1) {
					sql_sorgu = "select * from ogrenci WHERE ogr_soyad like '"+ alan + "%'";
				}
				else if(secilen == 3) {
					sql_sorgu = "select * from ogrenci WHERE ogr_veli like '"+ alan + "%'";
				}
				else if(secilen == 2) {
					sql_sorgu = "select * from ogrenci WHERE ogr_no= '"+ Integer.parseInt(alan) ;
				}
				
				myRs = baglanti.sorgula(sql_sorgu);
				try {
					while(myRs.next()) {
						satirlar[0] = myRs.getString("ogr_no");
						satirlar[1] = myRs.getString("ogr_soyad");
						satirlar[2] = myRs.getString("ogr_ad");
						satirlar[3] = myRs.getString("ogr_veli");
						modelim.addRow(satirlar);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				table.setModel(modelim);
			}
			
		});
		btnNewButton_2.setBounds(32, 345, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				txt_no.setText((String) modelim.getValueAt(table.getSelectedRow(), 0));
				txt_soyad.setText((String) modelim.getValueAt(table.getSelectedRow(), 1));
				txt_ad.setText((String) modelim.getValueAt(table.getSelectedRow(), 2));
				txt_veli.setText((String) modelim.getValueAt(table.getSelectedRow(), 3));
			}
		});
		
		
		//frame.getContentPane().add(table);
	}
}
