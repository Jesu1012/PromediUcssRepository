package Vista;

import javax.swing.*;

import Modelo.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Vista.frm_LogIn.frameManage;
import static Vista.frm_PrePrincipal.returnPrePrincipal;

public class frm_Principal {
    public JTabbedPane VentanaPrincipal;
    public JPanel panel1;
    private JButton cerrarSesionButton;

    private Usuario usuarioCurrent;

    public frm_Principal(Usuario newUser) {
        usuarioCurrent = newUser;
        VentanaPrincipal.addTab("Manejo de Espacio",new frm_PrefabPrincipal(usuarioCurrent,VentanaPrincipal).panelPrefab);
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameManage.dispose();
                returnPrePrincipal();
            }
        });
    }

    public int CreateNewMateria(){
        int R=0;
        //VentanaPrincipal.addTab("Prefab",new frm_PrefabCalculator("Lengua").Prefab_Promedio);
        return R;
    }
}
