package aplicacao;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import fachada.Sistema;
import modelo.Mensagem;
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
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class GrupoParticipante extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultListModel<Pessoa> modelPessoas =  new DefaultListModel<Pessoa>();
	private DefaultListModel<Pessoa> modelPessoasParticipando = new DefaultListModel<Pessoa>();
	private JLabel lblAmigo;
	private JButton btnEsconder;
	private JButton btnSortearAmigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrupoParticipante frame = new GrupoParticipante();
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
	public GrupoParticipante() {
//		setModal(true);
		setTitle("Grupo: "+Sistema.getGrupoSelecionado().getTitulo());
		setBounds(100, 100, 650, 485);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNmeroDeParticipantes = new JLabel("Número de participantes: "+Sistema.getGrupoSelecionado().getPessoas().size());
		lblNmeroDeParticipantes.setBounds(12, 5, 214, 15);
		getContentPane().add(lblNmeroDeParticipantes);
		
		btnSortearAmigo = new JButton("Sortear Amigo");
		if(Sistema.getLogado().getAmigo()== null)
			btnSortearAmigo.setEnabled(true);
		else
			btnSortearAmigo.setEnabled(false);
		btnSortearAmigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Sistema.conectar();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				do{
					Sistema.getGrupoSelecionado().embaralhar();
				}while(Sistema.getGrupoSelecionado().getPessoasEmbaralhadas().get(0).equals(Sistema.getLogado()));
				Sistema.getLogado().setAmigo(Sistema.getGrupoSelecionado().getPessoasEmbaralhadas().get(0));
				Sistema.getGrupoSelecionado().getPessoasEmbaralhadas().remove(0);
				Sistema.atualizarGrupo(Sistema.getGrupoSelecionado());
				Sistema.atualizarPessoa(Sistema.getLogado());
				if(Sistema.getLogado().getAmigo()!= null)
					btnSortearAmigo.setEnabled(false);
				
				Sistema.desconectar();
				
				
				
			}
		});
		btnSortearAmigo.setBounds(231, 0, 153, 25);
		getContentPane().add(btnSortearAmigo);
		
		JLabel lblOAmigoSorteado = new JLabel("O amigo sorteado foi -> ");
		lblOAmigoSorteado.setBounds(22, 32, 172, 15);
		getContentPane().add(lblOAmigoSorteado);
		
		lblAmigo = new JLabel("");
		lblAmigo.setBounds(251, 32, 143, 15);
		getContentPane().add(lblAmigo);
		
		btnEsconder = new JButton("Mostrar");
		btnEsconder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnEsconder.getText().equals("Mostrar")){
					lblAmigo.setText(Sistema.getLogado().getAmigo().getNome());
					btnEsconder.setText("Esconder");
				}else{
					lblAmigo.setText(" * * * * * * * ");
					btnEsconder.setText("Mostrar");
				}
				
			}
		});
		btnEsconder.setBounds(444, 0, 117, 25);
		getContentPane().add(btnEsconder);
		
		JTabbedPane tbPnEntrada = new JTabbedPane(JTabbedPane.TOP);
		tbPnEntrada.setBounds(12, 59, 624, 374);
		getContentPane().add(tbPnEntrada);
		
		JSplitPane splitPaneEntrada = new JSplitPane();
		tbPnEntrada.addTab("Caixa de entrada", null, splitPaneEntrada, null);
		
		JScrollPane scrPnEntrada = new JScrollPane();
		splitPaneEntrada.setLeftComponent(scrPnEntrada);
		
		JList<Mensagem> listEntrada = new JList<Mensagem>();
		scrPnEntrada.setViewportView(listEntrada);
		DefaultListModel<Mensagem>modelMensagemEntrada = new DefaultListModel<Mensagem>();
		for(Mensagem msg : Sistema.getLogado().getCaixaDeEntrada()){
			modelMensagemEntrada.addElement(msg);
		}
		listEntrada.setModel(modelMensagemEntrada);
		
		JScrollPane scrPnEntradaLer = new JScrollPane();
		splitPaneEntrada.setRightComponent(scrPnEntradaLer);
		
		JTextPane textPaneEntradaLer = new JTextPane();
		scrPnEntradaLer.setViewportView(textPaneEntradaLer);
		
		JSplitPane splitPaneSaida = new JSplitPane();
		tbPnEntrada.addTab("Caixa de saída", null, splitPaneSaida, null);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPaneSaida.setLeftComponent(scrollPane);
		
		JList<Mensagem> listSaida = new JList<Mensagem>();
		listSaida.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listSaida);
		DefaultListModel<Mensagem>modelMensagemSaida = new DefaultListModel<Mensagem>();
		for(Mensagem msg : Sistema.getLogado().getCaixadeSaida()){
			modelMensagemSaida.addElement(msg);
		}
		listSaida.setModel(modelMensagemSaida);
		listSaida.setSelectedIndex(0);

		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPaneSaida.setRightComponent(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		textArea.setText("Data:" + listSaida.getSelectedValue().getData()+"\n\n"+
						"Assunto: "+listSaida.getSelectedValue().getAssunto()+"\n\n"+
						"Mensagem:\n"+listSaida.getSelectedValue().getMensagem());
		
		
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
		
		
		

	}
}
