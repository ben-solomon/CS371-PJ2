package javaapplication1;
import java.awt.Point;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TabPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class MainForm extends javax.swing.JFrame {
private DBHandler db;
String currentUser;
/* Shouldn't thse next three arrays be in the DBhandler class?
    They are also living in the Moderator Frame
*/

private String[] categories;
String[] periods ={"All","3 Months","6 Months","12 Months"};
String[] col = new String[] {"ID","Ad Title","Details","Date","Price","Created By","Moderated By","Category"};

    public MainForm(DBHandler db,String user) {
        this.db=db;

        this.currentUser = user;
        
        initComponents();
    try {
        this.categories = db.getCategories();
    } catch (SQLException ex) {
        Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
    }
        // set buttons invisible until user action
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        clearButton.setVisible(false);
        editAdButton.setVisible(false);
        fillUserAds(adsTable);
        fillUserOwnAds(myAdsTable);
        deleteButton.setVisible(false);
        // delete button, visible on row click
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String id = myAdsTable.getModel().getValueAt(myAdsTable.getSelectedRow(),0).toString();
               if(db.deleteAd(id)){
                   JOptionPane.showMessageDialog(null,"Ad deleted succesfully");
                   fillUserOwnAds(myAdsTable);
               }
            }
        });
        //edit button, visible on row click
        editAdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Ad temp = new Ad();
               String id = myAdsTable.getModel().getValueAt(myAdsTable.getSelectedRow(),0).toString();
               temp.setID(Integer.parseInt(id));
               temp.setTitle(myAdsTable.getModel().getValueAt(myAdsTable.getSelectedRow(),1).toString());
               temp.setDetails(myAdsTable.getModel().getValueAt(myAdsTable.getSelectedRow(),2).toString());
               temp.setPrice(myAdsTable.getModel().getValueAt(myAdsTable.getSelectedRow(),4).toString());
               temp.setCategory(myAdsTable.getModel().getValueAt(myAdsTable.getSelectedRow(),7).toString());
               UpdateAdForm update = new UpdateAdForm(db,currentUser,temp);
               update.setVisible(true);                    
            }
        });
        myAdsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                editAdButton.setVisible(true);
                deleteButton.setVisible(true);
            }
        });
        // users can only select one ad at a time to edit
        adsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        myAdsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // disable cell editing in tables - edit form used instead
        adsTable.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getClickCount() > 1) {
                adsTable.getCellEditor().stopCellEditing();
            }
        }
    });
        myAdsTable.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getClickCount() > 1) {
                myAdsTable.getCellEditor().stopCellEditing();
            }
        }
    });
        //create and fill dropdown boxes
        DefaultComboBoxModel catDCB = new DefaultComboBoxModel(categories);
        DefaultComboBoxModel perDCB = new DefaultComboBoxModel(periods);
        categoryDDL.setModel(catDCB);
        periodDDL.setModel(perDCB);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillUserAds(adsTable);
                searchTextField.setText("");
                clearButton.setVisible(false);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = (String) searchTextField.getText();
                if (! text.isEmpty()){
                    fillSearchedAds(adsTable,text);
                    clearButton.setVisible(true);
                    }
                }
            
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillUserAds(adsTable);
                fillUserOwnAds(myAdsTable);
                categoryDDL.setSelectedIndex(0);
                periodDDL.setSelectedIndex(0);
                editAdButton.setVisible(false);
            }
        });
        // updates ads when user changes drop down list selection
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
        clearButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        adsTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        deleteButton = new javax.swing.JButton();
        editAdButton = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        myAdsTable = new javax.swing.JTable();
        logoutButton = new javax.swing.JButton();
        addAdvButton = new javax.swing.JButton();
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

        clearButton.setText("Clear");

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
        jScrollPane1.setViewportView(adsTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(categoryDDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(periodDDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 435, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(clearButton)))))
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
                            .addComponent(searchButton)
                            .addComponent(clearButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
        );

        TabPane.addTab("Advertisements", jPanel1);

        deleteButton.setText("Delete Ad");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        editAdButton.setText("Edit Selected Ad");

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
        jScrollPane6.setViewportView(myAdsTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(editAdButton)
                        .addGap(674, 674, 674)
                        .addComponent(deleteButton)
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editAdButton)
                    .addComponent(deleteButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
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
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logoutButton)
                    .addComponent(addAdvButton)
                    .addComponent(refreshButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(TabPane, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
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
    public void fillUserAds (JTable jtable){
        
        ArrayList<Ad> list = db.getAllActiveAds();
        jtable.setModel(new DefaultTableModel(arrayToAd(list),col));
    }
    public void fillUserOwnAds (JTable jtable){
        
        ArrayList<Ad> list = db.getAllUserActiveAds(currentUser);
        jtable.setModel(new DefaultTableModel(arrayToAd(list),col));
    }
        private void fillSearchedAds (JTable jtable, String text){
        
        ArrayList<Ad> list = db.searchAllActive(text);
        jtable.setModel(new DefaultTableModel(arrayToAd(list),col));
    }
    
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteButtonActionPerformed

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
    AddAdForm add = new AddAdForm(currentUser,db);
    add.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_addAdvButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshButtonActionPerformed
    
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
    public javax.swing.JTable adsTable;
    private javax.swing.JComboBox<String> categoryDDL;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editAdButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JButton logoutButton;
    public javax.swing.JTable myAdsTable;
    private javax.swing.JComboBox<String> periodDDL;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables
}
