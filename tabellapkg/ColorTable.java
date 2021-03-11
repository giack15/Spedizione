package tabellapkg;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Classe per la scelta del colore delle righe della tabella a seconda dello stato
 * @author Giacomo
 * @version 26/01/2021
 *
 */
public class ColorTable extends DefaultTableCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Colore attuale della riga
	 */
	private Color coloreAttuale = Color.WHITE;
	
	/**
	 * Costruttore di {@link ColorTable}
	 */
	public ColorTable() {
		
	}
	
	/**
	 * Metodo per scegliere il colore della riga della tabella in funzione dello stato 
	 * della spedizione
	 */
	@Override
	public Component getTableCellRendererComponent(JTable t, Object o, boolean selected, boolean focused, int row, int column) {
		if(t.getValueAt(row, 5).toString().equals("IN PREPARAZIONE"))
			coloreAttuale = Color.CYAN;
		else if(t.getValueAt(row, 5).toString().equals("IN TRANSITO")) {
			coloreAttuale = Color.PINK;
		}
		else if(t.getValueAt(row, 5).toString().equals("RICEVUTA")) {
			coloreAttuale = Color.GREEN;
		}
		else if(t.getValueAt(row, 5).toString().equals("FALLITA")) {
			coloreAttuale = Color.RED;
		}
		else if(t.getValueAt(row, 5).toString().equals("RIMBORSO RICHIESTO")) {
			coloreAttuale = Color.ORANGE;
		}
		else if(t.getValueAt(row, 5).toString().equals("RIMBORSO EROGATO")) {
			coloreAttuale = Color.YELLOW;
		}
		
		if(selected) {
			super.setForeground(Color.WHITE);
			super.setBackground(Color.BLUE); 
		}
		else {
			super.setForeground(Color.BLACK); 
			super.setBackground(coloreAttuale); 
		}
		
		setFont(t.getFont()); 
		setValue(o); 
		return this;
	}
}