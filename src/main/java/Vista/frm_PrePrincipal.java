package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
public class frm_PrePrincipal {

    private JPanel panelMain;
    private JPanel StartJpanel;
    private JButton logInButton;
    private JButton signUpButton;
    private JLabel ImageWelcome;
    public static JFrame frame;

    public static JFrame newJframe;

    public frm_PrePrincipal() {
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnableOrDisable(false);
                WindowChange(new frm_SignUp().PanelSignUp,"SignUp");

            }
        });
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnableOrDisable(false);

                WindowChange(new frm_LogIn().PanelLogIn,"LogIn");
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("frm_Principal");
        frame.setContentPane(new frm_PrePrincipal().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
    public static void WindowChange(JPanel newJPanel,String nameJFrame){
        newJframe = new JFrame(nameJFrame);
        newJframe.setUndecorated(true);
        newJframe.setContentPane(newJPanel);
        newJframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        newJframe.setSize(600,400);
        newJframe.setLocationRelativeTo(null);
        int thickness = 10;
        Color borderColor = new Color(42, 43, 55);
        newJframe.getRootPane().setBorder(new LineBorder(borderColor, thickness));
        newJframe.setVisible(true);
    }
    public static void EnableOrDisable(boolean value){
        frame.setEnabled(value);
    }public static void EnableOrDisable(boolean value,int vaI){
        newJframe.dispose();
        frame.setEnabled(value);
    }
    public static void returnPrePrincipal(){
        frame = new JFrame("frm_Principal");
        frame.setContentPane(new frm_PrePrincipal().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
