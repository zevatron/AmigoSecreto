package aplicacao;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import fachada.Sistema;

import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroPessoas extends JInternalFrame {
	private JTextField txfNome;
	private JTextField txfEmail;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroPessoas frame = new CadastroPessoas();
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
	public CadastroPessoas() {
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(30, 16, 45, 15);
		
		txfNome = new JTextField();
		txfNome.setBounds(81, 14, 321, 19);
		txfNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(30, 53, 47, 15);
		
		txfEmail = new JTextField();
		txfEmail.setBounds(83, 51, 319, 19);
		txfEmail.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(30, 90, 50, 15);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(86, 88, 229, 19);
		
		JLabel lblRepetirSenha = new JLabel("Repetir senha:");
		lblRepetirSenha.setBounds(30, 121, 105, 15);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(149, 119, 166, 19);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCadastroPessoa();
			}
		});
		btnLimpar.setBounds(30, 174, 105, 25);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(String.copyValueOf(passwordField.getPassword()).equals(String.copyValueOf(passwordField_1.getPassword()))){
					lblStatus.setText("Cadastrando...");
					try {
						Sistema.conectar();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						lblStatus.setText(e1.getMessage());
					}
					try {
						Sistema.cadastrarPessoa(txfNome.getText(),
								txfEmail.getText(),
								String.copyValueOf(passwordField.getPassword()) );
						lblStatus.setText(txfNome.getText()+" cadastrado com sucesso...");
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						lblStatus.setText(e.getMessage());
					}
					Sistema.desconectar();
					limparCadastroPessoa();
				}
				else
					lblStatus.setText("Senhas não conferem...");
			}
		});
		btnCadastrar.setBounds(317, 174, 105, 25);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(198, 174, 105, 25);
		getContentPane().setLayout(null);
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(30, 222, 392, 19);
		lblStatus.setFont(new Font("Dialog", Font.BOLD, 14));
		lblStatus.setForeground(Color.BLUE);
		getContentPane().add(lblStatus);
		getContentPane().add(lblNome);
		getContentPane().add(txfNome);
		getContentPane().add(lblEmail);
		getContentPane().add(txfEmail);
		getContentPane().add(btnCancelar);
		getContentPane().add(btnCadastrar);
		getContentPane().add(lblRepetirSenha);
		getContentPane().add(passwordField_1);
		getContentPane().add(lblSenha);
		getContentPane().add(passwordField);
		getContentPane().add(btnLimpar);

	}

	private void limparCadastroPessoa() {
		txfNome.setText("");
		txfEmail.setText("");
		passwordField.setText("");
		passwordField_1.setText("");
	}
}
