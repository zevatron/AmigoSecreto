package aplicacao;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import fachada.Sistema;
import modelo.Grupo;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BoxLayout;


public class MeusGrupos extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeusGrupos frame = new MeusGrupos();
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
	public MeusGrupos() {
		setTitle("Meus Grupos");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new GridLayout(3, 4, 0, 0));
		
		ArrayList<JButton> grupos = new ArrayList<JButton>();
		ArrayList<JButton> gruposP = new ArrayList<JButton>();
		try {
			Sistema.conectar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String titulo;
		for(int i=0; i< Sistema.listarGruposAdmin(Sistema.getLogado() ).size() ; i++ ){
			titulo = Sistema.listarGruposAdmin(Sistema.getLogado() ).get(i).getTitulo();
			final JButton btg = new JButton();
			btg.setBackground(new Color(255, 160, 122));
			btg.setText(titulo);
			grupos.add(btg);
			getContentPane().add(grupos.get(i));
			grupos.get(i).addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
//					JFrame novaTela = new JFrame(Sistema.listarGruposAdmin(Sistema.getLogado() ).get(0).getTitulo());
//					novaTela.setVisible(true);
					try {
						Sistema.conectar();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Sistema.setGrupoSelecionado(Sistema.localizarGrupo(btg.getText()));
					GrupoAdmin grupoAdmin = new GrupoAdmin();
					grupoAdmin.setVisible(true);
					Sistema.desconectar();
	
				}
			});
		}
		
		//llistar grupos participando
		for(int i=0; i< Sistema.listarGruposParticipando(Sistema.getLogado()).size() ; i++ ){
			titulo = Sistema.listarGruposParticipando(Sistema.getLogado()).get(i).getTitulo();
			final JButton btg = new JButton();
			btg.setText("Participando "+titulo);
			gruposP.add(btg);
			getContentPane().add(gruposP.get(i));
			gruposP.get(i).addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
//					JFrame novaTela = new JFrame(Sistema.listarGruposAdmin(Sistema.getLogado() ).get(0).getTitulo());
//					novaTela.setVisible(true);
					try {
						Sistema.conectar();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Sistema.setGrupoSelecionado(Sistema.localizarGrupo(btg.getText().split(" ")[1]));
					GrupoParticipante grupoParticipante = new GrupoParticipante();
					grupoParticipante.setVisible(true);
					Sistema.desconectar();
	
				}
			});
		}
		
		Sistema.desconectar();
	}
}
