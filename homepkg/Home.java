package homepkg;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mainpkg.WelcomePanel;
import spedizionepkg.Spedizione;
import spedizionepkg.SpedizioneAssicurata;
import tabellapkg.Table;

/**
 * Classe astratta per la home page dell' amministratore e dell' utente
 * @author Giacomo
 * @version 26/01/2021
 *
 */
public abstract class Home extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variabile di {@link WelcomePanel} per invocare il metodo {@link WelcomePanel#setButton(JButton)}
	 */
	protected WelcomePanel bv;
	/**
	 * Elenco di tutte le spedizioni inserite
	 */
	protected Vector<Spedizione> elencoSpedizioni;
	/**
	 * Tabella per l' amministratore con tutte le spedizioni inserite, e per l' utente
	 * con solo le spedizioni dell' utente stesso
	 */
	protected Table tab;
	/**
	 * Etichetta associata a {@link tab}
	 */
	protected JLabel lSpedizioni;
	/**
	 * Pulsante per salvare le modifiche apportate a {@link tab}
	 */
	protected JButton bSalva;
	/**
	 * Oggetto {@link GridBagConstraints} per l' inserimento dei componenti nel pannello
	 * con un layout di tipo GridBagLayout
	 */
	protected GridBagConstraints gbc;
	
	/**
	 * Costruttore di {@link Home}
	 */
	public Home() {
		bv = new WelcomePanel();
		elencoSpedizioni = new Vector<Spedizione>();
		lSpedizioni = new JLabel("Elenco spedizioni");
		bSalva = new JButton("Salva");
		
		bv.setButton(bSalva);
		lSpedizioni.setFont(new Font("Helvetica", Font.BOLD, 14));
		bSalva.setToolTipText("Salva le modifiche apportate alla tabella");
		
		aggiungiSpedizione();
	}
	
	/**
	 * Metodo che legge da {@link WelcomePanel#getElencospedizioni()} tutte le spedizioni
	 * inserite e le salva in {@link elencoSpedizioni}
	 */
	public void aggiungiSpedizione() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		BufferedReader br = openFile(bv.getElencospedizioni());
		
		try {
			String infoSped;
			Spedizione s;
			while(br.ready()) {
				infoSped = br.readLine();
				String word[] = infoSped.split(" - ");
				if(word[6].equals("null")) 
					s = new Spedizione(word[0], word[1], word[2], Double.valueOf(word[3]), sdf.parse(word[4]), word[5]);
				else
					s = new SpedizioneAssicurata(word[0], word[1], word[2], Double.valueOf(word[3]), sdf.parse(word[4]), word[5], Double.valueOf(word[6]));
				
				elencoSpedizioni.add(s);
			}
		}catch(ParseException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo che salva in {@link WelcomePanel#getElencospedizioni()} tutte le spedizioni
	 * presenti in {@link elencoSpedizioni}
	 */
	public void salvaSpedizioni() {
		File f = new File("");
		File fileSped;
		if(f.getAbsolutePath().endsWith("src")) 
			fileSped = new File(f.getAbsolutePath() + File.separator + bv.getElencospedizioni());
		else 
			fileSped = new File(f.getAbsolutePath() + File.separator + "src" + File.separator + bv.getElencospedizioni());
		
		try {
			FileWriter fw = new FileWriter(fileSped, false); 
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i=0; i<elencoSpedizioni.size(); i++) {
				String s = elencoSpedizioni.get(i).toString();
				bw.write(s);
				bw.newLine();
			}
			bw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo che apre un file, passato come parametro, in lettura
	 * @param nomeFile file da aprire in lettura
	 * @return un oggetto di tipo {@link BufferedReader}
	 */
	public BufferedReader openFile(String nomeFile) {
		File f = new File("");
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			if(f.getAbsolutePath().endsWith("src"))
				fr = new FileReader(f.getAbsolutePath() + File.separator + nomeFile);
			else 
				fr = new FileReader(f.getAbsolutePath() + File.separator + "src" + File.separator + nomeFile);
			br = new BufferedReader(fr);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return br;
	}
}