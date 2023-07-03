package Vista;

import Modelo.Usuario;
import Controlador.AdminUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static Vista.frm_PrePrincipal.*;

public class frm_LogIn {
    public JPanel PanelLogIn;
    private JTextField txtUsernameLogIn;
    private JPasswordField txtPasswordLogIn;
    private JButton btnContinuar;
    private JButton signUpButton;
    private JLabel ImageLoadMain;
    private JLabel txtViewError;
    public AdminUsuario adminUsuario;
    public static JFrame frameManage;

    public frm_LogIn() {
        adminUsuario = new AdminUsuario();
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newJframe.dispose();
                WindowChange(new frm_SignUp().PanelSignUp,"SignUp");
            }
        });

        ImageLoadMain.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                EnableOrDisable(true,1);
            }
        });
        btnContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario user = adminUsuario.leerUsuario(txtUsernameLogIn.getText(),txtPasswordLogIn.getText());
                if (user!=null){
                    newJframe.dispose();
                    frame.dispose();
                    System.out.println("Login"+user.getUserName());
                    EstablecerPrincipal(user);

                }else{
                    txtViewError.setForeground(Color.red);
                    txtViewError.setText("Credenciales inválidas. Verifica usuario y contraseña.");
                }
            }
        });
    }
    public void EstablecerPrincipal(Usuario user){
        frameManage = new JFrame("frm_Principal");
        frameManage.setContentPane(new frm_Principal(user).panel1);
        frameManage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameManage.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameManage.setVisible(true);
    }
}
