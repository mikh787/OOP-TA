/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tugasakhir;

/**
 *
 * @author Lenovo
 */
public class formMenu extends javax.swing.JFrame {

    /**
     * Creates new form formMenu
     */
    public formMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mPeserta = new javax.swing.JMenuItem();
        mPengguna = new javax.swing.JMenuItem();
        mKab = new javax.swing.JMenuItem();
        mKecamatan = new javax.swing.JMenuItem();
        mKelurahan = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mAdministrasi = new javax.swing.JMenuItem();
        mTransaksiNilai = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        mKeluar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Master");

        mPeserta.setText("Data Peserta");
        mPeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mPesertaActionPerformed(evt);
            }
        });
        jMenu1.add(mPeserta);

        mPengguna.setText("Data Pengguna");
        mPengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mPenggunaActionPerformed(evt);
            }
        });
        jMenu1.add(mPengguna);

        mKab.setText("Data Kab/Kota");
        mKab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mKabActionPerformed(evt);
            }
        });
        jMenu1.add(mKab);

        mKecamatan.setText("Data Kecamatan");
        mKecamatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mKecamatanActionPerformed(evt);
            }
        });
        jMenu1.add(mKecamatan);

        mKelurahan.setText("Data Kelurahan");
        mKelurahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mKelurahanActionPerformed(evt);
            }
        });
        jMenu1.add(mKelurahan);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Transaksi");

        mAdministrasi.setText("Transaksi Seleksi Administrasi");
        mAdministrasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mAdministrasiActionPerformed(evt);
            }
        });
        jMenu2.add(mAdministrasi);

        mTransaksiNilai.setText("Transaksi Penilaian");
        mTransaksiNilai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mTransaksiNilaiActionPerformed(evt);
            }
        });
        jMenu2.add(mTransaksiNilai);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Laporan");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Utility");

        mKeluar.setText("Keluar");
        mKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mKeluarActionPerformed(evt);
            }
        });
        jMenu4.add(mKeluar);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mPesertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mPesertaActionPerformed
        // TODO add your handling code here:
        new formPeserta().setVisible(true);
    }//GEN-LAST:event_mPesertaActionPerformed

    private void mPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mPenggunaActionPerformed
        // TODO add your handling code here:
        new formPengguna().setVisible(true);
    }//GEN-LAST:event_mPenggunaActionPerformed

    private void mKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mKeluarActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_mKeluarActionPerformed

    private void mKabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mKabActionPerformed
        // TODO add your handling code here:
        new formKabKot().setVisible(true);
    }//GEN-LAST:event_mKabActionPerformed

    private void mKecamatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mKecamatanActionPerformed
        // TODO add your handling code here:
        new formKecamatan().setVisible(true);
    }//GEN-LAST:event_mKecamatanActionPerformed

    private void mKelurahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mKelurahanActionPerformed
        // TODO add your handling code here:
        new formKelurahan().setVisible(true);
    }//GEN-LAST:event_mKelurahanActionPerformed

    private void mAdministrasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mAdministrasiActionPerformed
        // TODO add your handling code here:
        new formSeleksiAdm().setVisible(true);
    }//GEN-LAST:event_mAdministrasiActionPerformed

    private void mTransaksiNilaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mTransaksiNilaiActionPerformed
        // TODO add your handling code here:
        new formPenilaian().setVisible(true);
    }//GEN-LAST:event_mTransaksiNilaiActionPerformed

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
            java.util.logging.Logger.getLogger(formMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem mAdministrasi;
    private javax.swing.JMenuItem mKab;
    private javax.swing.JMenuItem mKecamatan;
    private javax.swing.JMenuItem mKeluar;
    private javax.swing.JMenuItem mKelurahan;
    private javax.swing.JMenuItem mPengguna;
    private javax.swing.JMenuItem mPeserta;
    private javax.swing.JMenuItem mTransaksiNilai;
    // End of variables declaration//GEN-END:variables
}
