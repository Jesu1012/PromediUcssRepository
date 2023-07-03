package BdCalculator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.sql.*;

import static Controlador.Conectar.getConexion;

public class Global {
    public static JTextField PrefabTextField(String textName, int sizeColumn){
        JTextField newTextFild = new JTextField(textName,sizeColumn);
        newTextFild.setBorder(new LineBorder(Color.white, 1));
        newTextFild.setForeground(Color.WHITE);
        newTextFild.setBackground(new Color(0x343541));
        Font boldFont = new Font(newTextFild.getFont().getName(), Font.BOLD, newTextFild.getFont().getSize());
        newTextFild.setFont(boldFont);
        newTextFild.setHorizontalAlignment(SwingConstants.CENTER);
        newTextFild.setBackground(new Color(0x343541));

        return newTextFild;
    }

    public static String TotalFormattedPrefab(double valueTotal,int valueCantidad){
        String totalFormatted = "";
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat decimalFormat = new DecimalFormat("#.00", symbols);
        if (valueCantidad!=0){
            totalFormatted = decimalFormat.format(valueTotal / valueCantidad);
        }else {
            totalFormatted = decimalFormat.format(valueTotal);
        }
        // Reemplazar cualquier coma por un punto en el resultado formateado
        totalFormatted = totalFormatted.replace(',', '.');

        return totalFormatted;
    }

    public static DefaultComboBoxModel getModeloCbo(String Sql,int col) {
        DefaultComboBoxModel Modelo = new DefaultComboBoxModel();
        Statement Acceso=null;
        try{
            Acceso=getConexion().createStatement();
            ResultSet Consulta=null;
            Consulta=Acceso.executeQuery(Sql);
            while(Consulta.next()){
                Modelo.addElement(Consulta.getString(col));
            }
        }
        catch(SQLException e) {System.out.println(""+e);}
        return Modelo;
    }

}
