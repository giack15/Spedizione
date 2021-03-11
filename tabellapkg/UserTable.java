package tabellapkg;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import spedizionepkg.Spedizione;

/**
 * Classe che estende {@link Table} e crea la tabella per l' utente
 * @author Giacomo
 * @version 26/01/2021
 *
 */
public class UserTable extends Table{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ComboBox per la scelta dello stato di una spedizione assicurata e fallita
	 */
	private JComboBox<String> cbStati;
	
	/**
	 * Costruttore di {@link UserTable}
	 * @param elencoSpedizioni elenco delle spedizioni dell' utente
	 */
	public UserTable(Vector<Spedizione> elencoSpedizioni) {	
		tm = new UserTableModel(elencoSpedizioni);
		creaTabella();
		tColumn = table.getColumnModel().getColumn(5);
		cbStati = new JComboBox<String>();
		
		cbStati.addItem("FALLITA");
		cbStati.addItem("RIMBORSO RICHIESTO");
		tColumn.setCellEditor(new DefaultCellEditor(cbStati));

		add(jScrollPane);
	}
	
	/**
	 * Metodo che crea la tabella dell' utente
	 */
	@Override
	public void creaTabella() {
		table = new JTable(tm);
		header = table.getTableHeader();
		header.setBackground(Color.WHITE);
		table.setDefaultRenderer(Object.class, new ColorTable());
		table.setPreferredScrollableViewportSize(new Dimension(755, 200));
		table.setRowHeight(20); 
		table.setFillsViewportHeight(true);
		
		columnName();
		columnWidth();
		renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        jScrollPane.setViewportView(table); 
	}
}