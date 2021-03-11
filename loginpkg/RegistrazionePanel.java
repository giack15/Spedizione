package loginpkg;

import java.awt.Font;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Classe che implementa il form grafico del pannello di {@link Registrazione}
 * @author Giacomo
 * @version 26/01/2021
 * @see LoginPanel
 *
 */
public class RegistrazionePanel extends LoginPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Etichetta per {@link txtIndirizzo}
	 */
	private JLabel lIndirizzo;
	/**
	 * Etichetta per {@link txtCitta}
	 */
	private JLabel lCitta;
	/**
	 * Etichetta per {@link txtCap}
	 */
	private JLabel lCap;
	/**
	 * Campo di inserimento dell' indirizzo di spedizione
	 */
	private JTextField txtIndirizzo;
	/**
	 * Campo di inserimento della citta di spedizione
	 */
	private JTextField txtCitta;
	/**
	 * Campo di inserimento del CAP della citta di spedizione
	 */
	private JTextField txtCap;
	
	/**
	 * Costruttore di {@link RegistrazionePanel}
	 */
	public RegistrazionePanel() {
		this.build();
	}
	
	/**
	 * Metodo per posizionare i componenti in ordine secondo il GridBagLayout
	 */
	private void build() {
		//row 5, column 0: lIndirizzo
		lIndirizzo = new JLabel("Indirizzo di spedizione");
		lIndirizzo.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 11));
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(0, 0, 0, 10);
		add(lIndirizzo, gbc);
		
		//row 6, column 0: txtIndirizzo
		txtIndirizzo = new JTextField(dimTextfield);
		gbc.gridy = 6;
		gbc.insets = new Insets(0, 0, 8, 10);
		add(txtIndirizzo, gbc);
		
		//row 7, column 0: lCitta
		lCitta = new JLabel("Città");
		lCitta.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 11));
		gbc.gridy = 7;
		gbc.insets = new Insets(0, 0, 0, 10);
		add(lCitta, gbc);
		
		//row 7, column 1: lCap
		lCap = new JLabel("CAP");
		lCap.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 11));
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.insets = new Insets(0, -50, 0, 10);
		add(lCap, gbc);
		
		//row 8, column 0: txtCitta
		txtCitta = new JTextField(dimTextfield-5);
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.insets = new Insets(0, 0, 8, 10);
		add(txtCitta, gbc);
		
		//row 8, column 1: txtCap
		txtCap = new JTextField(dimTextfield-8);
		gbc.gridx = 1;
		gbc.insets = new Insets(0, -50, 8, 10);
		add(txtCap, gbc);
	}
	
	/**
	 * Restituisce l' etichetta per l' indirizzo
	 * @return {@link lIndirizzo}
	 */
	public JLabel getlIndirizzo() {
		return lIndirizzo;
	}
	/**
	 * Restituisce l' etichetta per la città
	 * @return {@link lCitta}
	 */
	public JLabel getlCitta() {
		return lCitta;
	}
	/**
	 * Restituisce l' etichetta per il CAP
	 * @return {@link lCap}
	 */
	public JLabel getlCap() {
		return lCap;
	}
	/**
	 * Restituisce il campo di inserimento dell' indirizzo
	 * @return {@link txtIndirizzo}
	 */
	public JTextField getTxtIndirizzo() {
		return txtIndirizzo;
	}
	/**
	 * Restituisce il campo di inserimento della città
	 * @return {@link txtCitta}
	 */
	public JTextField getTxtCitta() {
		return txtCitta;
	}
	/**
	 * Restituisce il campo di inserimento del CAP
	 * @return {@link txtCap}
	 */
	public JTextField getTxtCap() {
		return txtCap;
	}
}