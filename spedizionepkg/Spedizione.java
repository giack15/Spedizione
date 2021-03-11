package spedizionepkg;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe che implementa la spedizione normale
 * @author Giacomo
 * @version 26/01/2021
 *
 */
public class Spedizione implements Comparable<Spedizione>{
	/**
	 * Username dell' utente che ha effettuato la spedizione
	 */
	private String username;
	/**
	 * Codice della spedizione
	 */
	private String codice;
	/**
	 * Destinazione della spedizione: è composta da indirizzo, città e CAP
	 */
	private String destinazione;
	/**
	 * Peso della spedizione
	 */
	private Double peso;
	/**
	 * Data della spedizione
	 */
	private Date data;
	/**
	 * Stato in cui si trova la spedizione
	 */
	private String stato;
	/**
	 * Formato di {@link data}
	 */
	protected SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Costruttore di {@link Spedizione}
	 * @param u {@link username}
	 * @param c {@link codice}
	 * @param d {@link destinazione}
	 * @param p {@link peso}
	 * @param dt {@link data}
	 * @param s {@link stato}
	 */
	public Spedizione(String u, String c, String d, Double p, Date dt, String s) {
		username = u;
		codice = c;
		destinazione = d;
		peso = p;
		data = dt;
		setStato(s);
	}
	
	/**
	 * Metodo per ordinare le spedizioni
	 */
	@Override
	public int compareTo(Spedizione o) {
		int c = username.compareTo(o.getUsername());
		if(c != 0)
			return c;
		else
			return data.compareTo(o.getData());
	}
	
	/**
	 * Restituisce la spedizione normale in formato stringa
	 * @return Stringa delle informazioni della spedizione normale
	 */
	public String toString() {
		return username + " - " + codice + " - " + destinazione + " - " + peso + " - " + sdf.format(data) + " - " + stato + " - null";
	}
	
	/**
	 * Restituisce il codice della spedizione
	 * @return {@link codice}
	 */
	public String getCodice() {
		return codice;
	}
	/**
	 * Restituisce la destinazione della spedizione
	 * @return {@link destinazione}
	 */
	public String getDestinazione() {
		return destinazione;
	}
	/**
	 * Restituisce il peso della spedizione
	 * @return {@link peso}
	 */
	public Double getPeso() {
		return peso;
	}
	/**
	 * Restituisce la data della spedizione
	 * @return {@link data}
	 */
	public Date getData() {
		return data;
	}
	/**
	 * Restituisce lo stato della spedizione
	 * @return {@link stato}
	 */
	public String getStato() {
		return stato;
	}
	/**
	 * Setta lo stato attuale della spedizione con un nuovo stato
	 * @param stato nuovo stato della spedizione
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}
	/**
	 * Restituisce l' username dell' utente della spedizione
	 * @return {@link username}
	 */
	public String getUsername() {
		return username;
	}
}