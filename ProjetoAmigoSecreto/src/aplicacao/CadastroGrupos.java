package aplicacao;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

import fachada.Sistema;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroGrupos extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txfTitulo;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroGrupos frame = new CadastroGrupos();
					frame.setVisible(true);
				} catch (Exception e) {
					
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroGrupos() {
		setClosable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		lblStatus = new JLabel("");
		lblStatus.setForeground(Color.BLUE);
		lblStatus.setFont(new Font("Dialog", Font.BOLD, 14));
		lblStatus.setBounds(12, 237, 416, 19);
		getContentPane().add(lblStatus);
		
		JLabel lblTtulo = new JLabel("Título");
		lblTtulo.setBounds(24, 92, 45, 15);
		getContentPane().add(lblTtulo);
		
		txfTitulo = new JTextField();
		txfTitulo.setColumns(10);
		txfTitulo.setBounds(75, 90, 321, 19);
		getContentPane().add(txfTitulo);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(180, 185, 105, 25);
		getContentPane().add(btnCancelar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Sistema.conectar();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					lblStatus.setText(e1.getMessage());
				}
				try {
					Sistema.criarGrupo(txfTitulo.getText());
					lblStatus.setText("O grupo \""+txfTitulo.getText()+"\" foi cadastrado com sucesso...");
					txfTitulo.setText("");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					lblStatus.setText(e1.getMessage());
				}
				Sistema.desconectar();
			}
		});
		btnCadastrar.setBounds(299, 185, 105, 25);
		getContentPane().add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBackground(new Color(255, 160, 122));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txfTitulo.setText("");
			}
		});
		btnLimpar.setBounds(12, 185, 105, 25);
		getContentPane().add(btnLimpar);

	}
}
