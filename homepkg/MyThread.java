package homepkg;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import spedizionepkg.Spedizione;

/**
 * Classe che implementa il Thread per cambiare automaticamente lo stato delle spedizioni
 * @author Giacomo
 * @version 26/01/2021
 *
 */
public class MyThread extends Thread implements Runnable{

	/**
	 * Oggetto usato per implementare il metodo {@link #stopThread()}
	 * 
	 */
	private final AtomicBoolean running = new AtomicBoolean(false);
	/**
	 * Elenco di tutte le spedizioni inserite
	 */
	private Vector<Spedizione> elSped;
	/**
	 * Variabile per la generazione di un numero casuale che rappresenta l' indice della
	 * spedizione a cui cambiare lo stato
	 */
	private Random rnd;
	/**
	 * Tabella dell'amministratore che deve essere aggiornata dopo che lo stato è cambiato
	 */
	private JTable tab;
	
	/**
	 * Costruttore di {@link MyThread}
	 * @param v {@link elSped}
	 * @param t {@link tab}
	 */
	public MyThread(Vector<Spedizione> v, JTable t) {
		elSped = v;
		tab = t;
		rnd = new Random();
	}
	
	/**
	 * Metodo run() del thread ed invoca {@link #cambiaStato()} 
	 */
	@Override
	public void run() {
		running.set(true);
		
		while(running.get()) {
			try {
				Thread.sleep(6000);
				cambiaStato();
				((AbstractTableModel) tab.getModel()).fireTableDataChanged();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metodo che cambia lo stato di una spedizione. Una spedizione che cambia stato può
	 * passare solamente allo stato immediatamente successivo
	 */
	public void cambiaStato() {
		if(elSped.size() > 0) {
			int index = rnd.nextInt(elSped.size());
			switch(elSped.get(index).getStato()) {
			case "IN PREPARAZIONE": elSped.get(index).setStato("IN TRANSITO");
								break;
			case "IN TRANSITO": int prob = rnd.nextInt(11);
								if(prob%3 == 0) 
									elSped.get(index).setStato("FALLITA");
								else
									elSped.get(index).setStato("RICEVUTA");
								break;
			case "RIMBORSO RICHIESTO": elSped.get(index).setStato("RIMBORSO EROGATO");
			break;
			}
		}
	}
	
	/**
	 * Metodo per fermare il thread poichè il metodo stop() è deprecato
	 */
	public void stopThread() {
		this.running.set(false);
	}
}