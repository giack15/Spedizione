package homepkg;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import loginpkg.Registrazione;
import mainpkg.MainClass;
import spedizionepkg.Spedizione;
import spedizionepkg.SpedizioneAssicurata;
import spedizionepkg.SpedizionePanel;
import tabellapkg.UserTable;

/**
 * Classe che estende {@link Home} ed implementa la home page dell' utente
 * @author Giacomo
 * @version 26/01/2021
 *
 */
public class UserHome extends Home implements ItemListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Pannello con il form per l' inserimento di una nuova spedizione
	 */
	private SpedizionePanel pSped;
	/**
	 * Variabile di {@link Registrazione} per invocare il metodo {@link Registrazione#salva(String, String)}
	 */
	private Registrazione r;
	/**
	 * Username dell' utente loggato
	 */
	private String username;
	/**
	 * Elenco delle spedizioni di {@link username}
	 */
	private Vector<Spedizione> elSpedUser;
	
	/**
	 * Costruttore di {@link UserHome}
	 * @param u {@link username}
	 */
	public UserHome(String u) {
		username = u;
		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		elSpedUser = new Vector<Spedizione>();
		Iterator<Spedizione> i = elencoSpedizioni.iterator();
		while(i.hasNext()) {
			Spedizione s = i.next();
			if(s.getUsername().equals(username)) {
				elSpedUser.add(s);
			}
		}

		pSped = new SpedizionePanel();
		tab = new UserTable(elSpedUser);
		r = new Registrazione();

		compilaDestinazione(username);
		
		//row 0, column 2: lSpedizioni
		gbc.gridx = 2;
		gbc.gridy = 0;
		add(lSpedizioni, gbc);
		
		//row 1, column 0: pSped
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(pSped, gbc);
		
		//row 1, column 2: tab
		gbc.gridx = 2;
		gbc.insets = new Insets(20, 20, 0, 0);
		add(tab, gbc);	
		
		//row 2, column 2: bSalva
		gbc.gridy = 2;
		gbc.insets = new Insets(5, 0, 0, 0);
		add(bSalva, gbc);	
		
		pSped.getCbAssicurazione().addItemListener(this);
		pSped.getbIndietro().addActionListener(this);
		pSped.getbAccedi().addActionListener(this);		
		bSalva.addActionListener(this);
	}

	/**
	 * Evento legato a {@link SpedizionePanel#getCbAssicurazione()}
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(pSped.getCbAssicurazione().isSelected())
			pSped.getTxtAssicurazione().setVisible(true);
		else
			pSped.getTxtAssicurazione().setVisible(false);
	}
	/**
	 * Evento legato a {@link SpedizionePanel#getbIndietro()}, {@link SpedizionePanel#getbAccedi()} e
	 * {@link bSalva}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		
		if(obj == pSped.getbIndietro()) { //bIndietro (Esci)
			Object[] options = {"Sì", "No"};
			UIManager.put("Button.background", Color.LIGHT_GRAY);
			int i = JOptionPane.showOptionDialog(null, "Sei sicuro di voler uscire dal programma?", "", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options, options);
			if(i == JOptionPane.OK_OPTION) {
				pSped.getlErr().setVisible(false);
				MainClass.showPanel("Benvenuto", "Benvenuto");
				MainClass.setDimFrame(250, 130);
			}
		}
		else if(obj == pSped.getbAccedi()) { //bAccedi (Conferma)
			confermaSpedizione();
		}
		else { //bSalva
			pSped.getlErr().setVisible(false);
			salvaSpedizioni();
			UIManager.put("Button.background", Color.LIGHT_GRAY);
			JOptionPane.showMessageDialog(null, "Le modifiche sono state salvate", "Salvataggio effettuato", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Metodo che controlla che i campi della spedizione siano stati compilati correttamente
	 * e invoca {@link Registrazione#salva(String, String)}
	 */
	public void confermaSpedizione() {
		TableModel tm;
		
		pSped.getlUsername().setForeground(Color.BLACK);
		pSped.getlUsername().setText("Peso");
		pSped.getlPassword().setForeground(Color.BLACK);
		pSped.getlPassword().setText("Data (gg/mm/aaaa)");
		pSped.getlErrAssicurazione().setVisible(false);
		if(pSped.getTxtIndirizzo().getText().isBlank() || pSped.getTxtCitta().getText().isBlank() || pSped.getTxtCap().getText().isBlank() || pSped.getTxtUsername().getText().isBlank() || pSped.getTxtData().getText().isBlank() || (pSped.getCbAssicurazione().isSelected() && pSped.getTxtAssicurazione().getText().isBlank())) {
			pSped.getlErr().setVisible(true);
		}
		else {
			pSped.getlErr().setVisible(false);
			Double peso = null;
			try {
				peso = Double.parseDouble(pSped.getTxtUsername().getText());
				pSped.getlUsername().setForeground(Color.BLACK);
				pSped.getlUsername().setText("Peso");
			}catch(NumberFormatException | NullPointerException e) {
				pSped.getlUsername().setForeground(Color.RED);
				pSped.getlUsername().setText("Peso non valido");
			}
			Date data = null;
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				data = sdf.parse(pSped.getTxtData().getText());
				if(data.before(new Date())) {
					pSped.getlPassword().setForeground(Color.RED);
					pSped.getlPassword().setText("Data non valida");
				}
				else {
					pSped.getlPassword().setForeground(Color.BLACK);
					pSped.getlPassword().setText("Data (gg/mm/aaaa)");
				}
			}catch(ParseException e) {
				pSped.getlPassword().setForeground(Color.RED);
				pSped.getlPassword().setText("Data non valida");
			}
			Double assicurazione = null;
			if(pSped.getCbAssicurazione().isSelected()) {
				try {
					assicurazione = Double.parseDouble(pSped.getTxtAssicurazione().getText());
					pSped.getlErrAssicurazione().setVisible(false);
					
					if(assicurazione < 0.01) {
						pSped.getCbAssicurazione().setSelected(false);
						assicurazione = null;
					}
				}catch(NumberFormatException | NullPointerException e) {
					pSped.getlErrAssicurazione().setVisible(true);
				}
			}
			
			if(pSped.getlPassword().getForeground() != Color.RED && pSped.getlUsername().getForeground() != Color.RED && !pSped.getlErrAssicurazione().isVisible()) {
				String destinazione = pSped.getTxtIndirizzo().getText() + ", " + pSped.getTxtCitta().getText() + ", " + pSped.getTxtCap().getText();
				
				int n = (int) (Math.random() * 100) + 1;
				String codice = pSped.getTxtCitta().getText().toUpperCase().substring(0, 3) + peso.intValue() + n;
				
				Spedizione s = null;
				
				if(assicurazione == null)
					s = new Spedizione(username, codice, destinazione, peso, data, "IN PREPARAZIONE");
				else
					s = new SpedizioneAssicurata(username, codice, destinazione, peso, data, "IN PREPARAZIONE", assicurazione);
				
				elencoSpedizioni.add(s);			
				elSpedUser.add(s);
				Collections.sort(elencoSpedizioni);
				
				r.salva(s.toString(), bv.getElencospedizioni());
				
				UIManager.put("Button.background", Color.LIGHT_GRAY);
				JOptionPane.showMessageDialog(null, "Il codice della spedizione è " + codice, "Spedizione confermata", JOptionPane.PLAIN_MESSAGE);		
				
				Collections.sort(elSpedUser);
				tm = tab.getTable().getModel();
				((AbstractTableModel) tm).fireTableDataChanged();
			
				pSped.getTxtUsername().setText("");
				pSped.getTxtData().setText("");
				pSped.getTxtAssicurazione().setText("");
			}
		}
	}
	
	/**
	 * Metodo per compilare in automatico i campi {@link SpedizionePanel#getTxtIndirizzo()}, 
	 * {@link SpedizionePanel#getTxtCitta()} e {@link SpedizionePanel#getTxtCap()}
	 * @param username {@link username}
	 */
	public void compilaDestinazione(String username) {
		BufferedReader br = openFile(bv.getElencoutenti());
		
		try {
			String credenzialiSalvate;
			while(br.ready()) {
				credenzialiSalvate = br.readLine();
				String word[] = credenzialiSalvate.split(" - ");
				if(word[0].equals(username)) {
					String dest[] = word[2].split(", ");
					pSped.getTxtIndirizzo().setText(dest[0]);
					pSped.getTxtCitta().setText(dest[1]);
					pSped.getTxtCap().setText(dest[2]);
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo per pulire il testo delle TextField 
	 */
	public void cleanText() {
		pSped.getTxtIndirizzo().setText("");
		pSped.getTxtCitta().setText("");
		pSped.getTxtCap().setText("");
		pSped.getTxtUsername().setText("");
		pSped.getTxtData().setText("");
		pSped.getTxtAssicurazione().setText("");
	}
}