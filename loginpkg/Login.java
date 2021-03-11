package loginpkg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import homepkg.AdminHome;
import homepkg.UserHome;
import mainpkg.MainClass;

/**
 * Classe che definisce il pannello di login sia per l' amministratore che per l' utente
 * estendendo {@link LoginPanel}
 * @author Giacomo
 * @version 26/01/2021
 * @see LoginPanel
 *
 */
public class Login extends LoginPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Nome di login dell' amministratore
	 */
	private final String adminName = "admin";
	/**
	 * Password di login dell' amministratore
	 */
	private final String adminPassword = "password";
	/**
	 * Variabile di {@link Registrazione} per invocare i metodi {@link Registrazione#controlloCredenziali(String, String, boolean)}
	 * e {@link Registrazione#cifraturaPassword(String)}
	 * 
	 */
	private Registrazione r;
	
	/**
	 * Costruttore di {@link Login} per l' implementazione degli eventi sui pulsanti 
	 * {@link bIndietro}, {@link bAccedi} e {@link bRegistrati } e per l'accesso dell' 
	 * amministratore o dell' utente
	 * @param b se true è l' utente ad autenticarsi, se false è l' amministratore ad 
	 * autenticarsi
	 */
	public Login(boolean b) {
		if(b)
			this.getbRegistrati().setVisible(true);
		
		r = new Registrazione();
		this.getbAccedi().addActionListener(this);
		this.getbIndietro().addActionListener(this);
		this.getbRegistrati().addActionListener(this);
	}
	
	/**
	 * Evento legato a {@link bIndietro}, {@link bAccedi} e {@link bRegistrati}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == this.getbIndietro()) { //bIndietro
			this.getlErr().setVisible(false);
			MainClass.showPanel("Benvenuto", "Benvenuto");
			MainClass.setDimFrame(250, 130);
		}
		else if(obj == this.getbRegistrati()) { //bRegistrati
			this.getlErr().setVisible(false);
			MainClass.showPanel("Registrazione", "Registrazione");
			MainClass.setDimFrame(330, 260);
		}
		else { //bAccedi
			String username = this.getTxtUsername().getText();
			String password = String.valueOf(this.getTxtPassword().getPassword());
			
			if(this.getbRegistrati().isVisible()) { //login utente
				password = r.cifraturaPassword(password);
				if(r.controlloCredenziali(username, password, true)) {
					this.getlErr().setVisible(false);
					JPanel p = new UserHome(username);
					MainClass.addPanel(p, "HomeUser");
					MainClass.showPanel("HomeUser", "Home " + username);
					MainClass.setDimFrame(1040, 400);
				}
				else
					this.getlErr().setVisible(true);
			}
			else { //login amministratore
				if(username.equals(adminName) && password.equals(adminPassword)) {
					this.getlErr().setVisible(false);
					JPanel p = new AdminHome();
					MainClass.addPanel(p, "HomeAdmin");
					MainClass.showPanel("HomeAdmin", "Home Amministratore");
					MainClass.setDimFrame(840, 500);
				}
				else
					this.getlErr().setVisible(true);
			}
		}
		this.getTxtUsername().setText("");
		this.getTxtPassword().setText("");
	}
}