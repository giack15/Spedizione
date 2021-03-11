package spedizionepkg;

import java.util.Date;

/**
 * Classe che implementa una spedizione assicurata estendendo {@link Spedizione}
 * @author Giacomo
 * @version 26/01/2021
 *
 */
public class SpedizioneAssicurata extends Spedizione{
	/**
	 * Valore assicurativo associato alla spedizione
	 */
	private Double assicurazione;
	
	/**
	 * Costruttore di {@link SpedizioneAssicurata}
	 * @param u {@link username}
	 * @param c {@link codice}
	 * @param d {@link destinazione}
	 * @param p {@link peso}
	 * @param dt {@link data}
	 * @param s {@link stato}
	 * @param a {@link assicurazione}
	 */
	public SpedizioneAssicurata(String u, String c, String d, Double p, Date dt, String s, Double a) {
		super(u, c, d, p, dt, s);
		assicurazione = a;
	}
	
	/**
	 * Restituisce la spedizione assicurata in formato stringa
	 * @return Stringa delle informazioni della spedizione assicurata
	 */
	@Override
	public String toString() {
		return getUsername() + " - " + getCodice() + " - " + getDestinazione() + " - " + getPeso() + " - " + sdf.format(getData()) + " - " + getStato() + " - " + assicurazione;
	}

	/**
	 * Restituisce il valore assicurativo della spedizione
	 * @return {@link assicurazione}
	 */
	public Double getAssicurazione() {
		return assicurazione;
	}
}