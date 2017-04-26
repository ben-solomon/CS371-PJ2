/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
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
    public String[] periods ={"All","3 Months","6 Months","12 Months"};
    public String[] col = new String[] {"ID","Ad Title","Details","Date","Price","Created By","Moderated By","Category","Status"};
    private String[] categories;
    public Moderator(DBHandler db, String username) throws SQLException {
        this.currentUser = username;
        this.db = db;
        initComponents();
        clearSearch.setVisible(false);
        this.categories = db.getCategories();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        fillModAds(unclaimedAds);
        fillMyAds(myAds, currentUser);
        disapproveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows = myAds.getSelectedRows();
                String[] selectedIDs = new String[rows.length];
                for (int i=0;i<rows.length;i++){
                    //myAdsTable.getModel().getValueAt(myAdsTable.getSelectedRow(),1).toString()
                    String j = myAds.getModel().getValueAt(rows[i],0).toString();
                    selectedIDs[i] = j;
                    db.disapproveAds(selectedIDs);
                    
                }
                fillMyAds(myAds,currentUser);
                fillModAds(unclaimedAds);
            }
        });
        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 int[] rows = myAds.getSelectedRows();
                String[] selectedIDs = new String[rows.length];
                for (int i=0;i<rows.length;i++){
                    //myAdsTable.getModel().getValueAt(myAdsTable.getSelectedRow(),1).toString()
                    String j = myAds.getModel().getValueAt(rows[i],0).toString();
                    selectedIDs[i] = j;
                    db.approveAds(selectedIDs);
                    
                }
                fillMyAds(myAds,currentUser);
                fillModAds(unclaimedAds);
            }
            
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillModAds(unclaimedAds);
        fillMyAds(myAds, currentUser);
            }
        });
        myAds.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getClickCount() > 1) {
                myAds.getCellEditor().stopCellEditing();
            }
        }
    });
        unclaimedAds.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getClickCount() > 1) {
                unclaimedAds.getCellEditor().stopCellEditing();
            }
        }
    });
        DefaultComboBoxModel catDCB = new DefaultComboBoxModel(categories);
        DefaultComboBoxModel perDCB = new DefaultComboBoxModel(periods);
        clearSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillModAds(unclaimedAds);
                searchTextField.setText("");
                categoryDDL.setSelectedIndex(0);
                periodDDL.setSelectedIndex(0);
                clearSearch.setVisible(false);
            }
        });
        categoryDDL.setModel(catDCB);
        periodDDL.setModel(perDCB);
        claimAdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows = unclaimedAds.getSelectedRows();
                int[] selectedIDs = new int[rows.length];
                for (int i=0;i<rows.length;i++){
                    //myAdsTable.getModel().getValueAt(myAdsTable.getSelectedRow(),1).toString()
                    String j = unclaimedAds.getModel().getValueAt(rows[i],0).toString();
                    selectedIDs[i] = Integer.parseInt(j);
                    db.claimAds(selectedIDs, currentUser);
                    fillModAds(unclaimedAds);
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
                        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(db.getCustomModAds("All",category)),col));
                        break;
                    case "3 Months":
                        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(db.getCustomModAds("3 Months",category)),col));
                        break;
                    case "6 Months":
                        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(db.getCustomModAds("6 Months",category)),col));
                        break;
                    case "12 Months":
                        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(db.getCustomModAds("12 Months",category)),col));
                        break;
                    default:
                        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(db.getCustomModAds("All",category)),col));
                       
                        
                }
            }
        });
        // updates ads when user changes drop down list
        categoryDDL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> items = (JComboBox<String>) e.getSource();
                String selected = (String) items.getSelectedItem();
                String period = periodDDL.getSelectedItem().toString();
                switch(selected){
                    case "All":
                        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(db.getCustomModAds(period,"All")),col));
                        break;
                    case "Cars and Trucks":
                        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(db.getCustomModAds(period,"Cars and Trucks")),col));
                        break;
                    case "Electronics":
                        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(db.getCustomModAds(period,"Electronics")),col));
                        break;
                    case "Housing":
                        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(db.getCustomModAds(period,"Housing")),col));
                        break;
                    case "Child Care":
                        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(db.getCustomModAds(period,"Child Care")),col));
                        break;
                    default:
                        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(db.getCustomModAds(period,"All")),col));


                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        categoryDDL = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        periodDDL = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        gobutton = new javax.swing.JButton();
        claimAdButton = new javax.swing.JButton();
        clearSearch = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        unclaimedAds = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        approveButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        myAds = new javax.swing.JTable();
        disapproveButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        categoryDDL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Category");

        periodDDL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Period");

        jLabel3.setText("Title, Desc..");

        gobutton.setText("Search");
        gobutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gobuttonActionPerformed(evt);
            }
        });

        claimAdButton.setText("Claim Ad");

        clearSearch.setText("Clear");

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
        jScrollPane5.setViewportView(unclaimedAds);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(categoryDDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(periodDDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(69, 69, 69)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 536, Short.MAX_VALUE)
                                .addComponent(claimAdButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gobutton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clearSearch))))
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(claimAdButton)))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryDDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gobutton)
                    .addComponent(periodDDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pending Ads", jPanel1);

        approveButton.setText("Approve Selected Ads");

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

        disapproveButton.setText("Disapprove Selected Ads");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(disapproveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(approveButton))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(approveButton)
                    .addComponent(disapproveButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("My Advertisements", jPanel2);

        logoutButton.setText("Log Out");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        refreshButton.setText("Refresh Data");

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
                        .addComponent(refreshButton)
                        .addGap(23, 23, 23))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logoutButton)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(refreshButton)
                        .addGap(8, 8, 8)))
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        dispose();
        NewJFrame back = new NewJFrame(db);
        back.setVisible(true);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void gobuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gobuttonActionPerformed
         String text=this.searchTextField.getText();
        if ("".equals(text)) {
            Component frame = null;
            //custom title, error icon
        JOptionPane.showMessageDialog(frame,"Search string cannot be empty","Error", 
                JOptionPane.ERROR_MESSAGE);
        return;
        }
        clearSearch.setVisible(true);
        ArrayList<Ad> result=db.searchUnclaimed(text);
        if (result.isEmpty()){
            Component frame = null;
        JOptionPane.showMessageDialog(frame,"Search returned no results","Error", 
                JOptionPane.ERROR_MESSAGE);
        return;    
        }
                
       unclaimedAds.setModel(new DefaultTableModel(arrayToAd(result),col));
    }//GEN-LAST:event_gobuttonActionPerformed

    private void fillModAds (JTable jtable){

        ArrayList<Ad> list1 = db.getPendingAds();
        unclaimedAds.setModel(new DefaultTableModel(arrayToAd(list1),col));
    }
private void fillMyAds (JTable jtable, String user){

        ArrayList<Ad> list = db.getMyModAds(user);
        myAds.setModel(new DefaultTableModel(arrayToAd(list),col));
    }


// Turns list of Ad objects into 2D String array
   private String[] [] arrayToAd(ArrayList<Ad> list){
       String[][] ret = new String[list.size()][9];
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
           ret[i][8]= j.getStatus();
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
    private javax.swing.JButton approveButton;
    private javax.swing.JComboBox<String> categoryDDL;
    private javax.swing.JButton claimAdButton;
    private javax.swing.JButton clearSearch;
    private javax.swing.JButton disapproveButton;
    private javax.swing.JButton gobutton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTable myAds;
    private javax.swing.JComboBox<String> periodDDL;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JTable unclaimedAds;
    // End of variables declaration//GEN-END:variables
}
