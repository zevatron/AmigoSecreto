package aplicacao;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.plaf.DesktopPaneUI;
import javax.swing.JPasswordField;


import fachada.Sistema;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txfLogin;
	private JPasswordField pwfLogin;
	private JLabel lblStatusLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
		setClosable(false);
		setBounds(100, 100, 450, 300);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(30, 53, 47, 15);
		
		txfLogin = new JTextField();
		txfLogin.setBounds(83, 51, 319, 19);
		txfLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(30, 90, 50, 15);
		
		pwfLogin = new JPasswordField();
		pwfLogin.setBounds(86, 88, 316, 19);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(30, 174, 105, 25);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Sistema.conectar();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Sistema.login(txfLogin.getText(), String.copyValueOf(pwfLogin.getPassword()));
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					lblStatusLogin.setText(e.getMessage());
					
				}
				
				Sistema.desconectar();
				
				if(Sistema.getLogado()!=null)
					dispose();
					
			}
		});
		btnLogin.setBounds(317, 174, 105, 25);
		getContentPane().setLayout(null);
		
		JLabel lblStatus = new JLabel("");
		lblStatus.setBounds(30, 245, 392, 0);
		lblStatus.setFont(new Font("Dialog", Font.BOLD, 14));
		lblStatus.setForeground(Color.BLUE);
		getContentPane().add(lblStatus);
		getContentPane().add(lblEmail);
		getContentPane().add(txfLogin);
		getContentPane().add(btnLogin);
		getContentPane().add(lblSenha);
		getContentPane().add(pwfLogin);
		getContentPane().add(btnLimpar);
		
		lblStatusLogin = new JLabel("");
		lblStatusLogin.setForeground(Color.RED);
		lblStatusLogin.setBounds(7, 241, 421, 15);
		getContentPane().add(lblStatusLogin);

	}
}
