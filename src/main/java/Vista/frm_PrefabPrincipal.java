package Vista;

import Modelo.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static BdCalculator.Global.getModeloCbo;
import static Vista.frm_PrePrincipal.WindowChange;


public class frm_PrefabPrincipal {
    private JButton btnInforUser;
    public JPanel panelPrefab;
    private JButton createMateriaButton;
    private JComboBox cbMateria;
    private JLabel txtViewError;
    private JLabel EnObras;
    private Usuario usuarioCurrent;

    public frm_PrefabPrincipal(Usuario newUser,JTabbedPane VentanaPrincipal) {
        usuarioCurrent = newUser;
        cbMateria.setModel(getModeloCbo("select * from materia",2));
        btnInforUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowChange(
                        new frm_SignUp("ACTUALIZAR",usuarioCurrent).PanelSignUp,"LogIn");
            }
        });
        createMateriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VentanaPrincipal.addTab(cbMateria.getSelectedItem().toString(),new frm_PrefabCalculator(cbMateria.getSelectedItem().toString()).Prefab_Promedio);
            }
        });
    }


}
