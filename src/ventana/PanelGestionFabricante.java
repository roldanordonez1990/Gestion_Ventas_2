package ventana;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Concesionario;
import model.Fabricante;
import model.controladores.ConcesionarioControlador;
import model.controladores.FabricanteControlador;
import utils.CacheImagenes;

public class PanelGestionFabricante extends JPanel {

	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;

	JTextField jtfId = new JTextField(5);
	JTextField jtfCif = new JTextField(15);
	JTextField jtfNombre = new JTextField(40);

	Fabricante actual = null;

	/**
	 * 
	 */
	public PanelGestionFabricante() {
		actual = FabricanteControlador.getControlador().findFirst();
		construir();
		cargarDatosActual();
	}

	private void construir() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		// Inclusiï¿½n del panel de navegaciï¿½n
		d.fill = GridBagConstraints.HORIZONTAL;
		d.gridx = 0;
		d.gridy = 0;
		d.gridwidth = 2;
		d.insets = new Insets(0, 0, 25, 0);
		this.add(getPanelNavegacion(), d);

		// Inclusiï¿½n del campo "Identificador"
		d.fill = GridBagConstraints.NONE;
		d.gridx = 0;
		d.gridy = 1;
		d.gridwidth = 1;
		d.anchor = GridBagConstraints.EAST;
		d.insets = new Insets(2, 2, 2, 2);
		this.add(new JLabel("Identificador: "), d);

		d.gridx = 1;
		d.gridy = 1;
		jtfId.setEnabled(false);
		d.anchor = GridBagConstraints.WEST;
		this.add(jtfId, d);

		// Inclusiï¿½n del campo "CIF"
		d.gridx = 0;
		d.gridy = 2;
		d.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("CIF: "), d);

		d.gridx = 1;
		d.gridy = 2;
		d.anchor = GridBagConstraints.WEST;
		this.add(jtfCif, d);

		// Inclusiï¿½n del campo "Nombre"
		d.gridx = 0;
		d.gridy = 3;
		d.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Nombre: "), d);

		d.gridx = 1;
		d.gridy = 3;
		d.anchor = GridBagConstraints.WEST;
		this.add(jtfNombre, d);
		
		d.gridx = 0;
	    d.gridy = 5;
	    d.gridwidth = 2;
	    d.anchor = GridBagConstraints.CENTER;
	    d.insets = new Insets(25, 0, 0, 0);
		this.add(getPanelAcciones(), d);
	}

	/**
	 * 
	 * @return
	 */
	private JPanel getPanelNavegacion() {
		JPanel pnl = new JPanel();

		pnl.setBackground(Color.ORANGE);

		JButton jbtPrimero = new JButton("Primero");
		asignarFuncion(jbtPrimero, LOAD_FIRST);

		JButton jbtAnterior = new JButton("Anterior");
		asignarFuncion(jbtAnterior, LOAD_PREV);

		JButton jbtSiguiente = new JButton("Siguiente");
		asignarFuncion(jbtSiguiente, LOAD_NEXT);

		JButton jbtUltimo = new JButton("Ultimo");
		asignarFuncion(jbtUltimo, LOAD_LAST);

		pnl.setLayout(new FlowLayout());
		pnl.add(jbtPrimero);
		pnl.add(jbtAnterior);
		pnl.add(jbtSiguiente);
		pnl.add(jbtUltimo);

		return pnl;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel getPanelAcciones() {
		JPanel pnl = new JPanel();

		pnl.setBackground(Color.ORANGE);

		JButton jbtNuevo1 = new JButton("Nuevo");
		asignarFuncion(jbtNuevo1, NEW);

		JButton jbtGuardar1 = new JButton("Guardar");
		asignarFuncion(jbtGuardar1, SAVE);

		JButton jbtEliminar1 = new JButton("Eliminar");
		asignarFuncion(jbtEliminar1, REMOVE);

		pnl.setLayout(new FlowLayout());
		pnl.add(jbtNuevo1);
		pnl.add(jbtGuardar1);
		pnl.add(jbtEliminar1);

		return pnl;
	}

	/**
	 * 
	 */
	private void nuevo() {
		limpiarPantalla();
		JOptionPane.showMessageDialog(this, "Por favor, introduzca los datos del nuevo registro");
	}

	/**
	 * 
	 */
	private void limpiarPantalla() {
		this.jtfId.setText("");
		this.jtfCif.setText("");
		this.jtfNombre.setText("");

	}

	/**
	 * 
	 */
	private void guardar() {
		Fabricante nuevoRegistro = new Fabricante();

		if (this.jtfId.getText().trim().equals(""))
			//esto sirve para guardar nuevos registros, por eso es 0
			nuevoRegistro.setId(0);
		else
			//este sirve para guardar registros que ya existen (modificados)
			nuevoRegistro.setId(Integer.parseInt(this.jtfId.getText()));

		nuevoRegistro.setCif(this.jtfCif.getText());
		nuevoRegistro.setNombre(this.jtfNombre.getText());

		if (nuevoRegistro.getId() == 0) {
			//persist para nuevo identificador
			FabricanteControlador.getControlador().persist(nuevoRegistro);
		} else {
			//merge para cuando hay una modificación (update)
			FabricanteControlador.getControlador().merge(nuevoRegistro);
		}

		this.jtfId.setText("" + nuevoRegistro.getId());
		JOptionPane.showMessageDialog(this, "Guardado correctamente");

		this.actual = nuevoRegistro;
	}

	/**
	 * 
	 * @return
	 */
	private Fabricante eliminar() {
		String respuestas[] = new String[] { "Sí", "No" };
		int opcionElegida = JOptionPane.showOptionDialog(null, "¿Realmente desea eliminar el registro?",
				"Eliminación del registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION,
				CacheImagenes.getCacheImagenes().getIcono("confirm.png"), respuestas, respuestas[1]);

		if (opcionElegida == 0) {
			Fabricante nuevoAMostrar = FabricanteControlador.getControlador().findPrevious(actual);
			if (nuevoAMostrar == null) {
				nuevoAMostrar = FabricanteControlador.getControlador().findNext(actual);
			}
			FabricanteControlador.getControlador().remove(actual);
			JOptionPane.showMessageDialog(this, "Eliminación correcta");

			if (nuevoAMostrar != null) {
				actual = nuevoAMostrar;
			} else {
				limpiarPantalla();
			}
		}
		return actual;
	}

	/**
	 * 
	 * @param jbt
	 * @param funcion
	 */
	private void asignarFuncion(JButton jbt, final int funcion) {
		jbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Fabricante obtenido = null;
				if (funcion == LOAD_FIRST)
					obtenido = FabricanteControlador.getControlador().findFirst();
				if (funcion == LOAD_PREV)
					obtenido = FabricanteControlador.getControlador().findPrevious(actual);
				if (funcion == LOAD_NEXT)
					obtenido = FabricanteControlador.getControlador().findNext(actual);
				if (funcion == LOAD_LAST)
					obtenido = FabricanteControlador.getControlador().findLast();
				if (funcion == NEW)
					nuevo();
				if (funcion == SAVE)
					guardar();
				if (funcion == REMOVE)
					obtenido = eliminar();

				if (obtenido != null) {
					actual = obtenido;
					cargarDatosActual();
				}
			}
		});
	}

	/**
	 * 
	 */
	private void cargarDatosActual() {
		if (this.actual != null) {
			this.jtfId.setText("" + this.actual.getId());
			this.jtfCif.setText(this.actual.getCif());
			this.jtfNombre.setText(this.actual.getNombre());

		}
	}
}
