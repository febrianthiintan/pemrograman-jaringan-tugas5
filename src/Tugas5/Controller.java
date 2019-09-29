/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tugas5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberInputStream;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author INTAN
 */
public class Controller {
    private Main view;
    private List<Integer> list = new ArrayList<>();
    
    public Controller(Main view) {
        this.view = this.view;
        
        this.view.getBtBaca5().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proses();
            }
        });
        
        this.view.getBtSimpan5().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpan();
            }
        });
    }
        
        private void proses() {
        JFileChooser loadFile = view.getLoadFile5();
        StyledDocument doc = view.getTxtPane5().getStyledDocument();
        
        if (JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)) {
           BufferedReader reader = null;
            try {
                
                //membuat variabel 
                int decimal;
                char ascii;
                String line = null;
                
                //membuat variabel untuk menghitung kata dan karakter
                int wordCount = 0; //default 0
                int charCount = 0; //default 0
                
                //inisialisasi class LineNumberReader dan LineNumberInputStrem
                //untuk menghitung baris, jumlah kata dan jumlah karakter
                LineNumberReader numberReader = new LineNumberReader(new FileReader(loadFile.getSelectedFile()));
                LineNumberInputStream inputstream = new LineNumberInputStream(new FileInputStream(loadFile.getSelectedFile()));
                
                reader = new BufferedReader(new FileReader(loadFile.getSelectedFile()));
                
                String data = null;
                doc.insertString(0, "", null);
                while ((data = reader.readLine()) != null) {
                   doc.insertString(doc.getLength(), data, null);
                   doc.insertString(doc.getLength(), "\n", null);
                }
                
                //menghitung jumlah baris
                while ((decimal = inputstream.read()) != -1) {
                    ascii = (char) decimal;
                }
                
                //menghitung jumlah karakter dan jumlah kata
                while ((line = numberReader.readLine()) != null) {
                    String[] wordList = line.split("\\s");
                    wordCount += wordList.length;
                    charCount += line.length();
                }
                
                //menampilkan pop-up jumlah baris, kata dan karakter
                JOptionPane.showMessageDialog(view, "File Berhasil Dibaca" + 
                        "\nJumlah Baris     : " + (inputstream.getLineNumber() + 1) +
                        "\nJumlah Kata      : " + (wordCount) +
                        "\nJumlah Karakter   : " + (charCount), "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
            
            } catch (FileNotFoundException ex) {
               Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
           } catch (IOException | BadLocationException ex) {
               Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
           } finally {
               if (reader != null) {
                   try {
                       reader.close();
                   } catch (IOException ex) {
                       Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
        }
    }
    
    private void simpan() {
        JFileChooser loadFile = view.getLoadFile5();
        
        if (JFileChooser.APPROVE_OPTION == loadFile.showSaveDialog(view)) {
            BufferedWriter writer = null;
            
            try {
                String contents = view.getTxtPane5().getText();
                if (contents != null && !contents.isEmpty()) {
                    writer = new BufferedWriter(new FileWriter(loadFile.getSelectedFile()));
                    writer.write(contents);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (writer != null) {
                    try {
                        writer.flush();
                        writer.close();
                        view.getTxtPane5().setText("");
                        
                        //menambahkan pop-up untuk menampilkan data tersimpan
                        JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan !!", "PESAN INFORMASI", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                } else {
                    //pop-up input-an tidak boleh kosong
                    JOptionPane.showMessageDialog(null, "Teks Tidak Boleh Kosong", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    } 
}
