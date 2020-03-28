package ventana;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Cliente;
import model.Coche;
import model.Fabricante;
import model.controladores.ClienteControlador;
import model.controladores.CocheControlador;
import model.controladores.FabricanteControlador;
import utils.CacheImagenes;

public class PanelGestionCoche extends JPanel {

	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;

	JTextField jtfId = new JTextField(5);
	JTextField jtfBastidor = new JTextField(15);
	JTextField jtfModelo = new JTextField(15);
	JTextField jtfColor = new JTextField(15);
	JComboBox<Fabricante> jcbIdFabricante = new JComboBox<Fabricante>();

	Coche actual = null;

	public PanelGestionCoche() {
		actual = CocheControlador.getControlador().findFirst();
		construir();
		cargarDatosActual();
	}

	/**
	 * 
	 */
	private void construir() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Inclusiï¿½n del panel de navegaciï¿½n
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 25, 0);
		this.add(getPanelNavegacion(), c);

		// Inclusiï¿½n del campo "Identificador"
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		this.add(new JLabel("Identificador: "), c);

		c.gridx = 1;
		c.gridy = 1;
		jtfId.setEnabled(false);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfId, c);

		// Inclusión del campo "idFabricante"
		List<Fabricante> listaFabricantes = FabricanteControlador.getControlador().findAll();

		for (Fabricante f : listaFabricantes) {
			jcbIdFabricante.addItem(f);
		}
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("IdFabricante: "), c);

		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		this.add(jcbIdFabricante, c);

		// Inclusiï¿½n del campo "CIF"
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Bastidor: "), c);

		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfBastidor, c);

		// Inclusiï¿½n del campo "Nombre"
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Modelo: "), c);

		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfModelo, c);

		// Inclusión del campo "Color"
		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Color: "), c);

		c.gridx = 1;
		c.gridy = 5;
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfColor, c);

		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(25, 0, 0, 0);
		this.add(getPanelAcciones(), c);
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

		pnl.setBackground(Color.orange);

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
		this.jcbIdFabricante.setSelectedIndex(0);
		this.jtfBastidor.setText("");
		this.jtfModelo.setText("");
		this.jtfColor.setText("");

	}

	/**
	 * @throws ParseException
	 * 
	 */
	private void guardar() throws ParseException {
		Coche nuevoRegistro = new Coche();

		if (this.jtfId.getText().trim().equals(""))
			// esto sirve para guardar nuevos registros, por eso es 0
			// esta condición sustituye al método exits
			nuevoRegistro.setId(0);
		else
			// este sirve para guardar registros que ya existen (modificados)
			nuevoRegistro.setId(Integer.parseInt(this.jtfId.getText()));

		nuevoRegistro.setFabricante((Fabricante) this.jcbIdFabricante.getSelectedItem());
		nuevoRegistro.setBastidor(this.jtfBastidor.getText());
		nuevoRegistro.setModelo(this.jtfModelo.getText());
		nuevoRegistro.setColor(this.jtfColor.getText());

		if (nuevoRegistro.getId() == 0) {
			// persist para nuevo identificador
			CocheControlador.getControlador().persist(nuevoRegistro);
		} else {
			// merge para cuando hay una modificación (update)
			CocheControlador.getControlador().merge(nuevoRegistro);
		}

		this.jtfId.setText("" + nuevoRegistro.getId());
		JOptionPane.showMessageDialog(this, "Guardado correctamente");

		this.actual = nuevoRegistro;
	}

	/**
	 * 
	 * @return
	 */
	private Coche eliminar() {
		String respuestas[] = new String[] { "Sí", "No" };
		int opcionElegida = JOptionPane.showOptionDialog(null, "¿Realmente desea eliminar el registro?",
				"Eliminación del registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION,
				CacheImagenes.getCacheImagenes().getIcono("confirm.png"), respuestas, respuestas[1]);

		if (opcionElegida == 0) {
			// El 0 es el SI
			// Antes de eliminarlo, vamos a adjudicar quién va a aparecer en su lugar. Por
			// defecto el anterior
			Coche nuevoAMostrar = CocheControlador.getControlador().findPrevious(actual);
			if (nuevoAMostrar == null) {
				nuevoAMostrar = CocheControlador.getControlador().findNext(actual);
				// Esto sería para el primero de la lista, ya que no existe un anterior. Y se
				// pondrá el siguiente
			}
			// El actual coincide siempre al 100% con lo que hay en el JPanel
			CocheControlador.getControlador().remove(actual);
			JOptionPane.showMessageDialog(this, "Eliminación correcta");

			// Si hay un nuevo a mostrar, que lo teníamos guardado de antes, el actual se
			// convierte en NuevoAmostrar
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

				Coche obtenido = null;
				if (funcion == LOAD_FIRST)
					obtenido = CocheControlador.getControlador().findFirst();
				if (funcion == LOAD_PREV)
					obtenido = CocheControlador.getControlador().findPrevious(actual);
				if (funcion == LOAD_NEXT)
					obtenido = CocheControlador.getControlador().findNext(actual);
				if (funcion == LOAD_LAST)
					obtenido = CocheControlador.getControlador().findLast();
				if (funcion == NEW)
					nuevo();
				if (funcion == SAVE)
					try {
						guardar();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if (funcion == REMOVE)
					obtenido = eliminar();

				if (obtenido != null) {
					actual = obtenido;
					cargarDatosActual();
				}
			}
		});
	}

	private void cargarDatosActual() {

		if (this.actual != null) {
			this.jtfId.setText("" + this.actual.getId());
			this.jcbIdFabricante.setSelectedItem(this.actual.getFabricante());
			this.jtfBastidor.setText(this.actual.getBastidor());
			this.jtfModelo.setText(this.actual.getModelo());
			this.jtfColor.setText(this.actual.getColor());

		}
	}

}
