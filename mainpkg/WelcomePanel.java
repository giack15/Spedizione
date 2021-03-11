package mainpkg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Classe che definisce il pannello iniziale del programma
 * @author Giacomo
 * @version 26/01/2021
 */
public class WelcomePanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Pulsante per l'accesso come amministratore
	 */
	private JButton bAdmin;
	/**
	 * Pulsante per l'accesso come utente
	 */
	private JButton bUser;
	/**
	 * Nome del file contenente l'elenco degli utenti registrati
	 */
	private final String elencoUtenti = "ElencoUtenti.txt";
	/**
	 * Nome del file contenente l'elenco di tutte le spedizioni effettuate
	 */
	private final String elencoSpedizioni = "ElencoSpedizioni.txt";
	
	/**
	 * Costruttore di {@link WelcomePanel} che crea i file {@link elencoUtenti} e 
	 * {@link elencoSpedizioni}, se non esistono, e crea i componenti per il pannello
	 */
	public WelcomePanel() {
		try {
			String path;
			File f = new File("");
			if(f.getAbsolutePath().endsWith("src"))
				path = f.getAbsolutePath() + File.separator;
			else
				path = f.getAbsolutePath() + File.separator + "src" + File.separator;
			
			File file = new File(path + elencoUtenti);
			if(!file.exists())
				file.createNewFile();

			file = new File(path + elencoSpedizioni);
			if(!file.exists())
				file.createNewFile();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		bAdmin = new JButton("Admin");
		bUser = new JButton("Utente");
		
		setButton(bAdmin);
		setButton(bUser);
		bAdmin.setToolTipText("Accedi come amministratore");
		bUser.setToolTipText("Accedi come utente");
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
		
		add(bAdmin);
		add(bUser);
		
		bAdmin.addActionListener(this);
		bUser.addActionListener(this);
	}

	/**
	 * Evento legato a {@link bAdmin} e {@link bUser}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if(obj == bAdmin) {
			MainClass.showPanel("LoginAdmin", "Login amministratore");
			MainClass.setDimFrame(330, 200);
		}
		else {
			MainClass.showPanel("LoginUser", "Login utente");
			MainClass.setDimFrame(330, 200);
		}
	}
	
	/**
	 * Metodo per settare i pulsanti del programma
	 * @param f pulsante da settare
	 */
	public void setButton(JButton f) {
		f.setPreferredSize(new Dimension(70, 30));
		f.setFont(new Font("Helvetica", Font.BOLD, 10));
		f.setBackground(new Color(220, 220, 220));
		f.setForeground(Color.BLACK);
		f.setFocusable(false);
	}
	
	/**
	 * Restituisce il file con l' elenco degli utenti registrati
	 * @return {@link elencoUtenti}
	 */
	public String getElencoutenti() {
		return elencoUtenti;
	}

	/**
	 * Restituisce il file con l' elenco delle spedizioni inserite
	 * @return {@link elencoSpedizioni}
	 */
	public String getElencospedizioni() {
		return elencoSpedizioni;
	}
}