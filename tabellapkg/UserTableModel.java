package tabellapkg;

import java.util.Vector;
import spedizionepkg.Spedizione;
import spedizionepkg.SpedizioneAssicurata;

/**
 * Classe che implementa il modello di {@link UserTable} per l' utente
 * @author Giacomo
 * @version 26/01/2021
 *
 */
public class UserTableModel extends AdminTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Elenco delle spedizioni dell' utente
	 */
	private Vector<Spedizione> elSpedUser;
	
	/**
	 * Costruttore di {@link UserTableModel}
	 * @param v {@link elSpedUser}
	 */
	public UserTableModel(Vector<Spedizione> v) {
		super(v);
		elSpedUser = v;
	}
	
	/**
	 * Metodo per l' inserimento degli elementi in tabella
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0: return elSpedUser.elementAt(rowIndex).getUsername();
		case 1: return elSpedUser.elementAt(rowIndex).getCodice();
		case 2: return elSpedUser.elementAt(rowIndex).getDestinazione();
		case 3: return (elSpedUser.elementAt(rowIndex).getPeso() + " Kg");
		case 4: return sdf.format(elSpedUser.elementAt(rowIndex).getData());
		case 5: return elSpedUser.elementAt(rowIndex).getStato();
		case 6: if(elSpedUser.elementAt(rowIndex) instanceof SpedizioneAssicurata) {
					Double ass = ((SpedizioneAssicurata) elSpedUser.elementAt(rowIndex)).getAssicurazione();
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
		return elSpedUser.size();
	}
	
	/**
	 * Metodo che setta le celle della tabella editabili oppure no
	 * @return true se, in quella riga, la spedizione è assicurata e lo stato è "Fallita", false altrimenti
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(elSpedUser.get(rowIndex) instanceof SpedizioneAssicurata && columnIndex == 5 && elSpedUser.get(rowIndex).getStato().equals("FALLITA")) 
			return true;
		else
			return false;
	}
	
	/**
	 * Metodo per gestire le modifiche dell' utente alla tabella
	 */
	@Override
	public void setValueAt(Object val, int rowIndex, int columnIndex) {
		Spedizione s = elSpedUser.elementAt(rowIndex);
		if(columnIndex == 5) {
			int i = 0;
			s.setStato((String) val); 
			while(!(elSped.get(i).getCodice().equals(elSpedUser.get(rowIndex).getCodice())))
				i++;
			elSped.get(i).setStato((String) val);
			fireTableDataChanged();
		}
	}
}