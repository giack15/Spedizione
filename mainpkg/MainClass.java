package mainpkg;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import loginpkg.Login;
import loginpkg.Registrazione;

/**
 * Classe che definisce il frame del programma
 * @author Giacomo
 * @version 26/01/2021
 */
public class MainClass implements WindowListener{

	/**
	 *Frame del programma 
	 */
	private static JFrame frame;
	/**
	 *Panello che contiene i pannelli delle varie finestre del programma 
	 */
	private static JPanel panels;
	/**
	 * Layout di {@link panels}
	 */
	private static CardLayout layout;
	
	/**
	 * Costruttore di {@link MainClass}
	 */
	public MainClass() {
		frame = new JFrame("Benvenuto");
		panels = new JPanel();
		layout = new CardLayout();
		JPanel pWelcome = new WelcomePanel();
		JPanel pLoginAdmin = new Login(false);
		JPanel pLoginUser = new Login(true);
		JPanel pRegistrazione = new Registrazione();
		
		setDimFrame(250, 130);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		panels.setLayout(layout);
		
		panels.add(pWelcome, "Benvenuto");
		panels.add(pLoginAdmin, "LoginAdmin");
		panels.add(pLoginUser, "LoginUser");
		panels.add(pRegistrazione, "Registrazione");
		frame.add(panels);
		
		frame.setVisible(true);
		
		frame.addWindowListener(this);
	}
	
	/**
	 * Metodo main del programma
	 * @param args argomento di {@link main}
	 */
	public static void main(String[] args) {
		new MainClass();
	}
	
	/**
	 * Metodo per settare la dimensione di {@link frame}
	 * @param width  larghezza di {@link frame}
	 * @param height  altezza di {@link frame}
	 */
	public static void setDimFrame(int width, int height) {
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
	}
	
	/**
	 * Metodo per mostrare un nuovo pannello e settare il titolo di {@link frame}
	 * @param p  Container in cui cercare il pannello
	 * @param t  nome del pannello da mostrare
	 */
	public static void showPanel(String p, String t) {
		layout.show(panels, p);
		frame.setTitle(t);
	}
	
	/**
	 * Metodo per aggiungere un pannello a {@link panels}
	 * @param p  pannello da aggiungere a {@link panels}
	 * @param s  nome del pannello
	 */
	public static void addPanel(JPanel p, String s) {
		panels.add(p, s);
	}
	
	/**
	 * Metodo che cattura la chiusura di {@link frame}
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		Object[] options = {"S�", "No"};
		UIManager.put("Button.background", Color.LIGHT_GRAY);		
		int n = JOptionPane.showOptionDialog(null, "Vuoi uscire? Le modifiche non salvate andranno perse", "Sei sicuro?", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options, options);
		if(n == JOptionPane.OK_OPTION) 
			System.exit(0);
	}
	@Override
	public void windowOpened(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
}