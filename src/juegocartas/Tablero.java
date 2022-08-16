/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegocartas;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author benavides
 */
public class Tablero extends JFrame {

    int intentos = 0;
    int puntaje = 0;
    Integer opcionesSeleccionadas = 0;
    Integer cartaAbierta = 0;
    Map<Integer, Integer> cartasMap;

    /**
     * Creates new form Tablero1
     */
    public Tablero() {
        initComponents();
        reiniciar();
    }

    public void barajarCartas() {
        cartasMap = new HashMap<>();
        Random random = new Random();
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(1);
        numeros.add(2);
        numeros.add(3);
        numeros.add(1);
        numeros.add(2);
        numeros.add(3);
        for (int i = 1; i < 7; i++) {
            int index = random.nextInt(numeros.size());
            cartasMap.put(i, numeros.get(index));
            numeros.remove(index);
        }
    }

    public final void scaleImage() {
        img1.setIcon(backImageIcon());
        img2.setIcon(backImageIcon());
        img3.setIcon(backImageIcon());
        img4.setIcon(backImageIcon());
        img5.setIcon(backImageIcon());
        img6.setIcon(backImageIcon());
    }

    public final ImageIcon backImageIcon() {
        ImageIcon backIcon = new ImageIcon(getClass().getResource("resources/card-back.jpg"));
        return new ImageIcon(backIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH));
    }

    public final ImageIcon frontImageIcon(int index) {
        ImageIcon backIcon = new ImageIcon(getClass().getResource("resources/A" + index + ".jpg"));
        return new ImageIcon(backIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH));
    }

    public final void ocultarCarta(int card) {
        switch (card) {
            case 1:
                img1.setEnabled(true);
                img1.setIcon(backImageIcon());
                break;
            case 2:
                img2.setEnabled(true);
                img2.setIcon(backImageIcon());
                break;
            case 3:
                img3.setEnabled(true);
                img3.setIcon(backImageIcon());
                break;
            case 4:
                img4.setEnabled(true);
                img4.setIcon(backImageIcon());
                break;
            case 5:
                img5.setEnabled(true);
                img5.setIcon(backImageIcon());
                break;
            case 6:
                img6.setEnabled(true);
                img6.setIcon(backImageIcon());
                break;
        }
    }

    public final void bloquearImagenes() {
        img1.setEnabled(false);
        img2.setEnabled(false);
        img3.setEnabled(false);
        img4.setEnabled(false);
        img5.setEnabled(false);
        img6.setEnabled(false);
    }

    public final void desbloquearImagenes() {
        img1.setEnabled(true);
        img2.setEnabled(true);
        img3.setEnabled(true);
        img4.setEnabled(true);
        img5.setEnabled(true);
        img6.setEnabled(true);
    }

    public void gameOver() {
        bloquearImagenes();
    }

    public final void reiniciar() {
        scaleImage();
        intentos = 0;
        puntaje = 0;
        opcionesSeleccionadas = 0;
        cartaAbierta = 0;
        barajarCartas();
        desbloquearImagenes();
        puntajeTxt.setText(String.valueOf(puntaje));
        intentosTxt.setText(String.valueOf(intentos));
        this.setVisible(true);
        cartasMap.keySet().forEach(clave -> {
            System.out.println("Clave: " + clave + ", valor: " + cartasMap.get(clave));
        });
    }

    public final void calculaPar(int index) {
        if (opcionesSeleccionadas == 1) {
            cartaAbierta = index;
        } else if (opcionesSeleccionadas == 2) {
            if (cartasMap.get(cartaAbierta) == cartasMap.get(index)) {
                setPuntaje();
            } else {
                this.setEnabled(false);
                Timer timer = new Timer(1000, e -> {
                    setIntentos(index, cartaAbierta);
                    this.setEnabled(true);
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
    }

    public final void setPuntaje() {
        puntaje++;
        puntajeTxt.setText(String.valueOf(puntaje));
        opcionesSeleccionadas = 0;
        if (puntaje == 3) {
            int result = JOptionPane.showOptionDialog(null, "GANADOR, Desea reiniciar el juego", "GANADOR", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            if (result == JOptionPane.OK_OPTION) {
                reiniciar();
            }
        }
    }

    public final void setIntentos(int carta1, int carta2) {
        intentos++;
        intentosTxt.setText(String.valueOf(intentos));
        ocultarCarta(carta1);
        ocultarCarta(carta2);
        opcionesSeleccionadas = 0;
        if (intentos > 2) {
            JOptionPane.showMessageDialog(null, "Ha consumido el número máximo de intentos, se va a reiniciar el juego", "Game Over", JOptionPane.ERROR_MESSAGE);
            reiniciar();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalir = new javax.swing.JButton();
        btnReiniciar = new javax.swing.JButton();
        puntajeLbl = new javax.swing.JLabel();
        puntajeTxt = new javax.swing.JTextField();
        intentosLbl = new javax.swing.JLabel();
        intentosTxt = new javax.swing.JTextField();
        img1 = new javax.swing.JToggleButton();
        img3 = new javax.swing.JToggleButton();
        img2 = new javax.swing.JToggleButton();
        img4 = new javax.swing.JToggleButton();
        img5 = new javax.swing.JToggleButton();
        img6 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Juego de pares");

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnReiniciar.setText("Reiniciar");
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });

        puntajeLbl.setText("Puntaje");

        puntajeTxt.setText("0");
        puntajeTxt.setFocusable(false);
        puntajeTxt.setMaximumSize(new java.awt.Dimension(45, 25));
        puntajeTxt.setMinimumSize(new java.awt.Dimension(45, 25));
        puntajeTxt.setPreferredSize(new java.awt.Dimension(45, 25));

        intentosLbl.setText("Intentos");

        intentosTxt.setText("0");
        intentosTxt.setFocusable(false);
        intentosTxt.setMaximumSize(new java.awt.Dimension(45, 25));
        intentosTxt.setMinimumSize(new java.awt.Dimension(45, 25));
        intentosTxt.setPreferredSize(new java.awt.Dimension(45, 25));

        img1.setText("Img1");
        img1.setMaximumSize(new java.awt.Dimension(100, 150));
        img1.setMinimumSize(new java.awt.Dimension(100, 150));
        img1.setPreferredSize(new java.awt.Dimension(100, 150));
        img1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                img1ActionPerformed(evt);
            }
        });

        img3.setText("Img3");
        img3.setMaximumSize(new java.awt.Dimension(100, 150));
        img3.setMinimumSize(new java.awt.Dimension(100, 150));
        img3.setPreferredSize(new java.awt.Dimension(100, 150));
        img3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                img3ActionPerformed(evt);
            }
        });

        img2.setText("Img2");
        img2.setMaximumSize(new java.awt.Dimension(100, 150));
        img2.setMinimumSize(new java.awt.Dimension(100, 150));
        img2.setPreferredSize(new java.awt.Dimension(100, 150));
        img2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                img2ActionPerformed(evt);
            }
        });

        img4.setText("Img4");
        img4.setMaximumSize(new java.awt.Dimension(100, 150));
        img4.setMinimumSize(new java.awt.Dimension(100, 150));
        img4.setPreferredSize(new java.awt.Dimension(100, 150));
        img4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                img4ActionPerformed(evt);
            }
        });

        img5.setText("Img5");
        img5.setMaximumSize(new java.awt.Dimension(100, 150));
        img5.setMinimumSize(new java.awt.Dimension(100, 150));
        img5.setPreferredSize(new java.awt.Dimension(100, 150));
        img5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                img5ActionPerformed(evt);
            }
        });

        img6.setText("Img6");
        img6.setMaximumSize(new java.awt.Dimension(100, 150));
        img6.setMinimumSize(new java.awt.Dimension(100, 150));
        img6.setPreferredSize(new java.awt.Dimension(100, 150));
        img6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                img6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(puntajeLbl)
                                    .addGap(18, 18, 18)
                                    .addComponent(puntajeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(img1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(img4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(img5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(intentosLbl)
                                        .addGap(18, 18, 18)
                                        .addComponent(intentosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(img6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(img2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(img3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(btnReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(img1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(img2, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(img3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(img4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(img5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(img6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(puntajeLbl)
                    .addComponent(puntajeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(intentosLbl)
                    .addComponent(intentosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReiniciar)
                    .addComponent(btnSalir))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarActionPerformed
        int result = JOptionPane.showOptionDialog(null, "Desea reiniciar el juego", "Reiniciar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        if (result == JOptionPane.OK_OPTION) {
            reiniciar();
        }
    }//GEN-LAST:event_btnReiniciarActionPerformed

    private void img1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_img1ActionPerformed
        img1.setIcon(frontImageIcon(cartasMap.get(1)));
        opcionesSeleccionadas++;
        img1.setEnabled(false);
        calculaPar(1);
    }//GEN-LAST:event_img1ActionPerformed

    private void img3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_img3ActionPerformed
        img3.setIcon(frontImageIcon(cartasMap.get(3)));
        opcionesSeleccionadas++;
        img3.setEnabled(false);
        calculaPar(3);
    }//GEN-LAST:event_img3ActionPerformed

    private void img2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_img2ActionPerformed
        img2.setIcon(frontImageIcon(cartasMap.get(2)));
        opcionesSeleccionadas++;
        img2.setEnabled(false);
        calculaPar(2);
    }//GEN-LAST:event_img2ActionPerformed

    private void img4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_img4ActionPerformed
        img4.setIcon(frontImageIcon(cartasMap.get(4)));
        opcionesSeleccionadas++;
        img4.setEnabled(false);
        calculaPar(4);
    }//GEN-LAST:event_img4ActionPerformed

    private void img5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_img5ActionPerformed
        img5.setIcon(frontImageIcon(cartasMap.get(5)));
        opcionesSeleccionadas++;
        img5.setEnabled(false);
        calculaPar(5);
    }//GEN-LAST:event_img5ActionPerformed

    private void img6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_img6ActionPerformed
        img6.setIcon(frontImageIcon(cartasMap.get(6)));
        opcionesSeleccionadas++;
        img6.setEnabled(false);
        calculaPar(6);
    }//GEN-LAST:event_img6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tablero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JToggleButton img1;
    private javax.swing.JToggleButton img2;
    private javax.swing.JToggleButton img3;
    private javax.swing.JToggleButton img4;
    private javax.swing.JToggleButton img5;
    private javax.swing.JToggleButton img6;
    private javax.swing.JLabel intentosLbl;
    private javax.swing.JTextField intentosTxt;
    private javax.swing.JLabel puntajeLbl;
    private javax.swing.JTextField puntajeTxt;
    // End of variables declaration//GEN-END:variables
}
