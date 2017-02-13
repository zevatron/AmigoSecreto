package aplicacao;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import fachada.Sistema;
import modelo.Pessoa;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.FlowLayout;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class GrupoAdmin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane panePessoas;
	private JScrollPane paneParticipando;
	private DefaultListModel<Pessoa> modelPessoas =  new DefaultListModel<Pessoa>();
	private DefaultListModel<Pessoa> modelPessoasParticipando = new DefaultListModel<Pessoa>();
	private JList<Pessoa> list;
	private JList<Pessoa> listParticipando;
	private JTextField txfLocalizar;
	private JButton btnGravar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrupoAdmin frame = new GrupoAdmin();
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
	public GrupoAdmin() {
//		setModal(true);
		setTitle("Grupo: "+Sistema.getGrupoSelecionado().getTitulo());
		setBounds(100, 100, 650, 485);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
				
		JLabel lblPessoas = new JLabel("Pessoas");
		lblPessoas.setBounds(12, 13, 70, 15);
		getContentPane().add(lblPessoas);
		
		
		JLabel lblParticipantes = new JLabel("Participantes");
		lblParticipantes.setBounds(431, 13, 95, 15);
		getContentPane().add(lblParticipantes);
		
		panePessoas = new JScrollPane();
		panePessoas.setBounds(12, 89, 237, 306);
		getContentPane().add(panePessoas);
		
		paneParticipando = new JScrollPane();
		paneParticipando.setBounds(404, 89, 232, 306);
		getContentPane().add(paneParticipando);
		
		try {
			Sistema.conectar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Pessoa p : Sistema.getGrupoSelecionado().getPessoas()){
			modelPessoasParticipando.addElement(p);
		}
		for(Pessoa p:Sistema.listarPessoas()){
			if(!modelPessoasParticipando.contains(p))
				modelPessoas.addElement(p);
		}
		
		Sistema.desconectar();
			
		list = new JList<Pessoa>(modelPessoas);
		panePessoas.setViewportView(list);
		
		listParticipando = new JList<Pessoa>(modelPessoasParticipando);
		paneParticipando.setViewportView(listParticipando);
		
		JButton btnAdicionar = new JButton("Adicionar >>");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				Sistema.localizarGrupo(Sistema.getGrupoSelecionado().getTitulo()).setPessoas((ArrayList<Pessoa>) list.getSelectedValuesList());
//				Sistema.atualizarGrupo(Sistema.getGrupoSelecionado());
				for(Pessoa p : list.getSelectedValuesList()){
					modelPessoasParticipando.addElement(p);
					modelPessoas.removeElement(p);
				}
				listParticipando.setModel(modelPessoasParticipando);
					
			}
		});
		btnAdicionar.setBounds(261, 89, 131, 25);
		getContentPane().add(btnAdicionar);
		
		JButton btnRemover = new JButton("<< Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(Pessoa p : listParticipando.getSelectedValuesList()){
					modelPessoas.addElement(p);
					modelPessoasParticipando.removeElement(p);
				}
			}
		});
		btnRemover.setBounds(261, 126, 131, 25);
		getContentPane().add(btnRemover);
		
		txfLocalizar = new JTextField();
		txfLocalizar.setBounds(12, 47, 144, 19);
		getContentPane().add(txfLocalizar);
		txfLocalizar.setColumns(10);
		
		JButton btnListar = new JButton("Listar");
//		btnListar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				try {
//					Sistema.conectar();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				modelPessoas.removeAllElements();
//				for(Pessoa p:Sistema.listarPessoas()){
//					if(!modelPessoasParticipando.contains(p)){
//						modelPessoas.addElement(p);
//					}
//						
//				}
//				Sistema.desconectar();
//			}
//		});
		btnListar.setBounds(165, 44, 84, 25);
		getContentPane().add(btnListar);
		
		btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Sistema.conectar();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(int i=0;i<modelPessoasParticipando.size();i++){
					 Sistema.adicionarPessoaGrupo(Sistema.localizarPessoa(modelPessoasParticipando.getElementAt(i).getEmail()));
					 System.out.println("Adicionando-> "+Sistema.localizarPessoa(modelPessoasParticipando.getElementAt(i).getEmail()));
				}
				Sistema.desconectar();
			}
		});
		btnGravar.setBounds(261, 328, 131, 54);
		getContentPane().add(btnGravar);
		
		
		

	}
}
