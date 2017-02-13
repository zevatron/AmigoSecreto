package aplicacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachada.Sistema;


import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private JLabel lblOla;
	private Login telaLogin;
	private JButton btnLogout;
	private JMenuItem mntmGrupos;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				chamarLogin();
					
			}
			@Override
			public void windowClosing(WindowEvent e) {
				lblOla.setText("Desconectando do servidor...");
				Sistema.desconectar();
				//JOptionPane.showMessageDialog(null, "sistema finalizado !");
			}
			
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(10, 10, 626, 21);
		contentPane.add(menuBar);
		
		JMenu mnCadastrar = new JMenu("Cadastrar");
		menuBar.add(mnCadastrar);
		
		JMenuItem mntmPessoas = new JMenuItem("Pessoas");
		mntmPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				CadastroPessoas cadPessoas = new CadastroPessoas();
				desktopPane.add(cadPessoas);
				cadPessoas.setVisible(true);
				
			}
			
		});
		mnCadastrar.add(mntmPessoas);
		
		mntmGrupos = new JMenuItem("Grupos");
		mntmGrupos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CadastroGrupos cadGrupos = new CadastroGrupos();
				desktopPane.add(cadGrupos);
				cadGrupos.setVisible(true);
				
			}
			
		});
		mnCadastrar.add(mntmGrupos);
		
		JMenu mnEditar = new JMenu("Editar");
		menuBar.add(mnEditar);
		
		JMenu mnListar = new JMenu("Listar");
		menuBar.add(mnListar);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Tutorial");
		mnAjuda.add(mntmNewMenuItem);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mnAjuda.add(mntmSobre);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(15, 91, 621, 329);
		contentPane.add(desktopPane);
		desktopPane.setLayout(new BorderLayout(0, 0));
		
		lblOla = new JLabel("");
		lblOla.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOla.setBounds(155, 43, 481, 21);
		contentPane.add(lblOla);
		
		btnLogout = new JButton("Logout");
		btnLogout.setEnabled(false);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					lblOla.setText(Sistema.logoff());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					lblOla.setText(e.getMessage());
				}
				desktopPane.removeAll();
				btnLogout.setEnabled(false);
				chamarLogin();
			}
		});
		btnLogout.setBounds(20, 41, 117, 25);
		contentPane.add(btnLogout);			
		
		
		
	}

	private void chamarLogin() {
		try {
			Sistema.conectar();
			lblOla.setText("Online");
			Sistema.desconectar();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			lblOla.setText(e1.getMessage());
		//	btnLogin.setEnabled(false);
		}
		mntmGrupos.setEnabled(false);
		telaLogin = new Login();
		desktopPane.add(telaLogin);
		telaLogin.setVisible(true);
		
		desktopPane.addContainerListener(new ContainerListener() {
			
			@Override
			public void componentRemoved(ContainerEvent arg0) {
				// TODO Auto-generated method stub
				if(Sistema.getLogado()!=null){
					lblOla.setText("Bem vindo "+Sistema.getLogado().getNome());
					btnLogout.setEnabled(true);
					mntmGrupos.setEnabled(true);
					MeusGrupos meusGrupos = new MeusGrupos();
					desktopPane.add(meusGrupos);
					meusGrupos.setVisible(true);
				}
				
			}
			
			@Override
			public void componentAdded(ContainerEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
