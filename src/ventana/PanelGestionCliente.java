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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Cliente;
import model.Fabricante;
import model.controladores.ClienteControlador;
import model.controladores.FabricanteControlador;
import utils.CacheImagenes;

public class PanelGestionCliente extends JPanel {

	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;

	JTextField jtfId = new JTextField(5);
	JTextField jtfDni = new JTextField(9);
	JTextField jtfNombre = new JTextField(15);
	JTextField jtfApellido = new JTextField(40);
	JTextField jtfLocalidad = new JTextField(40);
	JTextField jtfFecha = new JTextField(40);
	JTextField jtfActivo = new JTextField(5);
	JCheckBox jchActivo = new JCheckBox();

	Cliente actual = null;

	/**
	 * 
	 */
	public PanelGestionCliente() {
		actual = ClienteControlador.getControlador().findFirst();
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
		this.add(new JLabel("DNI: "), d);

		d.gridx = 1;
		d.gridy = 2;
		d.anchor = GridBagConstraints.WEST;
		this.add(jtfDni, d);

		// Inclusiï¿½n del campo "Nombre"
		d.gridx = 0;
		d.gridy = 3;
		d.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Nombre: "), d);

		d.gridx = 1;
		d.gridy = 3;
		d.anchor = GridBagConstraints.WEST;
		this.add(jtfNombre, d);

		// Inclusiï¿½n del campo "Apellidos"
		d.gridx = 0;
		d.gridy = 4;
		d.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Apellido: "), d);

		d.gridx = 1;
		d.gridy = 4;
		d.anchor = GridBagConstraints.WEST;
		this.add(jtfApellido, d);

		// Inclusiï¿½n del campo "Localidad"
		d.gridx = 0;
		d.gridy = 5;
		d.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Localidad: "), d);

		d.gridx = 1;
		d.gridy = 5;
		d.anchor = GridBagConstraints.WEST;
		this.add(jtfLocalidad, d);

		// Inclusiï¿½n del campo "Fecha"
		d.gridx = 0;
		d.gridy = 6;
		d.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Fecha: "), d);

		d.gridx = 1;
		d.gridy = 6;
		d.anchor = GridBagConstraints.WEST;
		this.add(jtfFecha, d);

//		// Inclusiï¿½n del campo "Activo"
//		d.gridx = 0;
//		d.gridy = 7;
//		d.anchor = GridBagConstraints.EAST;
//		this.add(new JLabel("Activo: "), d);
//
//		d.gridx = 1;
//		d.gridy = 7;
//		d.anchor = GridBagConstraints.WEST;
//		this.add(jtfActivo, d);

		// Inclusiï¿½n del campo "Activo"
		d.gridx = 0;
		d.gridy = 7;
		d.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Activo: "), d);

		d.gridx = 1;
		d.gridy = 7;
		d.anchor = GridBagConstraints.WEST;
		this.add(jchActivo, d);

		d.gridx = 0;
		d.gridy = 8;
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
		this.jtfDni.setText("");
		this.jtfNombre.setText("");
		this.jtfApellido.setText("");
		this.jtfLocalidad.setText("");
		this.jtfFecha.setText("");
		// this.jtfActivo.setText("");
		this.jchActivo.isSelected();

	}

	/**
	 * @throws ParseException
	 * 
	 */
	private void guardar() throws ParseException {
		Cliente nuevoRegistro = new Cliente();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (this.jtfId.getText().trim().equals(""))
			// esto sirve para guardar nuevos registros, por eso es 0
			nuevoRegistro.setId(0);
		else
			// este sirve para guardar registros que ya existen (modificados)
			nuevoRegistro.setId(Integer.parseInt(this.jtfId.getText()));

		nuevoRegistro.setDniNie(this.jtfDni.getText());
		nuevoRegistro.setNombre(this.jtfNombre.getText());
		nuevoRegistro.setApellidos(this.jtfApellido.getText());
		nuevoRegistro.setLocalidad(this.jtfLocalidad.getText());
		nuevoRegistro.setFechaNac(sdf.parse(this.jtfFecha.getText()));
		// nuevoRegistro.setActivo(Boolean.parseBoolean(this.jtfActivo.getText()));
		nuevoRegistro.setActivo(this.jchActivo.isSelected());

		if (nuevoRegistro.getId() == 0) {
			// persist para nuevo identificador
			ClienteControlador.getControlador().persist(nuevoRegistro);
		} else {
			// merge para cuando hay una modificación (update)
			ClienteControlador.getControlador().merge(nuevoRegistro);
		}

		this.jtfId.setText("" + nuevoRegistro.getId());
		JOptionPane.showMessageDialog(this, "Guardado correctamente");

		this.actual = nuevoRegistro;
	}

	/**
	 * 
	 * @return
	 */
	private Cliente eliminar() {
		String respuestas[] = new String[] { "Sí", "No" };
		int opcionElegida = JOptionPane.showOptionDialog(null, "¿Realmente desea eliminar el registro?",
				"Eliminación del registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION,
				CacheImagenes.getCacheImagenes().getIcono("confirm.png"), respuestas, respuestas[1]);

		if (opcionElegida == 0) {
			Cliente nuevoAMostrar = ClienteControlador.getControlador().findPrevious(actual);
			if (nuevoAMostrar == null) {
				nuevoAMostrar = ClienteControlador.getControlador().findNext(actual);
			}
			ClienteControlador.getControlador().remove(actual);
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

				Cliente obtenido = null;
				if (funcion == LOAD_FIRST)
					obtenido = ClienteControlador.getControlador().findFirst();
				if (funcion == LOAD_PREV)
					obtenido = ClienteControlador.getControlador().findPrevious(actual);
				if (funcion == LOAD_NEXT)
					obtenido = ClienteControlador.getControlador().findNext(actual);
				if (funcion == LOAD_LAST)
					obtenido = ClienteControlador.getControlador().findLast();
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

	/**
	 * 
	 */
	private void cargarDatosActual() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (this.actual != null) {
			this.jtfId.setText("" + this.actual.getId());
			this.jtfDni.setText(this.actual.getDniNie());
			this.jtfNombre.setText(this.actual.getNombre());
			this.jtfApellido.setText(this.actual.getApellidos());
			this.jtfLocalidad.setText(this.actual.getLocalidad());
			this.jtfFecha.setText(sdf.format(this.actual.getFechaNac()));
			// this.jtfActivo.setText(Boolean.toString(this.actual.getActivo()));
			this.jchActivo.setContentAreaFilled(isValid());

		}
	}

}
