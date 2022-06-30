/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package appdb1;

import java.awt.print.PrinterException;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.MessageFormat;
import java.util.Calendar;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SwingWorker;



/**
 *
 * @author Lenovo
 */
public class formTransaksi extends javax.swing.JFrame {
    Connection Con;
    ResultSet RsBrg;
    ResultSet RsKons;
    Statement stm;
    double total=0;
    String tanggal;
    String idBrg;
    String namaBrg;
    String hargaBrg;
    Boolean edit=false;
    DefaultTableModel tableModel = new DefaultTableModel( new Object [][] {},
    new String [] {"Kd Barang", "Nama Barang","Harga Barang","Jumlah","Total"});


    /**
     * Creates new form formTransaksi
     */
    public formTransaksi() {
        initComponents();
        open_db();
        inisialisasi_tabel();
        aktif(false);
        setTombol(true);
        txtTgl.setEditor(new JSpinner.DateEditor(txtTgl,"yyyy/MM/dd")); 
        format_tanggal();
    }
    
    private void open_db(){
        try{
            KoneksiMysql kon = new KoneksiMysql("localhost","root","","form_barang");
            Con = kon.getConnection();
            //System.out.println("Berhasil ");
        }catch (Exception e) {
            System.out.println("Error : "+e);
        }
    }
    
    private void hitung_jual(){
        double xtot,xhrg;
        int xjml;
        xhrg=Double.parseDouble(txtHrgBrg.getText());
        xjml=Integer.parseInt(txtJml.getText());
        xtot=xhrg*xjml;
        String xtotal=Double.toString(xtot);
        txtTotal.setText(xtotal);
        total=total+xtot;
        txtTotal.setText(Double.toString(total));
    }
    
    private void baca_konsumen(){
        try{
            stm= Con.createStatement();
            ResultSet rs=stm.executeQuery("select kd_kons,nm_kons from tblkonsumen");
            rs.beforeFirst();
            while(rs.next()){
                cmbKd_Kons.addItem(rs.getString(1).trim());
            }
            rs.close();
        }catch(SQLException e){
            System.out.println("Error : "+e);
        }
    }
    
    private void baca_barang(){
        try{
            stm= Con.createStatement();
            ResultSet rs=stm.executeQuery("select * from tblbarang");
            rs.beforeFirst();
            while(rs.next()){
                cmbKd_Brg.addItem(rs.getString(1).trim());
            }
            rs.close();
        }catch(SQLException e){
            System.out.println("Error : "+e);
        }
    }
    
    private void detail_barang(String xkode){
        try{
            stm= Con.createStatement();
            ResultSet rs=stm.executeQuery("select * from tblbarang where kd_brg='"+xkode+"'");
            rs.beforeFirst();
            while(rs.next()){
                txtNmBrg.setText(rs.getString(2).trim());
                txtHrgBrg.setText(Double.toString((Double)rs.getDouble(4)));
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("Error : "+e);
        }
    }
        
    private void detail_konsumen(String xkode){
        try{
            stm= Con.createStatement();
            ResultSet rs=stm.executeQuery("select * from tblkonsumen where kd_kons='"+xkode+"'");
            rs.beforeFirst();
            while(rs.next()){
                txtNmKons.setText(rs.getString(2).trim());
            }
            rs.close();
        }catch(SQLException e){
            System.out.println("Error : "+e);
        }
    }
    
    public void inisialisasi_tabel(){
        tblJual.setModel(tableModel);
    }
    
    //method pengkosongan isian
    private void kosong(){
        txtNoJual.setText("");
        txtNmKons.setText("");
        txtHrgBrg.setText("");
        txtTotal.setText("");
    }
    
        //method kosongkan detail jual
    private void kosong_detail(){
        txtNmBrg.setText("");
        txtHrgBrg.setText("");
        txtJml.setText("");
        txtTotal.setText("");
    }
    
    private void aktif(boolean x){
        cmbKd_Kons.setEnabled(x);
        cmbKd_Brg.setEnabled(x);
        txtTgl.setEnabled(x);
        txtJml.setEditable(x);
    }
    
    //method set tombol on/off
    private void setTombol(boolean t){
        cmdTambah.setEnabled(t);
        cmdSimpan.setEnabled(!t);
        cmdBatal.setEnabled(!t);
        cmdKeluar.setEnabled(t);
        cmdHapusItem.setEnabled(!t);
    }
    
    private void nomor_jual(){
        try{
            stm= Con.createStatement();
            ResultSet rs=stm.executeQuery("select no_jual from jual");
            int brs=0;
            while(rs.next()){
                brs=rs.getRow();
            }
            if(brs==0){
                txtNoJual.setText("1");
            }else{
                int nom=brs+1;
                txtNoJual.setText(Integer.toString(nom));
            }
                rs.close();
            }catch(SQLException e){
            System.out.println("Error : "+e);
        }
    }
    
    private void simpan_ditabel(){
        try{
            String tKode=cmbKd_Brg.getSelectedItem().toString();
            String tNama=txtNmBrg.getText();
            double hrg=Double.parseDouble(txtHrgBrg.getText());
            int jml=Integer.parseInt(txtJml.getText());
            double tot=Double.parseDouble(txtTotal.getText());
            tableModel.addRow(new Object[]{tKode,tNama,hrg,jml,tot});
            inisialisasi_tabel();
        }catch(Exception e){
            System.out.println("Error : "+e);
        }
    }
    
        private void simpan_transaksi(){
        try{
            String xnojual=txtNoJual.getText();
            format_tanggal();
            String xkode=cmbKd_Kons.getSelectedItem().toString();
            String msql="insert into jual values('"+xnojual+"','"+xkode+"','"+tanggal+"')";
            stm.executeUpdate(msql);
            for(int i=0;i<tblJual.getRowCount();i++){
                String xkd=(String)tblJual.getValueAt(i,0);
                double xhrg=(Double)tblJual.getValueAt(i,2);
                int xjml=(Integer)tblJual.getValueAt(i,3);
                String zsql="insert into djual values('"+xnojual+"','"+xkd+"',"+xhrg+","+xjml+")";
                stm.executeUpdate(zsql);
            }
        }catch(SQLException e){
            System.out.println("Error : "+e);
        }
    }
        
    private void format_tanggal(){
        String DATE_FORMAT = "yyyy-MM-dd";
        java.text.SimpleDateFormat sdf = new
        java.text.SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance();
        int year=c1.get(Calendar.YEAR);
        int month=c1.get(Calendar.MONTH)+1;
        int day=c1.get(Calendar.DAY_OF_MONTH);
        tanggal=Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);
    }
    
    private class PrintingTask extends SwingWorker<Object, Object>{
        private final MessageFormat headerFormat;
        private final MessageFormat footerFormat;
        private final boolean interactive;
        private volatile boolean complete = false;
        private volatile String message;
        public PrintingTask(MessageFormat header, MessageFormat footer, boolean interactive){
            this.headerFormat = header;
            this.footerFormat = footer;
            this.interactive = interactive;
    }
        
    @Override
    protected Object doInBackground(){
        try{
            complete = text.print(headerFormat, footerFormat, true, null, null, interactive);
            message = "Printing " + (complete ? "complete" : "canceled");
        }catch(PrinterException ex){
            message = "Sorry, a printer error occurred";
        }catch(SecurityException ex){
            message = "Sorry, cannot access the printer due to security reasons";
        }
            return null;
        }
        @Override
        protected void done() {
            message(!complete, message);
        }
    }
    
    private MessageFormat createFormat(String source) {
        String text = source;
        if (text != null && text.length() > 0) {
            try {
                return new MessageFormat(text);
            } catch (IllegalArgumentException e) {
                error("Sorry, this format is invalid.");
            }
        }
        return null;
    }
    
    private void message(boolean error, String msg) {
        int type = (error ? JOptionPane.ERROR_MESSAGE :
        JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(this, msg, "Printing", type);
    }
    
    private void error(String msg) {
        message(true, msg);
    }
    
    public double getTotal() {
        double xtotal=0;
        for(int i = 0; i < tblJual.getRowCount(); i++){//For each row
            xtotal=xtotal+Double.parseDouble(tblJual.getModel().getValueAt(i,4).toString());
        }
        return xtotal;
    }
    
    private boolean cekRowData(String cari) {
                boolean cek=false;
        for(int i = 0; i < tblJual.getRowCount(); i++){//For each row
            for(int j = 0; j < tblJual.getColumnCount(); j++){//For each column in that row
                if(tblJual.getModel().getValueAt(i, j).equals(cari)){//Search the model
                    System.out.println(tblJual.getModel().getValueAt(i, j));//Print if found string
                    cek=true;
                }
            }//For loop inner
        }//For loop outer
        return cek;
    }
    
    private int getStok(String xkode) {
        int jStok=0;
        try{
            stm=(Statement) Con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=stm.executeQuery("select kd_brg,stok from barang where kd_brg='"+ xkode + "' limit 1");
            rs.beforeFirst();
            while(rs.next()) {
                jStok=rs.getInt("stok");
            }
            rs.close();
        } catch(SQLException e){
            System.out.println("Error : "+e);
        }
        return jStok;
    }
    
    public void itemTerpilih(){
        formDaftarBarang fDB = new formDaftarBarang();
        fDB.fAB = this;
        text.setText(idBrg);
        txtNmBrg.setText(namaBrg);
        txtHrgBrg.setText(hargaBrg);
    }

        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        text = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblJual = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNoJual = new javax.swing.JTextField();
        txtTgl = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNmKons = new javax.swing.JTextField();
        cmbKd_Brg = new javax.swing.JComboBox<>();
        cmbKd_Kons = new javax.swing.JComboBox<>();
        txtNmBrg = new javax.swing.JTextField();
        txtHrgBrg = new javax.swing.JTextField();
        txtJml = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        cmdHapusItem = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        cmdTambah = new javax.swing.JButton();
        cmdSimpan = new javax.swing.JButton();
        cmdBatal = new javax.swing.JButton();
        cmdCetak = new javax.swing.JButton();
        cmdKeluar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        txtBayar = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        btnPilih = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        text.setColumns(20);
        text.setRows(5);
        jScrollPane1.setViewportView(text);

        tblJual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblJual);

        jLabel2.setText("No Jual");

        jLabel3.setText("Tgl Jual");

        txtTgl.setModel(new javax.swing.SpinnerDateModel());
        txtTgl.setEditor(new javax.swing.JSpinner.DateEditor(txtTgl, ""));

        jLabel4.setText("Kode Konsumen");

        jLabel5.setText("Nama Konsumen");

        cmbKd_Brg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbKd_Kons.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbKd_Kons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKd_KonsActionPerformed(evt);
            }
        });

        cmdHapusItem.setText("Hapus");
        cmdHapusItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHapusItemActionPerformed(evt);
            }
        });

        cmdTambah.setText("Tambah");
        cmdTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTambahActionPerformed(evt);
            }
        });

        cmdSimpan.setText("Simpan");
        cmdSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSimpanActionPerformed(evt);
            }
        });

        cmdBatal.setText("Batal");
        cmdBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBatalActionPerformed(evt);
            }
        });

        cmdCetak.setText("Cetak");
        cmdCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCetakActionPerformed(evt);
            }
        });

        cmdKeluar.setText("Keluar");
        cmdKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdKeluarActionPerformed(evt);
            }
        });

        jLabel6.setText("Total");

        jLabel7.setText("Bayar");

        jLabel8.setText("Kembali");

        btnPilih.setText("Pilih Barang");
        btnPilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilihActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbKd_Brg, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNmBrg, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtHrgBrg)
                                .addGap(18, 18, 18)
                                .addComponent(txtJml, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTotal))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmdHapusItem)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnPilih)
                                        .addGap(34, 34, 34)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3))
                                        .addGap(28, 28, 28)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNoJual, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(147, 147, 147)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNmKons, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbKd_Kons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 43, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmdTambah)
                                .addGap(18, 18, 18)
                                .addComponent(cmdSimpan)
                                .addGap(18, 18, 18)
                                .addComponent(cmdBatal)
                                .addGap(18, 18, 18)
                                .addComponent(cmdCetak)
                                .addGap(18, 18, 18)
                                .addComponent(cmdKeluar))
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                            .addComponent(txtBayar)
                            .addComponent(jTextField8))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNoJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cmbKd_Kons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(txtNmKons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbKd_Brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNmBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHrgBrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtJml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdHapusItem)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPilih))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdTambah)
                    .addComponent(cmdSimpan)
                    .addComponent(cmdBatal)
                    .addComponent(cmdCetak)
                    .addComponent(cmdKeluar))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilihActionPerformed
        // TODO add your handling code here:
        formDaftarBarang fDB = new formDaftarBarang();
        fDB.fAB = this;
        fDB.setVisible(true);
        fDB.setResizable(false);
    }//GEN-LAST:event_btnPilihActionPerformed

    private void cmbKd_KonsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKd_KonsActionPerformed
        // TODO add your handling code here:
        detail_konsumen(cmbKd_Kons.getSelectedItem().toString());
    }//GEN-LAST:event_cmbKd_KonsActionPerformed

    private void cmdTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTambahActionPerformed
        // TODO add your handling code here:
        kosong();
        kosong_detail();
        ComboBoxModel c1=new DefaultComboBoxModel();
        ComboBoxModel c2=new DefaultComboBoxModel();
        cmbKd_Kons.setModel(c1);
        cmbKd_Brg.setModel(c2);
        baca_konsumen();
        baca_barang();
        aktif(true);
        setTombol(false);
        nomor_jual();
    }//GEN-LAST:event_cmdTambahActionPerformed

    private void cmdSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSimpanActionPerformed
        // TODO add your handling code here:
        try {
            if(Double.parseDouble(txtBayar.getText())<
                Double.parseDouble(txtTotal.getText())) {
                JOptionPane.showMessageDialog(null, "Jumlah Bayar lebih kecil (<) dari Total,Ulangi....!!!!");
                return;
            }
            simpan_transaksi();
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            return;
        }
        JOptionPane.showMessageDialog(null, "Data Berhasil disimpan");
        aktif(false);
        setTombol(true);
    }//GEN-LAST:event_cmdSimpanActionPerformed

    private void cmdBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBatalActionPerformed
        // TODO add your handling code here:
        aktif(false);
        setTombol(true);
    }//GEN-LAST:event_cmdBatalActionPerformed

    private void cmdKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdKeluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_cmdKeluarActionPerformed

    private void cmdCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCetakActionPerformed
        // TODO add your handling code here:
        String ctk="Nota Penjualan\nNo:"+txtNoJual.getText()+"\nTanggal : "+tanggal;
        ctk=ctk+"\n"+"-----------------------------------------------------------------------------------------";
        ctk=ctk+"\n"+"Kode\tNama Barang\tHarga\tJml\tTotal";
        ctk=ctk+"\n"+"-------------------------------------------------------------------------------------------------";
        for(int i=0;i<tblJual.getRowCount();i++) {
            String xkd=(String)tblJual.getValueAt(i,0);
            String xnama=(String)tblJual.getValueAt(i,1);
            double xhrg=(Double)tblJual.getValueAt(i,2);
            int xjml=(Integer)tblJual.getValueAt(i,3);
            double xtot=(Double)tblJual.getValueAt(i,4);
            ctk=ctk+"\n"+xkd+"\t"+xnama+"\t"+xhrg+"\t"+xjml+"\t"+xtot;
        }
        ctk=ctk+"\n"+"-------------------------------------------------------------------------------------------------";
        text.setText(ctk);
        String headerField="";
        String footerField="";
        MessageFormat header = createFormat(headerField);
        MessageFormat footer = createFormat(footerField);
        boolean interactive = true;//interactiveCheck.isSelected();
        boolean background = true;//backgroundCheck.isSelected();
        PrintingTask task = new PrintingTask(header, footer, interactive);
        if (background) {
            task.execute();
        } else {
            task.run();
        }
    }//GEN-LAST:event_cmdCetakActionPerformed

    private void cmdHapusItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHapusItemActionPerformed
        // TODO add your handling code here:
        int row=tblJual.getSelectedRow();
        ((DefaultTableModel) tblJual.getModel()).removeRow(row);
        txtTotal.setText(Double.toString(getTotal()));

    }//GEN-LAST:event_cmdHapusItemActionPerformed

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
            java.util.logging.Logger.getLogger(formTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPilih;
    private javax.swing.JComboBox<String> cmbKd_Brg;
    private javax.swing.JComboBox<String> cmbKd_Kons;
    private javax.swing.JButton cmdBatal;
    private javax.swing.JButton cmdCetak;
    private javax.swing.JButton cmdHapusItem;
    private javax.swing.JButton cmdKeluar;
    private javax.swing.JButton cmdSimpan;
    private javax.swing.JButton cmdTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTable tblJual;
    private javax.swing.JTextArea text;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtHrgBrg;
    private javax.swing.JTextField txtJml;
    private javax.swing.JTextField txtNmBrg;
    private javax.swing.JTextField txtNmKons;
    private javax.swing.JTextField txtNoJual;
    private javax.swing.JSpinner txtTgl;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
