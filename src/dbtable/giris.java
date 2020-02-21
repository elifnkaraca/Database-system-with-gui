package dbtable;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class giris {

	private JFrame frame;
	private JTextField txt_ad;
	private JTextField txt_sifre;
	static String ad; //ekrandan ulasmak icin
	static String sifre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					giris window = new giris();
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
	public giris() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kullan\u0131c\u0131 ad\u0131");
		lblNewLabel.setBounds(111, 53, 79, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u015Eifre");
		lblNewLabel_1.setBounds(111, 78, 79, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		txt_ad = new JTextField();
		txt_ad.setBounds(200, 50, 86, 20);
		frame.getContentPane().add(txt_ad);
		txt_ad.setColumns(10);
		
		txt_sifre = new JTextField();
		txt_sifre.setBounds(200, 75, 86, 20);
		frame.getContentPane().add(txt_sifre);
		txt_sifre.setColumns(10);
		
		JButton btnNewButton = new JButton("Giri\u015F");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ad = txt_ad.getText();
				sifre = txt_sifre.getText();
				
				String sql_sorgu = "SELECT count(idkullanici) as giris FROM deneme.kullanici WHERE kullanici_ad = '" + ad +"' and sifre = '" + sifre + "'";
				
				ResultSet myRs = baglanti.yap();
				myRs = baglanti.sorgula(sql_sorgu);
				
				try {
					while(myRs.next()){
						if(myRs.getInt("giris")==1) {
							ekran ekr = new ekran();
							ekr.setVisible(true);
							
						}
						else {
							btnNewButton.setText("hatali giris");
						}
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(197, 116, 105, 23);
		frame.getContentPane().add(btnNewButton);
	}

}
