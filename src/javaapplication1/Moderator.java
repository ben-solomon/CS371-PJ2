/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Moderator extends javax.swing.JFrame {
    private DBHandler db;
    private String currentUser;
    public String[] categories = {"All","Cars and Trucks","Electronics","Housing","Child Care"};
    public String[] periods ={"All","3 Months","6 Months","12 Months"};
    public String[] col = new String[] {"ID","Ad Title","Details","Date","Price","Created By","Moderated By","Category"};
    public Moderator(DBHandler db, String username) {
        this.currentUser = username;
        this.db = db;
        initComponents();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.fillModAds(unclaimedAds);
        this.fillMyAds(myAds, currentUser);
        DefaultComboBoxModel catDCB = new DefaultComboBoxModel(categories);
        DefaultComboBoxModel perDCB = new DefaultComboBoxModel(periods);
        categoryDDL.setModel(catDCB);
        periodDDL.setModel(perDCB);
        // updates ads when user changes drop down list
        categoryDDL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> items = (JComboBox<String>) e.getSource();
                String selected = (String) items.getSelectedItem();
                String period = periodDDL.getSelectedItem().toString();
                switch(selected){
                    case "All":
                        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds(period,"All")),col));
                        break;
                    case "Cars and Trucks":
                        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds(period,"Cars and Trucks")),col));
                        break;
                    case "Electronics":
                        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds(period,"Electronics")),col));
                        break;
                    case "Housing":
                        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds(period,"Housing")),col));
                        break;
                    case "Child Care":
                        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds(period,"Child Care")),col));
                        break;
                    default:
                        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds(period,"All")),col));


                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        categoryDDL = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        periodDDL = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        gobutton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        unclaimedAds = new javax.swing.JTable();
        claimAdButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        myAds = new javax.swing.JTable();
        approve_button = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        NewAdButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        categoryDDL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Category");

        periodDDL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Period");

        jLabel3.setText("Title, Desc..");

        gobutton.setText("GO");
        gobutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gobuttonActionPerformed(evt);
            }
        });

        unclaimedAds.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(unclaimedAds);

        claimAdButton.setText("Claim Ad");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(categoryDDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(periodDDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 446, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gobutton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(claimAdButton)
                        .addGap(15, 15, 15))))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addComponent(jLabel3))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryDDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gobutton)
                    .addComponent(periodDDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(claimAdButton))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Unclaimed Ads", jPanel1);

        myAds.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(myAds);

        approve_button.setText("approve");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(approve_button)
                .addGap(35, 35, 35))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(approve_button)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("My Advertisements", jPanel2);

        logoutButton.setText("Log Out");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        NewAdButton.setText("Add Advertisement");
        NewAdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewAdButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logoutButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NewAdButton)
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logoutButton)
                    .addComponent(NewAdButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        dispose();
        NewJFrame back = new NewJFrame(db);
        back.setVisible(true);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void NewAdButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewAdButtonActionPerformed
    AddAdForm add = new AddAdForm(currentUser,db);
    add.setVisible(true);

            // TODO add your handling code here:
    }//GEN-LAST:event_NewAdButtonActionPerformed
    private void gobuttonActionPerformed(java.awt.event.ActionEvent evt) {
        String text=this.searchTextField.getText();
        if ("".equals(text)) {
            Component frame = null;
            //custom title, error icon
        JOptionPane.showMessageDialog(frame,"Search string cannot be empty","Error", 
                JOptionPane.ERROR_MESSAGE);
        return;
        }
        ArrayList<Ad> result=db.searchUnclaimed(text);
        if (result.isEmpty()){
            Component frame = null;
        JOptionPane.showMessageDialog(frame,"Search returned no results","Error", 
                JOptionPane.ERROR_MESSAGE);
        return;    
        }
                
       unclaimedAds.setModel(new DefaultTableModel(arrayToAd(result),col));
        
    }
    private void fillModAds (JTable jtable){

        ArrayList<Ad> list = db.getPendingAds();
        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(list),col));
    }
private void fillMyAds (JTable jtable, String user){

        ArrayList<Ad> list = db.getMyModAds(user);
        myAds.setModel(new DefaultTableModel(arrayToAd(list),col));
    }


// Turns list of Ad objects into 2D String array
   private String[] [] arrayToAd(ArrayList<Ad> list){
       String[][] ret = new String[list.size()][8];
       int i = 0;
       for (Ad j:list){
           ret[i][0] = Integer.toString(j.getID());
           ret[i][1] = j.getTitle();
           ret[i][2] = j.getDetails();
           ret[i][3] = j.getDate();
           ret[i][4] = j.getPrice();
           ret[i][5] = j.getUser();
           ret[i][6] = j.getModerator();
           ret[i][7] = j.getCategory();
           i+=1;
       }
       return ret;
   }
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
            java.util.logging.Logger.getLogger(Moderator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Moderator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Moderator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Moderator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton NewAdButton;
    private javax.swing.JButton approve_button;
    private javax.swing.JComboBox<String> categoryDDL;
    private javax.swing.JButton claimAdButton;
    private javax.swing.JButton gobutton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTable myAds;
    private javax.swing.JComboBox<String> periodDDL;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JTable unclaimedAds;
    // End of variables declaration//GEN-END:variables
}
