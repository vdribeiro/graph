package Grafos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel m_contentPane;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 237, 300);
		m_contentPane = new JPanel();
		m_contentPane.setLayout(null);
		m_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(m_contentPane);
		{
			JButton btnImportarGrafo = new JButton("Importar Grafo");
			btnImportarGrafo.setBounds(10, 11, 200, 23);
			m_contentPane.add(btnImportarGrafo);
		}
		{
			JButton btnGerarGrafo = new JButton("Gerar Grafo");
			btnGerarGrafo.setBounds(10, 45, 200, 23);
			m_contentPane.add(btnGerarGrafo);
		}
		{
			JButton btnExportarGrafo = new JButton("Exportar Grafo");
			btnExportarGrafo.setBounds(10, 181, 200, 23);
			m_contentPane.add(btnExportarGrafo);
		}
		{
			JButton btnSair = new JButton("Sair");
			btnSair.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//accao
				}
			});
			btnSair.setBounds(60, 230, 100, 23);
			m_contentPane.add(btnSair);
		}
		{
			JButton btnPrim = new JButton("Prim");
			btnPrim.setBounds(10, 79, 200, 23);
			m_contentPane.add(btnPrim);
		}
		{
			JButton btnKruskal = new JButton("Kruskal");
			btnKruskal.setBounds(10, 113, 200, 23);
			m_contentPane.add(btnKruskal);
		}
		{
			JButton btnGraficoDeDesempenho = new JButton("Grafico de Desempenho");
			btnGraficoDeDesempenho.setBounds(10, 147, 200, 23);
			m_contentPane.add(btnGraficoDeDesempenho);
		}
	}

}
