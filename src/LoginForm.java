import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    public JPanel loginPanel;
    private JTextField textUser;
    private JPasswordField passwordPSW;
    private JButton btnLogin;

    public LoginForm(JFrame frame) {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = textUser.getText();
                String contrasena = new String(passwordPSW.getPassword());

                if (usuario.equals("admin") && contrasena.equals("1234")) {
                    JOptionPane.showMessageDialog(null, "Login exitoso");
                    frame.setContentPane(new MainForm().mainPanel); // Cambiar al MainForm
                    frame.revalidate(); // Refrescar el JFrame
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });
    }
}
