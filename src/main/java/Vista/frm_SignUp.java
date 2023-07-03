package Vista;

import Controlador.AdminUsuario;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static BdCalculator.Global.getModeloCbo;
import static Vista.frm_PrePrincipal.*;

public class frm_SignUp extends Container {
    private JTextField entUsername;
    private JTextField entAge;
    private JPasswordField entPassword;
    private JComboBox cbCampus;
    public JPanel PanelSignUp;

    private JButton btnClose;
    private JButton btnRegistrar;
    private JButton logInButtonEnlace;
    private JLabel ImageLoadMain;
    private JLabel txtYatienes;
    private JPasswordField entConfirmPassword;
    private JLabel txtVerificar;
    AdminUsuario adminUsuario;

    public frm_SignUp(){
        adminUsuario = new AdminUsuario();
        cbCampus.setModel(getModeloCbo("Select * from campus",2));
        logInButtonEnlace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                newJframe.dispose();
                WindowChange(new frm_LogIn().PanelLogIn,"LogIn");

            }
        });

        ImageLoadMain.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                EnableOrDisable(true,1);
            }
        });
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Verificar(0)){
                    Usuario user = new Usuario();
                    user.setUserName(entUsername.getText());
                    user.setAgeUser(Integer.parseInt(entAge.getText()));
                    user.setPasswords(entPassword.getText());
                    user.setIdCampus(cbCampus.getSelectedIndex() + 1);
                    int R = adminUsuario.Insertar(user);
                    txtVerificar.setText("Registrado <N "+R+">. :)");
                }
            }
        });
    }
    public frm_SignUp(String Name,Usuario usuario){
        adminUsuario = new AdminUsuario();

        int IdUser = usuario.getIdUser();
        String UserName = usuario.getUserName();
        int Age = usuario.getAgeUser();
        String Password = usuario.getPasswords();
        int IdCampus = usuario.getIdCampus();
        //
        cbCampus.setModel(getModeloCbo("Select * from campus",2));
        btnRegistrar.setText(Name);

        entUsername.setText(UserName);
        entAge.setText(Age+"");
        entPassword.setText(Password);
        cbCampus.setSelectedIndex(IdCampus-1);
        PanelSignUp.remove(21);
        PanelSignUp.remove(21);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Verificar(1)){
                    Usuario user = new Usuario();
                    user.setIdUser(IdUser);
                    user.setUserName(entUsername.getText());
                    user.setAgeUser(Integer.parseInt(entAge.getText()));
                    user.setPasswords(entPassword.getText());
                    user.setIdCampus(cbCampus.getSelectedIndex() + 1);
                    int R = adminUsuario.actualizarUsuario(user);
                    txtVerificar.setText("Actualizado <N "+R+">. :)");
                }
            }
        });


        ImageLoadMain.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                newJframe.dispose();
            }
        });

    }
    public boolean Verificar(int G){
        boolean valueVerificar = false;
        String username = entUsername.getText().toString();
        String ageText = entAge.getText().toString();
        String password = entPassword.getText().toString();
        String confirmPassword = entConfirmPassword.getText().toString();

        if (username.isEmpty() || ageText.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            txtVerificar.setText("Por favor, completa todos los campos.");
            txtVerificar.setForeground(Color.red);
            return valueVerificar;
        }

        try {
            int age = Integer.parseInt(ageText);
            if (age < 0 || age > 120) {
                txtVerificar.setText("La edad debe estar en un rango válido.");
                txtVerificar.setForeground(Color.red);
                return valueVerificar;
            }
        } catch (NumberFormatException ex) {
            txtVerificar.setText("La edad debe ser un número válido.");
            txtVerificar.setForeground(Color.red);
            return valueVerificar;
        }

        if (!password.equals(confirmPassword)) {
            txtVerificar.setText("Las contraseñas no coinciden.");
            txtVerificar.setForeground(Color.red);
            return valueVerificar;
        }

        boolean userVerify = adminUsuario.verificarUsuarioExistente(username);
        if (userVerify && G==0) {
            txtVerificar.setText("Ya existe el usuario.");
            txtVerificar.setForeground(Color.red);
        } else {
            txtVerificar.setForeground(Color.green);
            valueVerificar = true;
        }
        return valueVerificar;
    }


}
