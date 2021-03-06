/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author jayhixson
 */
public class AddAdForm extends javax.swing.JFrame {
    public DBHandler dbx;
    private MainForm f;
    public String currentUser;
    private String[] categories;
    String[] periods ={"All","3 Months","6 Months","12 Months"};
    String[] col = new String[] {"ID","Ad Title","Details","Date","Price","Created By","Moderated By","Category"};
    
    /**
     * Creates new form AddAdForm
     * @param username
     * @param db
     */
    // pass user and db conn to new form
    public AddAdForm(String username, DBHandler db) {
        this.dbx = db;
        this.currentUser = username;

        initComponents();
        // fill dropdowns with categories from db
        try {
            this.categories=db.getCategories();
        } catch (SQLException ex) {
            Logger.getLogger(AddAdForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultComboBoxModel DCB = new DefaultComboBoxModel(categories);
        Category.setModel(DCB);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Ad toAd = new Ad();
       if(!title.getText().equals("")){
           toAd.setTitle(title.getText());
       }
       else{
           JOptionPane.showMessageDialog(null,"Title can't be blank!");
       }
       if(!Details.getText().equals("")){
           toAd.setDetails(Details.getText());
       }
       else{
             JOptionPane.showMessageDialog(null,"Details can't be blank!");
       }
       if(!Price.getText().equals("")){
           toAd.setPrice(Price.getText());
       }
       else{
        JOptionPane.showMessageDialog(null,"Price can't be blank!");
        }
       toAd.setCategory(Category.getSelectedItem().toString());
       dbx.addAd(toAd);
         dispose();
            }
        });
        
    }

    AddAdForm(DBHandler db) {
        initComponents();
        DefaultComboBoxModel DCB = new DefaultComboBoxModel(categories);
        Category.setModel(DCB);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Ad toAd = new Ad();
       if(!title.getText().equals("")){
           toAd.setTitle(title.getText());
       }
       else{
           JOptionPane.showMessageDialog(null,"Title can't be blank!");
       }
       if(!Details.getText().equals("")){
           toAd.setDetails(Details.getText());
       }
       else{
             JOptionPane.showMessageDialog(null,"Details can't be blank!");
       }
       if(!Price.getText().equals("")){
           toAd.setPrice(Price.getText());
       }
       else{
        JOptionPane.showMessageDialog(null,"Price can't be blank!");
        }
       toAd.setCategory(Category.getSelectedItem().toString());
       dbx.addAd(toAd);
         dispose();
         
            }
        });
  
    }

    private AddAdForm() {
        initComponents();
        this.dbx =null;
        this.currentUser = "";
        DefaultComboBoxModel DCB = new DefaultComboBoxModel(categories);
        Category.setModel(DCB);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Ad toAd = new Ad();
       if(!title.getText().equals("")){
           toAd.setTitle(title.getText());
       }
       else{
           JOptionPane.showMessageDialog(null,"Title can't be blank!");
       }
       if(!Details.getText().equals("")){
           toAd.setDetails(Details.getText());
       }
       else{
             JOptionPane.showMessageDialog(null,"Details can't be blank!");
       }
       if(!Price.getText().equals("")){
           toAd.setPrice(Price.getText());
       }
       else{
        JOptionPane.showMessageDialog(null,"Price can't be blank!");
        }
       toAd.setCategory((String) Category.getSelectedItem());
       // after checking fields for null values, the ad is added to the DB
       dbx.addAd(toAd);
       dispose();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Details = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        Category = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        Price = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Title");

        Details.setColumns(20);
        Details.setRows(5);
        jScrollPane1.setViewportView(Details);

        jLabel2.setText("Details");

        Category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Category");

        jLabel4.setText("Price");

        jButton1.setText("Add Advertisement");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(142, 142, 142))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                .addComponent(title))
                            .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
       
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Category;
    private javax.swing.JTextArea Details;
    private javax.swing.JTextField Price;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField title;
    // End of variables declaration//GEN-END:variables
}
