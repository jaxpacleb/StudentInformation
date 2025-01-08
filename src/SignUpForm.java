
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.sql.ResultSet;
import javax.swing.JToolTip;
import javax.swing.ToolTipManager;

public class SignUpForm extends javax.swing.JDialog {

    /**
     * Creates new form SignUpForm
     */
    public SignUpForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        ImageIcon bgImg = new ImageIcon("C:\\Users\\acer\\Downloads\\filter-bg.jpg");
        Image getImg = bgImg.getImage();
        Image scaleImg = getImg.getScaledInstance(768, 633, Image.SCALE_SMOOTH);

        ImageIcon scaledImg1 = new ImageIcon(scaleImg);

        JLabel bg = new JLabel(scaledImg1);
        setContentPane(bg);
        ImageIcon bgIcon = new ImageIcon("C:\\Users\\acer\\Downloads\\new-account-icon-24.jpg");
        Image img = bgIcon.getImage();
        Image scaledImg2 = img.getScaledInstance(128, 128, Image.SCALE_SMOOTH);
        setIconImage(scaledImg2);

        initComponents();

        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //NO IMPLEMENTATION
            }

            @Override
            public void keyPressed(KeyEvent e) {
                Object source = e.getSource();

                if (source == fNameField && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    midNameField.requestFocus();
                } else if (source == midNameField && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    lNameField.requestFocus();
                } else if (source == lNameField && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    contactNumField.requestFocus();
                } else if (source == contactNumField && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    emailField.requestFocus();
                } else if (source == emailField && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    usernameField.requestFocus();
                } else if (source == usernameField && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    passwordField.requestFocus();
                } else if (source == passwordField && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    confirmPassField.requestFocus();
                } else if (source == confirmPassField && e.getKeyCode() == KeyEvent.VK_ENTER) {

                    SignUpForm sign = new SignUpForm(null, false);
                    sign.dispose();

                    SuccessAccCreationDialog success = new SuccessAccCreationDialog(null, true);
                    success.setVisible(true);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                //NO IMPLEMENTATION
            }

        };

        DocumentListener docuListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateInput();

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateInput();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateInput();

            }

            private void validateInput() {
                String firstNameContent = fNameField.getText();
                String middleNameContent = midNameField.getText();
                String lastNameContent = lNameField.getText();
                String contactNumContent = contactNumField.getText();
                String emailContent = emailField.getText();
                String usernameContent = usernameField.getText();
                String passwordContent = passwordField.getText();
                String confirmPassContent = confirmPassField.getText();

                ImageIcon checkIcon = new ImageIcon("C:\\Users\\acer\\Downloads\\check.png");
                Image checkImg = checkIcon.getImage();
                Image scaledImg1 = checkImg.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                ImageIcon validIcon = new ImageIcon(scaledImg1);

                ImageIcon errorIcon = new ImageIcon("C:\\Users\\acer\\Downloads\\delete.png");
                Image errorImg = errorIcon.getImage();
                Image scaledImg2 = errorImg.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                ImageIcon invalidIcon = new ImageIcon(scaledImg2);

                if (firstNameContent.isBlank()) {
                    fNameField.setBorder(invalidBorder);
                } else {
                    fNameField.setBorder(defaultBorder);
                }
                if (middleNameContent.isBlank()) {
                    midNameField.setBorder(invalidBorder);
                } else {
                    midNameField.setBorder(defaultBorder);
                }
                if (lastNameContent.isBlank()) {
                    lNameField.setBorder(invalidBorder);
                } else {
                    lNameField.setBorder(defaultBorder);
                }

                if (emailContent.isBlank() || !emailContent.contains("@gmail.com")) {
                    emailField.setBorder(invalidBorder);
                } else {
                    emailField.setBorder(defaultBorder);
                }

                if (passwordContent.isBlank()) {
                    passwordField.setBorder(invalidBorder);
                } else {
                    passwordField.setBorder(defaultBorder);
                }
                if (confirmPassContent.isBlank()) {
                    confirmPassField.setBorder(invalidBorder);
                } else {
                    confirmPassField.setBorder(defaultBorder);
                }

                if (passwordContent.isBlank()) {
                    checkBox1.setEnabled(false);
                } else {
                    checkBox1.setEnabled(true);
                    checkBox1.setForeground(Color.black);
                }
                if (confirmPassContent.isBlank()) {
                    checkBox2.setEnabled(false);
                } else {
                    checkBox2.setEnabled(true);
                    checkBox2.setForeground(Color.black);
                }

                try {

                    con = DriverManager.getConnection(url, username, password);

                    // Combined query to check both contact number and username
                    String query = "SELECT Contact_Number, Username FROM logincredentials WHERE Contact_Number = ? OR Username = ?";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, contactNumContent);
                    ps.setString(2, usernameContent);

                    ResultSet rs = ps.executeQuery();

                    boolean contactExists = false;
                    boolean usernameExists = false;

                    while (rs.next()) {
                        // Check for contact number match
                        returnedContactNumber = rs.getString("Contact_Number");
                        returnedUsername = rs.getString("Username");
                        if (returnedContactNumber != null && returnedContactNumber.equals(contactNumContent)) {
                            contactExists = true;
                        }

                        // Check for username match
                        if (returnedUsername != null && returnedUsername.equals(usernameContent)) {
                            usernameExists = true;
                        }

                        // If both are found, we can stop early
                        if (contactExists && usernameExists) {
                            break;
                        }

                    }

                    boolean contactIsInvalid = contactValidator(contactNumContent, returnedContactNumber);
                    boolean usernameIsInvalid = usernameValidator(usernameContent, returnedUsername);

                    if (contactIsInvalid || contactNumContent.isBlank()) {
                        contactNumField.setBorder(invalidBorder);
                    } else if (!contactIsInvalid) {
                        contactNumField.setBorder(defaultBorder);
                    }

                    if (usernameIsInvalid || usernameContent.isBlank()) {
                        usernameField.setBorder(invalidBorder);
                    } else if (!usernameIsInvalid) {
                        usernameField.setBorder(defaultBorder);
                    }

                    JToolTip toolTip = new JToolTip();
                    toolTip.setFont(new Font("Arial", Font.BOLD, 14));

                    // Set icons and indicators based on the results
                    contactNumIndicator.setIcon(contactExists ? invalidIcon : (contactNumContent.isBlank()
                            || !contactNumContent.matches("\\d+") || contactNumContent.length() != 11 || returnedContactNumber != null ? null : validIcon));
                    contactNumIndicator.setEnabled(true);

                    usernameIndicator.setIcon(usernameExists ? invalidIcon : (usernameContent.isBlank() || returnedUsername != null ? null : validIcon));
                    usernameIndicator.setEnabled(true);

                    //The succeeding codes below is for checking if the username and contact number is already exist in the database.
                    //Fix this method....
                    validateContactNumAvailability(contactExists);
                    validateUsernameAvailability(usernameExists);

                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        fNameField.addKeyListener(listener);
        lNameField.addKeyListener(listener);
        midNameField.addKeyListener(listener);
        contactNumField.addKeyListener(listener);
        emailField.addKeyListener(listener);
        usernameField.addKeyListener(listener);
        passwordField.addKeyListener(listener);
        confirmPassField.addKeyListener(listener);

        fNameField.getDocument().addDocumentListener(docuListener);
        lNameField.getDocument().addDocumentListener(docuListener);
        midNameField.getDocument().addDocumentListener(docuListener);
        contactNumField.getDocument().addDocumentListener(docuListener);
        emailField.getDocument().addDocumentListener(docuListener);
        usernameField.getDocument().addDocumentListener(docuListener);
        passwordField.getDocument().addDocumentListener(docuListener);
        confirmPassField.getDocument().addDocumentListener(docuListener);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        jSeparator2 = new javax.swing.JSeparator();
        fNameField = new javax.swing.JTextField();
        lNameField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        confirmPassField = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        createAccBtn = new javax.swing.JButton();
        midNameField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        contactNumField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        checkBox2 = new javax.swing.JCheckBox();
        checkBox1 = new javax.swing.JCheckBox();
        usernameIndicator = new javax.swing.JLabel();
        contactNumIndicator = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CREATE ACCOUNT");
        setBounds(new java.awt.Rectangle(380, 80, 0, 0));
        setModal(true);
        setResizable(false);

        fNameField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fNameField.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5))));

        lNameField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lNameField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        lNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lNameFieldActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Hello student! Please create your account.");

        confirmPassField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        confirmPassField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        confirmPassField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPassFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("First Name:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Last Name:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Email:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Username:");

        usernameField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usernameField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });

        createAccBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        createAccBtn.setText("CREATE ACCOUNT");
        createAccBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAccBtnActionPerformed(evt);
            }
        });

        midNameField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        midNameField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        midNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                midNameFieldActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Middle Name:");

        passwordField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        passwordField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Password:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Confirm Password:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Contact Number:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("CREATE ACCOUNT");

        contactNumField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        contactNumField.setPreferredSize(new java.awt.Dimension(64, 15));

        emailField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        checkBox2.setText("Show password");
        checkBox2.setEnabled(false);
        checkBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox2ActionPerformed(evt);
            }
        });

        checkBox1.setText("Show password");
        checkBox1.setEnabled(false);
        checkBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox1ActionPerformed(evt);
            }
        });

        usernameIndicator.setEnabled(false);

        contactNumIndicator.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(passwordField)
                                .addComponent(jLabel9)
                                .addComponent(lNameField)
                                .addComponent(fNameField)
                                .addComponent(emailField, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
                            .addComponent(checkBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(createAccBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)
                                .addComponent(jLabel11)
                                .addComponent(jLabel10)
                                .addComponent(midNameField)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(contactNumField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(usernameField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(usernameIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(contactNumIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(checkBox2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(confirmPassField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(midNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contactNumField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contactNumIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(emailField, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBox2)
                    .addComponent(checkBox1))
                .addGap(28, 28, 28)
                .addComponent(createAccBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmPassFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPassFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmPassFieldActionPerformed

    private void lNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lNameFieldActionPerformed

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    //this  method displays tooltip text when the icon beside the contact number field is hovered.
    private void validateContactNumAvailability(boolean contactExists) {
        String contactContent = contactNumField.getText();

        //if the contact number is already exist in the database, it tell the user that the contac number
        //is already exist.
        if (contactExists) {
            contactNumIndicator.setToolTipText("This contact number is already taken.");
        } //if the contact number is not yet exist, the tooltip text will be displayed and says that 
        //the contact number is available.
        else if (!contactExists) {
            contactNumIndicator.setToolTipText("This contact number is available.");
        } //the tooltip text display nothing if the contact field is blank or empty.
        else if (contactContent.isBlank()) {
            contactNumIndicator.setToolTipText(null);
        }
    }

    //this method also displays tooltip text when the icon beside the username field is hovered.
    private void validateUsernameAvailability(boolean usernameExists) {
        String usernameContent = usernameField.getText();

        //if the username is already exist it also display a tooltip text where the username is already taken.
        if (usernameExists) {
            usernameIndicator.setToolTipText("This username is already taken.");
        } //if the username is not already exist from the database then it tell to the user that the username is available.
        else if (!usernameExists) {
            usernameIndicator.setToolTipText("This username is available.");
        } //if the username field is empty or blank it will display nothing.
        else if (usernameContent.isBlank()) {
            usernameIndicator.setToolTipText(null);
        }
    }

    //this method will be called  and returns a true value if one of the conditon is met, otherwise it returns false.
    private boolean contactValidator(String contactNumContent, String returnedContactNumber) {

        //the conditon will be check if it not contains a single alphabet, not a blank or empty field, not exceeds or lower than 11 digit,
        //or the contact number field is same as the retrieve value from the database.
        return contactNumContent.isBlank() || !contactNumContent.matches("\\d+") ||  returnedContactNumber == null || (!contactNumContent.isBlank() && (contactNumContent.length() != 11 || contactNumContent.length() > 11
                || returnedContactNumber.equals(contactNumContent)));
    }

    //this also returns a value 
    private boolean usernameValidator(String usernameContent, String returnedUsername) {
        return (usernameContent.isBlank() || returnedUsername.equals(usernameContent) || returnedUsername == null);

    }
    private void createAccBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createAccBtnActionPerformed
        String firstNameContent = fNameField.getText();
        String middleNameContent = midNameField.getText();
        String lastNameContent = lNameField.getText();
        String contactNumContent = contactNumField.getText();
        String emailContent = emailField.getText();
        String usernameContent = usernameField.getText();
        String passwordContent = passwordField.getText();
        String confirmPassContent = confirmPassField.getText();

        if (!firstNameContent.isBlank() && !middleNameContent.isBlank() && !lastNameContent.isBlank() && !contactNumContent.isBlank()
                && !emailContent.isBlank() && !usernameContent.isBlank() && !passwordContent.isBlank() && !confirmPassContent.isBlank()) {

            //this stores true boolean value if the contact number of the user have a non-digit value or its contact number is not equal to 11,
            //otherwise, its false.
            boolean invalidContactNumAndLength = !contactNumContent.matches("\\d+") || contactNumContent.length() != 11 || contactNumContent.length() > 11;

            //this stores a true boolean value if the email of the user contains a string of "@gmail.com", otherwise it stores false value.
            boolean invalidEmail = !emailContent.contains("@gmail.com");

            //this also stores a true boolean value if the entered password of the user from password field and confirm password field does not match,
            //otherwise, it stores false value.
            boolean unsamePassword = !passwordContent.equals(confirmPassContent);

            //the condition below will be check depending of what values stores from the tree variables above, each conditions will displayed
            //invalid dialog if condition becomes true, otherwise it automatically execute the succeeding code inside of the else block.
            if (invalidContactNumAndLength) {
                InvalidContactNumber invalidContactNumDialog = new InvalidContactNumber(null, true);
                invalidContactNumDialog.setLocationRelativeTo(null);
                invalidContactNumDialog.setVisible(true);
            } else if (invalidEmail) {
                InvalidEmailDialog emailDialog = new InvalidEmailDialog(null, true);
                emailDialog.setLocationRelativeTo(null);
                emailDialog.setVisible(true);
            } else if (unsamePassword) {
                PasswordNotMatch passNotMatchDialog = new PasswordNotMatch(null, true);
                passNotMatchDialog.setLocationRelativeTo(null);
                passNotMatchDialog.setVisible(true);
            } else if (returnedUsername.equals(usernameContent)) {
                usernameUnavailableDialog unavailableUsername = new usernameUnavailableDialog(null, true);
                unavailableUsername.setLocationRelativeTo(null);
                unavailableUsername.setVisible(true);
            } else if (returnedContactNumber.equals(contactNumContent)) {
                contactNumUnavailableDialog unavailableContact = new contactNumUnavailableDialog(null, true);
                unavailableContact.setLocationRelativeTo(null);
                unavailableContact.setVisible(true);
            } else {

                try {
                    //this establish a connection of our database which have a parameter of our database url, username, and password.
                    con = DriverManager.getConnection(url, username, password);

                    //this is a query for inserting a data in our database table. this is a parametized query for preventing SQL Injection that could be made
                    //by a hackers. The question marks symbols in the query serves as the placeholder of values to be inserted in database table.
                    String query = "INSERT INTO logincredentials (first_name, middle_name, last_name, email, contact_number, username, password, user_type)"
                            + " VALUES (?, ?, ?, ?, ?, ?, ?)";

                    //this will be prepares the query.
                    PreparedStatement ps = con.prepareStatement(query);

                    //the text fields value will be get and trimmed and sets as the value of each placeholder from the query above.
                    //the number of placeholder should have the same number of value to be inserted in the database table.
                    ps.setString(1, firstNameContent.trim());
                    ps.setString(2, middleNameContent.trim());
                    ps.setString(3, lastNameContent.trim());
                    ps.setString(4, emailContent.trim());
                    ps.setString(5, contactNumContent.trim());
                    ps.setString(6, usernameContent.trim());
                    ps.setString(7, confirmPassContent.trim());
                    ps.setString(8, "Student");

                    int row = ps.executeUpdate();

                    //this prints that the data is successfully inserted in the table, it also shows how many row affected after the insertion.
                    System.out.print("Data successfully inserted. " + row + " row/s affected.");

                    //this dialog will automatically close after the insertion.
                    this.dispose();

                    //this shows a dialog for the user that his account is successfully created.
                    SuccessAccCreationDialog succAccCre = new SuccessAccCreationDialog(null, true);
                    succAccCre.setLocationRelativeTo(null);
                    succAccCre.setVisible(true);
                } catch (SQLException ex) {
                    System.err.print(ex.getMessage());
                }

            }
        } else {
            EmptyTextFieldsDialog dialog = new EmptyTextFieldsDialog(null, true);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }


    }//GEN-LAST:event_createAccBtnActionPerformed


    private void midNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_midNameFieldActionPerformed

    }//GEN-LAST:event_midNameFieldActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

//This method show and unshow the password of the user from the confirm password field.
    private void checkBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox2ActionPerformed

        //if the checkbox is selected it shows the plain text password to the user,
        //if its unselected then it shows the asterisk characters which it depends the length of the user password.
        if (checkBox2.isSelected()) {
            confirmPassField.setEchoChar((char) 0);
        } else {
            confirmPassField.setEchoChar('*');
        }
    }//GEN-LAST:event_checkBox2ActionPerformed

    //This method show and unshow the password of the user from the password field.
    private void checkBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox1ActionPerformed

        //if the checkbox is selected it shows the plain text password to the user,
        //if its unselected then it shows the asterisk characters which it depends the length of the user password.
        if (checkBox1.isSelected()) {
            passwordField.setEchoChar((char) 0);
        } else {
            passwordField.setEchoChar('*');
        }
    }//GEN-LAST:event_checkBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(SignUpForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUpForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUpForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUpForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SignUpForm dialog = new SignUpForm(new javax.swing.JFrame(), true);
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

    private Border invalidBorder = BorderFactory.createLineBorder(Color.red, 3, true);
    private Border defaultBorder = BorderFactory.createLineBorder(Color.black, 3, true);

    static String returnedContactNumber;
    static String returnedUsername;
    private Connection con;
    private String url = "jdbc:mysql://localhost:3306/enrollmentsystem";
    private String username = "root";
    private String password = "Gwapsako@2004";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBox1;
    private javax.swing.JCheckBox checkBox2;
    private javax.swing.JPasswordField confirmPassField;
    private javax.swing.JTextField contactNumField;
    private javax.swing.JLabel contactNumIndicator;
    private javax.swing.JButton createAccBtn;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField fNameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField lNameField;
    private javax.swing.JTextField midNameField;
    private javax.swing.JPasswordField passwordField;
    public javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameIndicator;
    // End of variables declaration//GEN-END:variables
}
