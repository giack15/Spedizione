package spedizionepkg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import loginpkg.RegistrazionePanel;

/**
 * Classe che implementa il form grafico della home dell' utente estendendo {@link RegistrazionePanel}.
 * @author Giacomo
 * @version 26/01/2021
 */
public class SpedizionePanel extends RegistrazionePanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Campo di inserimento della data di spedizione
	 */
	private JTextField txtData;
	/**
	 * Campo di inserimento del valore assicurativo della spedizione
	 */
	private JTextField txtAssicurazione;
	/**
	 * Checkbox per la scelta dell' utente di avere o meno l' assicurazione sulla spedizione
	 */
	private JCheckBox cbAssicurazione;
	/**
	 * Etichetta per segnalare un errore durante l' inserimento dell' assicurazione
	 */
	private JLabel lErrAssicurazione;
	
	/**
	 * Costruttore di {@link SpedizionePanel}
	 */
	public SpedizionePanel() {
		this.build();
	}
	
	/**
	 * Metodo per posizionare i componenti in ordine secondo il GridBagLayout
	 */
	private void build() {
		remove(getTxtPassword());

		//row 0, column 0: lErr
		getlErr().setText("Spedizione non completa");
		
		//row 1, column 0: lIndirizzo
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 0, 10);
		add(getlIndirizzo(), gbc);
		
		//row 2, column 0: txtIndirizzo
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 8, 10);
		add(getTxtIndirizzo(), gbc);
		
		//row 3, column 0: lCitta
		gbc.gridy = 3;
		gbc.insets = new Insets(0, 0, 0, 10);
		add(getlCitta(), gbc);
		
		//row 3, column 1: lCap
		gbc.gridx = 1;
		gbc.insets = new Insets(0, -35, 0, 10);
		add(getlCap(), gbc);
		
		//row 4, column 0: txtCitta
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(0, 0, 8, 10);
		add(getTxtCitta(), gbc);
		
		//row 4, column 1: txtCap
		gbc.gridx = 1;
		gbc.insets = new Insets(0, -35, 8, 10);
		add(getTxtCap(), gbc);
		
		//row 5, column 0: lPeso
		getlUsername().setText("Peso (Kg)");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(0, 0, 0, 10);
		add(getlUsername(), gbc);
		
		//row 6, column 0: txtPeso
		getTxtUsername().setColumns(dimTextfield-10);
		gbc.gridy = 6;
		gbc.insets = new Insets(0, 0, 8, 10);
		add(getTxtUsername(), gbc);
		
		//row 7, column 0: lData
		gbc.gridy = 7;
		getlPassword().setText("Data (gg/mm/aaaa)");
		gbc.insets = new Insets(0, 0, 0, 10);
		add(getlPassword(), gbc);
		
		//row 8, column 0: txtData
		txtData = new JTextField(dimTextfield-7);
		gbc.gridy = 8;
		gbc.insets = new Insets(0, 0, 8, 10);
		add(txtData, gbc);
		
		//row 9, column 0: cbAssicurazione
		cbAssicurazione = new JCheckBox("Spedizione assicurata");
		cbAssicurazione.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 11));
		cbAssicurazione.setSelected(true);
		cbAssicurazione.setFocusable(false);
		gbc.gridy = 9;
		gbc.insets = new Insets(0, 0, 0, 10);
		add(cbAssicurazione, gbc);
		
		//row 10, column 0: txtAssicurazione
		txtAssicurazione = new JTextField(dimTextfield-7);
		gbc.gridy = 10;
		gbc.insets = new Insets(0, 0, 8, 10);
		add(txtAssicurazione, gbc);
		
		//row 10, column 1: lErrAssicurazione
		lErrAssicurazione = new JLabel("Valore non valido");
		lErrAssicurazione.setForeground(Color.RED);
		lErrAssicurazione.setFont(new Font("Helvetica", Font.BOLD, 9));
		lErrAssicurazione.setVisible(false);
		gbc.gridx = 1;
		gbc.insets = new Insets(0, -70, 10, 10);
		add(lErrAssicurazione, gbc);
		
		//row 11, column 1: bIndietro (logout)
		getbIndietro().setText("Logout");
		getbIndietro().setPreferredSize(new Dimension(70, 30));
		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.insets = new Insets(10, 5, 10, 10);
		add(getbIndietro(), gbc);
		
		//row 11, column 0: bInvia (conferma)
		getbAccedi().setText("Conferma");
		getbAccedi().setPreferredSize(new Dimension(80, 30));
		getbAccedi().setToolTipText("Conferma la spedizione");
		gbc.gridx = 1;
		gbc.insets = new Insets(10, -70, 10, 10);
		add(getbAccedi(), gbc);
	}
	
	/**
	 * Restituisce il campo di inserimento della data
	 * @return {@link txtData}
	 */
	public JTextField getTxtData() {
		return txtData;
	}
	/**
	 * Restituisce il campo di inserimento del valore assicurativo
	 * @return {@link txtAssicurazione}
	 */
	public JTextField getTxtAssicurazione() {
		return txtAssicurazione;
	}
	/**
	 * Restituisce la checkBox del valore assicurativo
	 * @return {@link cbAssicurazione}
	 */
	public JCheckBox getCbAssicurazione() {
		return cbAssicurazione;
	}
	/**
	 * Restituisce l' etichetta dell' errore durante l' inserimento del valore assicurativo
	 * @return {@link lErrAssicurazione}
	 */
	public JLabel getlErrAssicurazione() {
		return lErrAssicurazione;
	}
}