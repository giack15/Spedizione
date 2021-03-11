package tabellapkg;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import spedizionepkg.Spedizione;
import spedizionepkg.SpedizioneAssicurata;

/**
 * Classe che implementa il modello di {@link AdminTable} per l' amministratore
 * @author Giacomo
 * @version 26/01/2021
 *
 */
public class AdminTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Nomi delle colonne della tabella
	 */
	private String nomeColonna[] = {"Username", "Codice", "Destinazione", "Peso", "Data", "Stato", "Assicurazione"};
	/**
	 * Elenco di tutte le spedizioni inserite
	 */
	protected Vector<Spedizione> elSped;
	/**
	 * Formato della data
	 */
	protected DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Costruttore di {@link AdminTableModel}
	 * @param v {@link elSped}
	 */
	public AdminTableModel(Vector<Spedizione> v) {
		elSped = v;
		Collections.sort(elSped);
	}

	/**
	 * Metodo che restituisce il numero delle colonne della tabella
	 * @return numero di colonne della tabella
	 */
	@Override
	public int getColumnCount() {
		return nomeColonna.length;
	}

	/**
	 * Metodo per l' inserimento degli elementi in tabella
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0: return elSped.elementAt(rowIndex).getUsername();
		case 1: return elSped.elementAt(rowIndex).getCodice();
		case 2: return elSped.elementAt(rowIndex).getDestinazione();
		case 3: return (elSped.elementAt(rowIndex).getPeso() + " Kg");
		case 4: return sdf.format(elSped.elementAt(rowIndex).getData());
		case 5: return elSped.elementAt(rowIndex).getStato();
		case 6: if(elSped.elementAt(rowIndex) instanceof SpedizioneAssicurata) {
					Double ass = ((SpedizioneAssicurata) elSped.elementAt(rowIndex)).getAssicurazione();
					return (ass + " €");
				}
				else
					return "";		
		default: return "";
		}
	}
	
	/**
	 * Metodo che restituisce il numero di righe della tabella
	 * @return numero di righe della tabella
	 */
	@Override
	public int getRowCount() {
		return elSped.size();
	}
	
	/**
	 * Metodo che setta le celle della tabella editabili oppure no
	 * @return false perchè tutte le celle della tabella dell' amministratore non sono editabili
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	/**
	 * Restituisce i nomi delle colonne della tabella
	 * @return {@link nomeColonna}
	 */
	public String[] getNomeColonna() {
		return nomeColonna;
	}
}