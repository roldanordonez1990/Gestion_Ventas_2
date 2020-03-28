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
import model.Concesionario;
import model.Fabricante;
import model.Venta;
import model.controladores.ClienteControlador;
import model.controladores.CocheControlador;
import model.controladores.ConcesionarioControlador;
import model.controladores.FabricanteControlador;
import model.controladores.VentaControlador;
import utils.CacheImagenes;

public class PanelGestionVenta extends JPanel {

	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;

	JTextField jtfId = new JTextField(5);
	JTextField jtfFecha = new JTextField(40);
	JTextField jtfPrecioVenta = new JTextField(10);
	JComboBox<Cliente> jcbIdCliente = new JComboBox<Cliente>();
	JComboBox<Coche> jcbIdCoche = new JComboBox<Coche>();
	JComboBox<Concesionario> jcbIdConcesionario = new JComboBox<Concesionario>();

	Venta actual = null;

	/**
	 * 
	 */

	public PanelGestionVenta() {
		actual = VentaControlador.getControlador().findFirst();
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

		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Fecha: "), c);

		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfFecha, c);

		// Inclusiï¿½n del campo "CIF"
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Precio de Venta: "), c);

		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfPrecioVenta, c);

		// Inclusión del campo "idCliente"
		List<Cliente> listaCliente = ClienteControlador.getControlador().findAll();

		for (Cliente cli : listaCliente) {
			jcbIdCliente.addItem(cli);
		}
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("IdCLiente: "), c);

		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		this.add(jcbIdCliente, c);

		// Inclusión del campo "idCoche"
		List<Coche> listaCoche = CocheControlador.getControlador().findAll();

		for (Coche co : listaCoche) {
			jcbIdCoche.addItem(co);
		}
		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("IdCoche: "), c);

		c.gridx = 1;
		c.gridy = 5;
		c.anchor = GridBagConstraints.WEST;
		this.add(jcbIdCoche, c);

		// Inclusión del campo "idConcesionario"
		List<Concesionario> listaConcesionario = ConcesionarioControlador.getControlador().findAll();

		for (Concesionario conce : listaConcesionario) {
			jcbIdConcesionario.addItem(conce);
		}
		c.gridx = 0;
		c.gridy = 6;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("IdConcesionario: "), c);

		c.gridx = 1;
		c.gridy = 6;
		c.anchor = GridBagConstraints.WEST;
		this.add(jcbIdConcesionario, c);

		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(25, 0, 0, 0);
		this.add(getPanelAcciones(), c);
	}

	/**
	 * 
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

	private void limpiarPantalla() {
		this.jtfId.setText("");
		this.jtfFecha.setText("");
		this.jtfPrecioVenta.setText("");
		this.jcbIdCliente.setSelectedIndex(0);
		this.jcbIdCoche.setSelectedIndex(0);
		this.jcbIdConcesionario.setSelectedIndex(0);
		

	}
	
	/**
	 * 
	 */
	
	private void guardar() throws ParseException {
		Venta nuevoRegistro = new Venta();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (this.jtfId.getText().trim().equals(""))
			// esto sirve para guardar nuevos registros, por eso es 0
			nuevoRegistro.setId(0);
		else
			// este sirve para guardar registros que ya existen (modificados)
			nuevoRegistro.setId(Integer.parseInt(this.jtfId.getText()));

	
		nuevoRegistro.setFecha(sdf.parse(this.jtfFecha.getText()));
		nuevoRegistro.setPrecioVenta(Float.parseFloat(this.jtfPrecioVenta.getText()));
		nuevoRegistro.setCliente((Cliente) this.jcbIdCliente.getSelectedItem());
		nuevoRegistro.setCoche((Coche) this.jcbIdCoche.getSelectedItem());
		nuevoRegistro.setConcesionario((Concesionario) this.jcbIdConcesionario.getSelectedItem());
		

		if (nuevoRegistro.getId() == 0) {
			// persist para nuevo identificador
			VentaControlador.getControlador().persist(nuevoRegistro);
		} else {
			// merge para cuando hay una modificación (update)
			VentaControlador.getControlador().merge(nuevoRegistro);
		}

		this.jtfId.setText("" + nuevoRegistro.getId());
		JOptionPane.showMessageDialog(this, "Guardado correctamente");

		this.actual = nuevoRegistro;
	}
	
	/**
	 * 
	 */
	
	private Venta eliminar() {
		String respuestas[] = new String[] { "Sí", "No" };
		int opcionElegida = JOptionPane.showOptionDialog(null, "¿Realmente desea eliminar el registro?",
				"Eliminación del registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION,
				CacheImagenes.getCacheImagenes().getIcono("confirm.png"), respuestas, respuestas[1]);

		if (opcionElegida == 0) {
			// El 0 es el SI
			// Antes de eliminarlo, vamos a adjudicar quién va a aparecer en su lugar. Por
			// defecto el anterior
			Venta nuevoAMostrar = VentaControlador.getControlador().findPrevious(actual);
			if (nuevoAMostrar == null) {
				nuevoAMostrar = VentaControlador.getControlador().findNext(actual);
				// Esto sería para el primero de la lista, ya que no existe un anterior. Y se
				// pondrá el siguiente
			}
			// El actual coincide siempre al 100% con lo que hay en el JPanel
			VentaControlador.getControlador().remove(actual);
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
	 */
	
	private void asignarFuncion(JButton jbt, final int funcion) {
		jbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Venta obtenido = null;
				if (funcion == LOAD_FIRST)
					obtenido = VentaControlador.getControlador().findFirst();
				if (funcion == LOAD_PREV)
					obtenido = VentaControlador.getControlador().findPrevious(actual);
				if (funcion == LOAD_NEXT)
					obtenido = VentaControlador.getControlador().findNext(actual);
				if (funcion == LOAD_LAST)
					obtenido = VentaControlador.getControlador().findLast();
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
			this.jtfFecha.setText(sdf.format(this.actual.getFecha()));
			this.jtfPrecioVenta.setText(Float.toString(this.actual.getPrecioVenta()));
			this.jcbIdCliente.setSelectedItem(this.actual.getCliente());
			this.jcbIdCoche.setSelectedItem(this.actual.getCoche());
			this.jcbIdConcesionario.setSelectedItem(this.actual.getConcesionario());
			

		}
	}

}
