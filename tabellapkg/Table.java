package tabellapkg;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 * Classe astratta per l' implementazione delle tabelle dell' amministratore e dell' 
 * utente
 * @author Giacomo
 * @version 26/01/2021
 *
 */
public abstract class Table extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Componente JTable per la tabella
	 */
	protected JTable table;
	/**
	 * Modello della tabella
	 */
	protected AdminTableModel tm;
	/**
	 * Variabile che salva una singola colonna della tabella (vedi {@link #columnName()})
	 */
	protected TableColumn tColumn;
	/**
	 * JScrollPane per {@link table}
	 */
	protected JScrollPane jScrollPane;
	/**
	 * Cell Renderer per posizionare ogni valore della tabella al centro della propria cella
	 */
	protected DefaultTableCellRenderer renderer;
	/**
	 * Intestazione della tabella
	 */
	protected JTableHeader header; 
	
	/**
	 * Costruttore di {@link Table}
	 */
	public Table() {
		jScrollPane = new JScrollPane();
	}
	
	/**
	 * Metodo astratto per la creazione della tabella 
	 */
	public abstract void creaTabella();

	/**
	 * Metodo che inserisce i nomi delle colonne della tabella
	 */
	public void columnName() {
		for(int i=0; i<tm.getNomeColonna().length; i++) {
			tColumn = table.getColumnModel().getColumn(i);
			tColumn.setHeaderValue(tm.getNomeColonna()[i]);
		}
	}
	
	/**
	 * Metodo che imposta la larghezza delle colonne della tabella
	 */
	public void columnWidth() {
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(230);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(85);
	}
	
	/**
	 * Restituisce il componente table della tabella
	 * @return {@link table}
	 */
	public JTable getTable() {
		return table;
	}
}