package ventana;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

import utils.CacheImagenes;


public class JTabbedPaneGestion extends JTabbedPane {


	/**
	 * 
	 * @return
	 */
	public JTabbedPaneGestion (){
	
		ImageIcon icono = CacheImagenes.getCacheImagenes().getIcono("duke1-32x32.png");
		
		this.addTab("Concesionario", icono, new PanelGestionConcesionario(), "Concesionario");
		this.addTab("Fabricante", icono, new PanelGestionFabricante(), "Fabricante");
		this.addTab("Cliente", icono, new PanelGestionCliente(), "Cliente");
		this.addTab("Coche", icono, new PanelGestionCoche(), "Coche");
		this.addTab("Ventas", icono, new PanelGestionVenta(), "Ventas");
		
		this.setSelectedIndex(0);  
		
	}	
}
