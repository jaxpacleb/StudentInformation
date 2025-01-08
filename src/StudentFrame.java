
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentFrame extends javax.swing.JFrame {

    public StudentFrame(int userId) {
        this.userId = userId;
        initComponents();

        checkPrivileged();
    }

    public void checkPrivileged() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/students", "root", "Gwapsako@2004");
            String query = "SELECT * FROM userinformation WHERE userId = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, this.userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String lastName = rs.getString("lastName");
                String firstName = rs.getString("firstName");
                greetingHeading.setText(greetingHeading.getText() + " " + lastName + ", " + firstName);
            }

        } catch (Exception e) {

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        greetingHeading = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        switchUserItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jMenuItem2.setText("jMenuItem2");

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MAIN FRAME");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        greetingHeading.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        greetingHeading.setText("Welcome");

        jMenu1.setText("File");

        switchUserItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        switchUserItem.setText("Switch User");
        switchUserItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchUserItemActionPerformed(evt);
            }
        });
        jMenu1.add(switchUserItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(greetingHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(greetingHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(367, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void switchUserItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchUserItemActionPerformed
        SwitchInformationDialog confirmSwitch = new SwitchInformationDialog(null, true, this);
        confirmSwitch.setVisible(true);
    }//GEN-LAST:event_switchUserItemActionPerformed

    /* public static void main(String args[]) {

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

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm(this.userId).setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel greetingHeading;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem switchUserItem;
    // End of variables declaration//GEN-END:variables
    static int userId;
}
