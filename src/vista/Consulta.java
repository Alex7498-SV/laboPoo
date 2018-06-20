
package vista;

import dao.FiltroDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
//import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Filtro;


public class Consulta extends JFrame {

    public JLabel lblNombre, lblDirector, lblPais, lblClasificacion, lblAño, lblProy;

    public JTextField nombre, director, pais, año, proyeccion;
    public JComboBox clasificacion;

    ButtonGroup eProy = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;

    public JPanel table;

    public JButton buscar, eliminar, insertar, limpiar, actualizar;

    private static final int ANCHOC = 130, ALTOC = 30;

    DefaultTableModel tm;

    public Consulta() {
        super("inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblNombre);
        container.add(lblDirector);
        container.add(lblPais);
        container.add(lblClasificacion);
        container.add(lblAño);
        container.add(lblProy);
        container.add(nombre);
        container.add(clasificacion);
        container.add(pais);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(800, 800);
        eventos();

    }

    private void agregarLabels() {
        lblNombre = new JLabel("Nombre");
        lblDirector = new JLabel("Director");
        lblPais = new JLabel("Pais");
        lblClasificacion = new JLabel("Clasificacion");
        lblAño = new JLabel("Año");
        lblProy = new JLabel("En proyeccion");
        //---------------------------------------
        lblNombre.setBounds(10, 10, ANCHOC, ALTOC);
        lblDirector.setBounds(10, 60, ANCHOC, ALTOC);
        lblPais.setBounds(10, 100, ANCHOC, ALTOC);
        lblClasificacion.setBounds(10, 140, ANCHOC, ALTOC);
        lblAño.setBounds(10, 180, ANCHOC, ALTOC);
        lblProy.setBounds(10, 220, ANCHOC, ALTOC);

    }


    private void formulario() {
        nombre = new JTextField();
        clasificacion = new JComboBox();
        pais = new JTextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();

        clasificacion.addItem("G");
        clasificacion.addItem("PG");
        clasificacion.addItem("14A");
        clasificacion.addItem("18A");
        clasificacion.addItem("R");
        clasificacion.addItem("A");

        eProy = new ButtonGroup();
        eProy.add(si);
        eProy.add(no);
        //-------------------------------------------
        nombre.setBounds(140, 10, ANCHOC, ALTOC);
        director.setBounds(140, 60, ANCHOC, ALTOC);
        pais.setBounds(140, 100, ANCHOC, ALTOC);
        año.setBounds(140, 180, ANCHOC, ALTOC);
        proyeccion.setBounds(140, 220, ANCHOC, ALTOC);
        si.setBounds(140, 140, 50, ALTOC);
        no.setBounds(210, 140, 50, ALTOC);

        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        actualizar.setBounds(150, 210, ANCHOC, ALTOC);
        eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        limpiar.setBounds(450, 210, ANCHOC, ALTOC);

        resultados = new JTable();
        table.setBounds(10, 300, 600, 300);
        table.add(new JScrollPane(resultados));

    }

    private void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return int.class;
                    default:
                        return Boolean.class;
                }
            }
        };

        tm.addColumn("Nombre");
        tm.addColumn("Director");
        tm.addColumn("Pais");
        tm.addColumn("Clasificacion");
        tm.addColumn("Año");
        tm.addColumn("En proyeccion");

        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();

        for (Filtro fi : filtros) {
            tm.addRow(new Object[]{fi.getNombre(), fi.getDirector(), fi.getPais(), fi.getClasificacion(), fi.getAnnio(),fi.isEn_proyeccion()});
        }

        resultados.setModel(tm);

    }

    private void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(nombre.getText(), clasificacion.getSelectedItem().toString(), Integer.parseInt(pais.getText()), true);

                if (no.isSelected()) {
                    f.setExistencia(false);
                }

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro registrado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema con la creación de este filtro.");
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(nombre.getText(), clasificacion.getSelectedItem().toString(), Integer.parseInt(pais.getText()), true);

                if (no.isSelected()) {
                    f.setExistencia(false);
                }

                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro modificado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de creación de este filtro.");
                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(nombre.getText(), clasificacion.getSelectedItem().toString(), Integer.parseInt(pais.getText()), true);
                if (fd.delete(nombre.getText())) {
                    JOptionPane.showMessageDialog(null, "Filtro eliminado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar este filtro.");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = fd.read(nombre.getText());
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "El Filtro buscado no ha sido encontrado");
                } else {

                    nombre.setText(f.getCodigo());
                    clasificacion.setSelectedItem(f.getMarca());
                    pais.setText(Integer.toString(f.getStock()));

                    if (f.getExistencia()) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    public void limpiarCampos() {
        nombre.setText("");
        clasificacion.setSelectedItem("FRAM");
        pais.setText("");

    }

}
