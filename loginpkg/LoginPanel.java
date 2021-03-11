package loginpkg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import mainpkg.WelcomePanel;

/**
 * Classe che implementa il form grafico del pannello di {@link Login}
 * @author Giacomo
 * @version 26/01/2021
 *
 */
public class LoginPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Etichetta per {@link txtUsername}
	 */
	private JLabel lUsername;
	/**
	 * Etichetta per segnalare un errore durante il login
	 */
	private JLabel lErr;
	/**
	 * Etichetta per {@link txtPassword}
	 */
	private JLabel lPassword;
	/**
	 * Campo di inserimento dell' username dell' amministratore/utente
	 */
	private JTextField txtUsername;
	/**
	 * Campo di inserimento della password dell' amministratore/utente
	 */
	private JPasswordField txtPassword;	
	/**
	 * Pulsante per ritornare a {@link WelcomePanel}
	 */
	private JButton bIndietro;
	/**
	 * Pulsante per accedere a {@link homepkg.AdminHome} oppure a {@link homepkg.UserHome}
	 */
	private JButton bAccedi;
	/**
	 * Pulsante per andare a {@link Registrazione} per la registrazione dell' utente
	 */
	private JButton bRegistrati;
	/**
	 * Lunghezza dei campi di inserimento
	 */
	protected final int dimTextfield = 15;
	/**
	 * Oggetto {@link GridBagConstraints} per l' inserimento dei componenti
	 * nel pannello con un layout di tipo GridBagLayout
	 */
	protected GridBagConstraints gbc;
	/**
	 * Variabile di {@link WelcomePanel} per richiamare il metodo {@link WelcomePanel#setButton(JButton)}
	 * 
	 */
	private WelcomePanel b;
	
	/**
	 * Costruttore di {@link LoginPanel}
	 */
	public LoginPanel() {
		b = new WelcomePanel();
		build();
	}
	
	/**
	 * Metodo per posizionare i componenti in ordine secondo il GridBagLayout
	 */
	private void build() {
		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		//row 0, column 0: lErr
		lErr = new JLabel("Username/Password non validi");
		lErr.setFont(new Font("Helvetica", Font.BOLD, 9));
		lErr.setForeground(Color.RED);
		lErr.setVisible(false);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(lErr, gbc);
		
		//row 1, column 0: lUsername
		lUsername = new JLabel("Username");
		lUsername.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 11));
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 0, 10);
		add(lUsername, gbc);
		
		//row 2, column 0: txtUsername
		txtUsername = new JTextField(dimTextfield);
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 8, 10);
		add(txtUsername, gbc);
		
		//row 3, column 0: lPassword
		lPassword = new JLabel("Password");
		lPassword.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 11));
		gbc.gridy = 3;
		gbc.insets = new Insets(0, 0, 0, 10);
		add(lPassword, gbc);
		
		//row 4, column 0: txtPassword
		txtPassword = new JPasswordField(dimTextfield);
		gbc.gridy = 4;
		gbc.insets = new Insets(0, 0, 8, 10);
		add(txtPassword, gbc);
		
		//row 9, column 0: bIndietro
		bIndietro = new JButton("Indietro");
		b.setButton(bIndietro);
		bIndietro.setPreferredSize(new Dimension(71, 30));
		gbc.gridy = 9;
		gbc.insets = new Insets(5, 0, 0, 5);
		add(bIndietro, gbc);
		
		//row 9, column 0: bAccedi
		bAccedi = new JButton("Accedi");
		gbc.insets = new Insets(5, 75, 0, 10);
		b.setButton(bAccedi);
		bAccedi.setPreferredSize(new Dimension(70, 30));
		gbc.gridy = 9;
		add(bAccedi, gbc);
		
		//row 10, column 0: bRegistrati
		bRegistrati = new JButton("Non hai un account? Registrati ora");
		bRegistrati.setPreferredSize(new Dimension(170, 15));
		bRegistrati.setFont(new Font("Helvetica", Font.BOLD, 8));
		bRegistrati.setBackground(Color.CYAN);
		bRegistrati.setForeground(Color.BLUE);
		bRegistrati.setFocusable(false);
		bRegistrati.setVisible(false);
		gbc.gridy = 10;
		gbc.insets = new Insets(5, 0, 0, 0);
		add(bRegistrati, gbc);
	}
	
	/**
	 * Restituisce l' etichetta per l' username
	 * @return {@link lUsername}
	 */
	public JLabel getlUsername() {
		return lUsername;
	}
	/**
	 * Restituisce l' etichetta per la segnalazione di un errore durante il login
	 * @return {@link lErr}
	 */
	public JLabel getlErr() {
		return lErr;
	}
	/**
	 * Restituisce l' etichetta per la password
	 * @return {@link lPassword}
	 */
	public JLabel getlPassword() {
		return lPassword;
	}
	/**
	 * Restituisce il campo di inserimento dell' username
	 * @return {@link txtUsername}
	 */
	public JTextField getTxtUsername() {
		return txtUsername;
	}
	/**
	 * Restituisce il campo di inserimento della password
	 * @return {@link txtPassword}
	 */
	public JPasswordField getTxtPassword() {
		return txtPassword;
	}
	/**
	 * Restituisce il pulsante per tornare alla finestra precedente
	 * @return {@link bIndietro}
	 */
	public JButton getbIndietro() {
		return bIndietro;
	}
	/**
	 * Restituisce il pulsante per accedere
	 * @return {@link bAccedi}
	 */
	public JButton getbAccedi() {
		return bAccedi;
	}
	/**
	 * Restituisce il pulsante per la registrazione dell' utente
	 * @return {@link bRegistrati}
	 */
	public JButton getbRegistrati() {
		return bRegistrati;
	}
}