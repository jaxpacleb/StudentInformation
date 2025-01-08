
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LogIn extends javax.swing.JDialog {

    /**
     * Creates new form LogIn
     */
    public LogIn(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
     
        
        ImageIcon bgImgPath = new ImageIcon("C:\\Users\\acer\\Downloads\\black_bg.png");
        Image getImg = bgImgPath.getImage();
        Image scaledImg = getImg.getScaledInstance(428, 405, Image.SCALE_SMOOTH);
        ImageIcon newImg = new ImageIcon(scaledImg);

        JLabel background = new JLabel(newImg);
        setContentPane(background);

        ImageIcon loginIcon = new ImageIcon("C:\\Users\\acer\\Downloads\\login-images-png-4.png");
        Image getIconImg = loginIcon.getImage();
        Image scaledIcon = getIconImg.getScaledInstance(120, 120, Image.SCALE_SMOOTH);

        setIconImage(scaledIcon);
        initComponents();

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                Object source = e.getSource();

                if (source == usernameField && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    passwordField.requestFocus();
                } else if (source == passwordField && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!passwordField.getText().isBlank() && !usernameField.getText().isBlank()) {
                        loginBtn.doClick();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        };

        usernameField.addKeyListener(keyListener);
        passwordField.addKeyListener(keyListener);
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginLabel = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        passwordLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        recoverPasswordBtn = new javax.swing.JButton();
        loginBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        signUpBtn = new javax.swing.JButton();
        subHeadLabel = new javax.swing.JLabel();
        checkBoxPass = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LOG IN ACCOUNT");
        setBounds(new java.awt.Rectangle(550, 150, 0, 0));
        setResizable(false);

        loginLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        loginLabel.setForeground(new java.awt.Color(255, 255, 255));
        loginLabel.setText("LOG IN YOUR ACCOUNT");

        usernameField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usernameField.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5))));

        passwordField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        passwordField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        passwordLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(255, 255, 255));
        passwordLabel.setText("ENTER YOUR PASSWORD");

        usernameLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setText("ENTER YOUR USERNAME");

        recoverPasswordBtn.setText("Recover my account");
        recoverPasswordBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recoverPasswordBtnActionPerformed(evt);
            }
        });

        loginBtn.setText("LOG IN");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        signUpBtn.setText("SIGN UP");
        signUpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpBtnActionPerformed(evt);
            }
        });

        subHeadLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        subHeadLabel.setForeground(new java.awt.Color(255, 255, 255));
        subHeadLabel.setText("Login your Existing Credential");

        checkBoxPass.setForeground(new java.awt.Color(255, 255, 255));
        checkBoxPass.setText("Show password");
        checkBoxPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkBoxPass)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(usernameField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(usernameLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passwordLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(loginLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(subHeadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(32, 32, 32))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(signUpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(recoverPasswordBtn)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(loginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subHeadLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(usernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkBoxPass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signUpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(recoverPasswordBtn)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void recoverPasswordBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recoverPasswordBtnActionPerformed

    }//GEN-LAST:event_recoverPasswordBtnActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed

        
        try {

            con = DriverManager.getConnection(url,  username, password);
            String query = "SELECT * FROM userinformation WHERE userName = ? AND password = ?";

            ps = con.prepareStatement(query);
            ps.setString(1, usernameField.getText().trim());
            ps.setString(2, Integer.toHexString(passwordField.getText().hashCode()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("userId");
                String type = rs.getString("userType");

                if (type.equals("Administrator")) {
                    // JTable table = AdminFrame.getTable();
                    DefaultTableModel tableModel = new DefaultTableModel();

                    //table.setModel(tableModel);
                    AdminFrame adminFrame = new AdminFrame(userId);
                    //  tableModel.addRow(new Object[]{userID, userType);

                    adminFrame.setVisible(true);
                } else {
                    StudentFrame studFrame = new StudentFrame(userId);
                    studFrame.setVisible(true);
                }
            } else {
                JOptionPane.showConfirmDialog(null, "Account not yet registered or Incorrect username and/or password",
                        "LOGIN UNSUCCESSFUL", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {

        };

        this.dispose();
    }//GEN-LAST:event_loginBtnActionPerformed

    private void signUpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpBtnActionPerformed
        SignUpForm signUp = new SignUpForm(null, true);
        signUp.setVisible(true);

    }//GEN-LAST:event_signUpBtnActionPerformed

    private void checkBoxPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxPassActionPerformed
        if (checkBoxPass.isSelected()) {
            passwordField.setEchoChar((char) 0);
        } else {
            passwordField.setEchoChar('*');
        }
    }//GEN-LAST:event_checkBoxPassActionPerformed

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
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LogIn dialog = new LogIn(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private Connection con;
    private PreparedStatement ps;
    private String url = "jdbc:mysql://localhost:3306/students";
    private String username = "root";
    private String password = "Gwapsako@2004";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBoxPass;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton loginBtn;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton recoverPasswordBtn;
    private javax.swing.JButton signUpBtn;
    private javax.swing.JLabel subHeadLabel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
