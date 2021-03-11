package homepkg;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import mainpkg.MainClass;
import spedizionepkg.SpedizioneAssicurata;
import tabellapkg.AdminTable;

/**
 * Classe che estende {@link Home} ed implementa la home page dell' amministratore
 * @author Giacomo
 * @version 26/01/2021
 * @see MyThread
 *
 */
public class AdminHome extends Home{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Pulsante per il logout dell' amministratore
	 */
	private JButton bEsci;
	/**
	 * Pulsante per eliminare una riga (cioè una spedizione) dalla tabella
	 */
	private JButton bElimina;
	/**
	 * Thread per cambiare automaticamente lo stato delle spedizioni
	 */
	private MyThread t;
	
	/**
	 * Costruttore di {@link AdminHome}
	 */
	public AdminHome() {	
		tab = new AdminTable(elencoSpedizioni);
		bEsci = new JButton("Logout");
		bElimina = new JButton("Elimina");
		t = new MyThread(elencoSpedizioni, tab.getTable());
		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		bElimina.setToolTipText("Elimina la riga selezionata");
		bv.setButton(bEsci);
		bv.setButton(bElimina);
		
		//row 0, column 1: lSpedizioni
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(lSpedizioni, gbc);
		
		//row 1, column 1: tab
		gbc.gridy = 1;
		gbc.insets = new Insets(20, 0, 0, 0);
		add(tab, gbc);
		
		//row 2, column 1: bEsci, bElimina, bSalva
		gbc.gridy = 2;
		gbc.insets = new Insets(10, -150, 0, 0);
		add(bEsci, gbc);
		gbc.insets = new Insets(10, 0, 0, 0);
		add(bElimina, gbc);
		gbc.insets = new Insets(10, 150, 0, 0);
		add(bSalva, gbc);		
		
		bEsci.addActionListener(this);
		bElimina.addActionListener(this);
		bSalva.addActionListener(this);
		
		t.start();
	}
	
	/**
	 * Evento legato a {@link bEsci}, {@link bElimina} e {@link bSalva}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		TableModel tm;
		
		if(obj == bEsci) { //bEsci
			Object[] options = {"Sì", "No"};
			UIManager.put("Button.background", Color.LIGHT_GRAY);
			int i = JOptionPane.showOptionDialog(null, "Sei sicuro di voler uscire dal programma?", "", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options, options);
			if(i == JOptionPane.OK_OPTION) {
				t.stopThread();
				MainClass.showPanel("Benvenuto", "Benvenuto");
				MainClass.setDimFrame(250, 130);
			}
		}
		else if(obj == bElimina) { //bElimina
			t.stopThread();
			
			if(tab.getTable().getSelectedRow() != -1) {
				if(controllaStato(tab.getTable().getSelectedRow())) {
					elencoSpedizioni.remove(tab.getTable().getSelectedRow()); 
					tm = tab.getTable().getModel();
					((AbstractTableModel) tm).fireTableDataChanged();
				}
				else {
					UIManager.put("Button.background", Color.LIGHT_GRAY);
					JOptionPane.showMessageDialog(null, "Non puoi eliminare questa spedizione", "Attenzione", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				UIManager.put("Button.background", Color.LIGHT_GRAY);
				JOptionPane.showMessageDialog(null, "Seleziona una spedizione da eliminare", "Attenzione", JOptionPane.WARNING_MESSAGE);
			}
		}
		else { //bSalva
			t.stopThread();
			salvaSpedizioni();
			UIManager.put("Button.background", Color.LIGHT_GRAY);
			JOptionPane.showMessageDialog(null, "Le modifiche sono state salvate", "Salvataggio effettuato", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Metodo che controlla che la spedizione da eliminare rispetti le condizioni per l'
	 * eliminazione
	 * @param rowIndex indice della spedizione da eliminare
	 * @return true se la spedizione può essere eliminata, false altrimenti
	 */
	public boolean controllaStato(int rowIndex) {
		if(tab.getTable().getValueAt(rowIndex, 5).toString().equals("RICEVUTA") || tab.getTable().getValueAt(rowIndex, 5).toString().equals("RIMBORSO EROGATO"))
			return true;
		else if(!(elencoSpedizioni.get(rowIndex) instanceof SpedizioneAssicurata) && tab.getTable().getValueAt(rowIndex, 5).toString().equals("FALLITA")) 
			return true;
		else
			return false;
	}
}