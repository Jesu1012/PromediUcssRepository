package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static BdCalculator.Global.PrefabTextField;
import static BdCalculator.Global.TotalFormattedPrefab;

public class frm_PrefabCalculator {
    private JPanel PanelCalculator;
    private JTextField ent_EC;
    private JTextField entE1;
    private JTextField entE2;
    private JTextField entE3;
    private JTextField entEF;
    private JTextField ent_PF_NonEditable;
    private JRadioButton ECRadioButton;

    private JPanel JPanelEc;
    private JComboBox cbEc;
    private JButton btnCalculateECCollects;
    public JPanel Prefab_Promedio;
    private JLabel txtNameMateria;
    private List<JTextField> listEcTextField = new ArrayList<>();
    private List<JTextField> listEntMain = new ArrayList<>();

    public frm_PrefabCalculator() {
        StartOfProcess();

    }
    public frm_PrefabCalculator(String nameMateria){
        txtNameMateria.setText(nameMateria);
        StartOfProcess();
    }

    public static boolean esNumero(String texto) {
        try {
            // Intenta convertir el texto en un número double
            Integer.parseInt(texto);
            return true; // El texto es un número válido
        } catch (NumberFormatException e) {
            return false; // El texto no es un número válido
        }
    }

    public void CreateInJPanelECAutomatic(int count){

        JPanelEc.removeAll();

        for (int i = 0; i <= count; i++) {
            JTextField newTextFild = new JTextField("EC" + (i + 1),5);

            newTextFild.setForeground(Color.BLUE);
            JPanelEc.add(newTextFild);
            listEcTextField.add(newTextFild);
        }
        JPanelEc.setLayout(new FlowLayout(FlowLayout.CENTER));
        // Actualizar la interfaz gráfica
        JPanelEc.revalidate();
        JPanelEc.repaint();
    }

    public void SettingsValues(boolean value){
        JPanelEc.removeAll();
        listEcTextField.clear();
        btnCalculateECCollects.setVisible(value);
    }
    //0
    public void StartOfProcess(){
        listEntMain.add(ent_EC);
        listEntMain.add(entE1);
        listEntMain.add(entE2);
        listEntMain.add(entE3);
        listEntMain.add(entEF);
        ECRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ECRadioButton_();
            }
        });
        cbEc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsValues(false);
                ECRadioButton.setSelected(false);
            }
        });
        //
        FocusAdapter listener = new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                FOCUSAdapter();
            }
        };
        ent_EC.addFocusListener(listener);
        entE1.addFocusListener(listener);
        entE2.addFocusListener(listener);
        entE3.addFocusListener(listener);
        entEF.addFocusListener(listener);
        //
        btnCalculateECCollects.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BtnCalculateECCollects();
            }
        });

    }
    //1
    public void ECRadioButton_(){
        if (ECRadioButton.isSelected()) {

            SettingsValues(true);
            int amount = cbEc.getSelectedIndex() + 1;
            for (int i = 0; i < amount; i++) {
                JTextField newTextField = PrefabTextField("EC" + (i + 1),5);
                JPanelEc.add(newTextField);
                listEcTextField.add(newTextField);
            }
            JPanelEc.setLayout(new FlowLayout(FlowLayout.CENTER));
            JPanelEc.revalidate();
            JPanelEc.repaint();
        }
        else{
            SettingsValues(false);
        }
    }
    //2
    public void FOCUSAdapter(){
        double total = 0;
        for (int i = 0; i < listEntMain.size(); i++) {
            JTextField ent = listEntMain.get(i);
            if (!ent.getText().isEmpty()) {
                double valor = Double.parseDouble(ent.getText());
                if (valor > 20) {
                    ent.setText(""); // Borrar el valor si sobrepasa los veinte
                    return; // Retornar sin calcular el total
                }
                if (i == 0 || i == 2 || i == 3) {
                    total += valor * 0.2; // Multiplicar por el 20%
                } else if (i == 1) {
                    total += valor * 0.1; // Multiplicar por el 10%
                } else if (i == 4) {
                    total += valor * 0.3; // Multiplicar por el 30%
                }
            }
        }
        ent_PF_NonEditable.setText(TotalFormattedPrefab(total,0));
    }
    //3
    public void BtnCalculateECCollects(){
        double total = 0;
        int cantidad = cbEc.getSelectedIndex() + 1;
        for (JTextField ent : listEcTextField) {
            if (!ent.getText().isEmpty()) {
                String texto = ent.getText();
                try {
                    double valor = Double.parseDouble(texto);
                    if (valor > 20) {
                        ent.setText(""); // Borrar el valor si sobrepasa los veinte
                        return; // Retornar sin calcular el total
                    }
                    total += valor;
                } catch (NumberFormatException ex) {
                    ent.setText(""); // Borrar el valor si no es un número válido
                    return; // Retornar sin calcular el total
                }
            }
        }


        ent_EC.setText(TotalFormattedPrefab(total,cantidad));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("frm_Principal");
        frame.setContentPane(new frm_PrefabCalculator().PanelCalculator);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }



}
