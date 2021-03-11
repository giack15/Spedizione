package loginpkg;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import mainpkg.WelcomePanel;
import mainpkg.MainClass;

/**
 * Classe che implementa il pannello di registrazione estendendo {@link RegistrazionePanel}.
 * @author Giacomo
 * @version 26/01/2021
 *
 */
public class Registrazione extends RegistrazionePanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variabile di {@link mainpkg.WelcomePanel} per richiamare {@link WelcomePanel#getElencoutenti()}
	 */
	private WelcomePanel bv;

	/**
	 * Costruttore di {@link Registrazione} per l' implementazione degli eventi
	 * sui pulsanti {@link bIndietro} e {@link bAccedi}
	 */
	public Registrazione() {
		bv = new WelcomePanel();
		
		this.getbAccedi().setText("Registrati");
		this.getbAccedi().setPreferredSize(new Dimension(80, 30));
		
		this.getbAccedi().addActionListener(this);
		this.getbIndietro().addActionListener(this);
	}

	/**
	 * Evento legato ai pulsanti {@link bIndietro} e {@link bAccedi}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == this.getbIndietro()) //bIndietro
			cleanText();
		else { //bAccedi
			if(this.getTxtUsername().getText().isBlank() || String.valueOf(this.getTxtPassword().getPassword()).isBlank() || this.getTxtIndirizzo().getText().isBlank() || this.getTxtCitta().getText().isBlank() || this.getTxtCap().getText().isBlank())
				this.getlErr().setVisible(true);
			else {
				String username = this.getTxtUsername().getText();
				String password = String.valueOf(this.getTxtPassword().getPassword());
				String indirizzo = this.getTxtIndirizzo().getText();
				String citta = this.getTxtCitta().getText();
				String cap = this.getTxtCap().getText();
				citta = citta.substring(0, 1).toUpperCase() + citta.substring(1);
				
				if(!controlloCredenziali(username, password, false)) {
					password = cifraturaPassword(password);
					String credenziali = username + " - " + password + " - " + indirizzo + ", " + citta + ", " + cap;
					salva(credenziali, bv.getElencoutenti());
					JOptionPane.showMessageDialog(null, "Registrazione avvenuta con successo", "Ora puoi accedere", JOptionPane.INFORMATION_MESSAGE);
					cleanText();
				}
				else
					this.getlErr().setVisible(true);
			}
		}
	}
	
	/**
	 * Metodo per il controllo delle credenziali durante il login dell' utente e per il 
	 * controllo dell' username durante la registrazione
	 * @param username username dell' utente
	 * @param password password dell' utente 
	 * @param log se è true controllo anche la password (login), se false controllo solo l' username (registrazione)
	 * @return true se username e/o password dati corrispondono alle credenziali di un utente già presente in {@link WelcomePanel#getElencoutenti()}, 
	 * false altrimenti
	 */
	public boolean controlloCredenziali(String username, String password, boolean log) {
		File f = new File("");
		FileReader fr = null;
		BufferedReader br = null;
		try {
			if(f.getAbsolutePath().endsWith("src"))
				fr = new FileReader(f.getAbsolutePath() + File.separator + bv.getElencoutenti());
			else
				fr = new FileReader(f.getAbsolutePath() + File.separator + "src" + File.separator + bv.getElencoutenti());
			br = new BufferedReader(fr);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			String cred;
			while(br.ready()) {
				cred = br.readLine();
				String word[] = cred.split(" - ");
				if(word[0].equals(username)) {
					if(log) {
						if(word[1].equals(password))
							return true;
					}
					else
						return true;
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Metodo per la cifratura della password (in fase di registrazione e di login): ogni 
	 * lettera è sostituita da quella ad essa successiva nell' alfabeto
	 * @param password password da cifrare
	 * @return stringa che corrisponde alla password cifrata
	 */
	  public String cifraturaPassword(String password) { 
		StringBuffer passwd = new StringBuffer(password);
		for(int i=0; i<password.length(); i++) {
			char c = passwd.charAt(i);
			passwd.setCharAt(i, ++c);
		}
		return passwd.toString();
	}
	
	  /**
	   * Metodo che salva l' utente in {@link WelcomePanel#getElencoutenti()}
	   * @param user credenziali dell' utente registrato che devono essere salvate
	   * @param nomeFile nome del file in cui salvare l' utente ({@link WelcomePanel#getElencoutenti()})
	   */
	   public void salva(String user, String nomeFile) {   
		File f = new File("");
		File fileTesto;
		if(f.getAbsolutePath().endsWith("src"))
			fileTesto = new File(f.getAbsolutePath() + File.separator + nomeFile);
		else 
			fileTesto = new File(f.getAbsolutePath() + File.separator + "src" + File.separator + nomeFile);
		try {
			FileWriter fw = new FileWriter(fileTesto, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(user);
			bw.newLine();
			bw.close();
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(-1);;
		}
	}
	
	/**
	 * Metodo che pulisce il testo delle TextField
	 */
	private void cleanText() {
		this.getlErr().setVisible(false);
		this.getTxtUsername().setText("");
		this.getTxtPassword().setText("");
		this.getTxtIndirizzo().setText("");
		this.getTxtCitta().setText("");
		this.getTxtCap().setText("");
		MainClass.showPanel("LoginUser", "Login utente");
		MainClass.setDimFrame(330, 200);
	}

	/**
	 * Restituisce l' oggetto di tipo {@link WelcomePanel}
	 * @return {@link bv}
	 */
	public WelcomePanel getBv() {
		return bv;
	}
}