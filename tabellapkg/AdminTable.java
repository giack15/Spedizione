package tabellapkg;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import spedizionepkg.Spedizione;

/**
 * Classe che estende {@link Table} e crea la tabella dell' amministratore
 * @author Giacomo
 * @version 26/01/2021
 *
 */
public class AdminTable extends Table{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore di {@link AdminTable}
	 * @param elencoSpedizioni elenco di tutte le spedizioni inserite
	 */
	public AdminTable(Vector<Spedizione> elencoSpedizioni) {
		tm = new AdminTableModel(elencoSpedizioni);   
		creaTabella();
		
        add(jScrollPane);
	}
	
	/**
	 * Metodo che crea la tabella dell' amministratore
	 */
	@Override
	public void creaTabella() {
		table = new JTable(tm);
		header = table.getTableHeader();
		header.setBackground(Color.WHITE);
		table.setDefaultRenderer(Object.class, new ColorTable());
		table.setPreferredScrollableViewportSize(new Dimension(755, 300));
		table.setRowHeight(20);
		table.setFillsViewportHeight(true);
		
		columnName();
		columnWidth();
		renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        jScrollPane.setViewportView(table);
	}
}