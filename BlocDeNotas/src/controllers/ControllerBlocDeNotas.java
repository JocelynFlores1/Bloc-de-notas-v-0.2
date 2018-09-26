
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import models.ModelBlocDeNotas;
import views.ViewBlocDeNotas;
/**
 *
 * @author Jocelyn
 */
public class ControllerBlocDeNotas {
    
    ModelBlocDeNotas modelBlocDeNotas;
    ViewBlocDeNotas viewBlocDeNotas;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewBlocDeNotas.jmi_leer) {
                LeerArchivo();
            } else if (e.getSource() == viewBlocDeNotas.jmi_guardar) {
                escribirArchivo();
            }
        }
    };
       public ControllerBlocDeNotas(ModelBlocDeNotas modelBlocDeNotas, ViewBlocDeNotas viewBlocDeNotas) {
        this.modelBlocDeNotas = modelBlocDeNotas;
        this.viewBlocDeNotas = viewBlocDeNotas;
        InitComponents();
        this.viewBlocDeNotas.jmi_leer.addActionListener(actionListener);
        this.viewBlocDeNotas.jmi_guardar.addActionListener(actionListener);

    }

    
    public void LeerArchivo() {
       try {
            String row;
            StringBuilder contenido = new StringBuilder();
            try (FileReader file = new FileReader(modelBlocDeNotas.getPath())) {
                BufferedReader bufferedReader = new BufferedReader(file);
                int i = 0;
                while ((row = bufferedReader.readLine()) != null) {
                    contenido.append(row);
                    contenido.append("\n");
                    
                }
                viewBlocDeNotas.jta_bloc.setText(String.valueOf(contenido));
                bufferedReader.close();
            } catch (FileNotFoundException ex) {
                System.err.println("File Not Found!! " + ex.getMessage());
            }
        } catch (IOException ex) {
            System.err.println("Error I/O Operation " + ex.getMessage());
        }
    }
   
    public void escribirArchivo() {
        modelBlocDeNotas.setText(viewBlocDeNotas.jta_bloc.getText()); 
        try {
            File file = new File(modelBlocDeNotas.getPath()); 
            FileWriter fileWriter = new FileWriter(file, false);
            try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
                printWriter.println(modelBlocDeNotas.getText()); 

                printWriter.close();
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File Not Found!! " + ex.getMessage());

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error I/O Operation " + ex.getMessage());

        }
    }
    public void AbrirArchivo(){
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File archivo=chooser.getSelectedFile();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea = reader.readLine();
            while (linea != null) {
                jta_bloc.append(linea + "\n");
                linea = reader.readLine();
            }
        } catch (Exception ex) {
        }
    }
    public void InitComponents() {
        viewBlocDeNotas.setVisible(true);
        viewBlocDeNotas.setLocationRelativeTo(null);

    }
}
