package javaapplication1;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.TabPane;
import javax.swing.table.DefaultTableModel;


public class MainForm extends javax.swing.JFrame {
private DBHandler db;
String currentUser;
/* Shouldn't thse next three arrays be in the DBhandler class?
    They are also living in the Moderator Frame
*/

String[] categories = {"All","Cars and Trucks","Electronics","Housing","Child Care"};
String[] periods ={"All","3 Months","6 Months","12 Months"};
String[] col = new String[] {"ID","Ad Title","Details","Date","Price","Created By","Moderated By","Category"};

    public MainForm(DBHandler db,String user) {
        this.db=db;

        this.currentUser = user;

        initComponents();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        fillUserAds(adsTable);
        fillUserOwnAds(myAdsTable);
        DefaultComboBoxModel catDCB = new DefaultComboBoxModel(categories);
        DefaultComboBoxModel perDCB = new DefaultComboBoxModel(periods);
        categoryDDL.setModel(catDCB);
        periodDDL.setModel(perDCB);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillUserAds(adsTable);
                fillUserOwnAds(myAdsTable);
                categoryDDL.setSelectedIndex(0);
                periodDDL.setSelectedIndex(0);
            }
        });
<<<<<<< HEAD
        // updates ads when user changes drop down list
=======
        // updates ads when user changes drop down list 
>>>>>>> ben-solomon/master
        categoryDDL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> items = (JComboBox<String>) e.getSource();
                String selected = (String) items.getSelectedItem();
                String period = periodDDL.getSelectedItem().toString();
                switch(selected){
                    case "All":
                        adsTable.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds(period,"All")),col));
                        break;
                    case "Cars and Trucks":
                        adsTable.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds(period,"Cars and Trucks")),col));
                        break;
                    case "Electronics":
                        adsTable.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds(period,"Electronics")),col));
                        break;
                    case "Housing":
                        adsTable.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds(period,"Housing")),col));
                        break;
                    case "Child Care":
                        adsTable.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds(period,"Child Care")),col));
                        break;
                    default:
                        adsTable.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds(period,"All")),col));


                }

            }
        });
        periodDDL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             JComboBox<String> items = (JComboBox<String>) e.getSource();
                String selected = (String) items.getSelectedItem();
                String category = categoryDDL.getSelectedItem().toString();
                switch(selected){
                    case "All":
                        adsTable.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds("All",category)),col));
                        break;
                    case "3 Months":
                        adsTable.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds("3 Months",category)),col));
                        break;
                    case "6 Months":
                        adsTable.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds("6 Months",category)),col));
                        break;
                    case "12 Months":
                        adsTable.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds("12 Months",category)),col));
                        break;
                    default:
                        adsTable.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds("All",category)),col));


                }
<<<<<<< HEAD
=======
              
            }
        });
        periodDDL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             JComboBox<String> items = (JComboBox<String>) e.getSource();
                String selected = (String) items.getSelectedItem();
                String category = categoryDDL.getSelectedItem().toString();
                switch(selected){
                    case "All":
                        adsTable.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds("All",category)),col));
                        break;
                    case "3 Months":
                        adsTable.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds("3 Months",category)),col));
                        break;
                    case "6 Months":
                        adsTable.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds("6 Months",category)),col));
                        break;
                    case "12 Months":
                        adsTable.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds("12 Months",category)),col));
                        break;
                    default:
                        adsTable.setModel(new DefaultTableModel(arrayToAd(db.getCustomAds("All",category)),col));
                       
                        
                }
>>>>>>> ben-solomon/master
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        categoryDDL = new javax.swing.JComboBox<>();
        periodDDL = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        adsTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        deleteButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        myAdsTable = new javax.swing.JTable();
        editButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        addAdvButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Category");

        jLabel4.setText("Period");

        categoryDDL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        categoryDDL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryDDLActionPerformed(evt);
            }
        });

        periodDDL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        periodDDL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                periodDDLActionPerformed(evt);
            }
        });

        jLabel2.setText("Search Title/Description");

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        adsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(adsTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(categoryDDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(periodDDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(categoryDDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(periodDDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton))))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        TabPane.addTab("Advertisements", jPanel1);

        deleteButton.setText("Delete Ad");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        myAdsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(myAdsTable);

        editButton.setText("Save Changes");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteButton)
                .addGap(43, 43, 43))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton)
                    .addComponent(deleteButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 885, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 322, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        TabPane.addTab("My Advertisements", jPanel2);

        logoutButton.setText("Log Out");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        addAdvButton.setText("Add Advertisement");
        addAdvButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAdvButtonActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");

        refreshButton.setText("Refresh Data");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
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
                        .addComponent(TabPane)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logoutButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshButton)
                        .addGap(18, 18, 18)
                        .addComponent(addAdvButton, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logoutButton)
                    .addComponent(addAdvButton)
                    .addComponent(refreshButton))
                .addGap(16, 16, 16)
                .addComponent(TabPane)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed

    @SuppressWarnings("empty-statement")
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
    private void fillUserAds (JTable jtable){

        ArrayList<Ad> list = db.getAllActiveAds();
        jtable.setModel(new DefaultTableModel(arrayToAd(list),col));
    }
    private void fillUserOwnAds (JTable jtable){
<<<<<<< HEAD

=======
        
>>>>>>> ben-solomon/master
        ArrayList<Ad> list = db.getAllUserActiveAds(currentUser);
        jtable.setModel(new DefaultTableModel(arrayToAd(list),col));
    }
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        dispose();
        NewJFrame back = new NewJFrame(db);
        back.setVisible(true);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void categoryDDLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryDDLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryDDLActionPerformed

    private void periodDDLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_periodDDLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_periodDDLActionPerformed

    private void addAdvButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAdvButtonActionPerformed
<<<<<<< HEAD

=======
>>>>>>> ben-solomon/master
    AddAdForm add = new AddAdForm(currentUser,db);
    add.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_addAdvButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshButtonActionPerformed
<<<<<<< HEAD

=======
    
>>>>>>> ben-solomon/master
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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane TabPane;
    private javax.swing.JButton addAdvButton;
    private javax.swing.JTable adsTable;
    private javax.swing.JComboBox<String> categoryDDL;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTable myAdsTable;
    private javax.swing.JComboBox<String> periodDDL;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables
}
