import javax.swing.*;
import java.awt.*;

public class LoginUI {
    public LoginUI(UserManager manager) {

        JFrame frame = new JFrame("User System");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5,1,10,10));
        frame.getContentPane().setBackground(new Color(30,30,60));

        JTextField user = new JTextField();
        JPasswordField pass = new JPasswordField();

        JButton login = new JButton("Login");
        JButton register = new JButton("Register");

        login.setBackground(Color.GREEN);
        register.setBackground(Color.ORANGE);

        frame.add(label("Username"));
        frame.add(user);
        frame.add(label("Password"));
        frame.add(pass);
        frame.add(login);
        frame.add(register);

        login.addActionListener(e -> {
            if(manager.login(user.getText(), new String(pass.getPassword()))) {
                frame.dispose();
                new DashboardUI(manager);
            } else JOptionPane.showMessageDialog(frame,"Invalid Login");
        });

        register.addActionListener(e -> {
            if(manager.register(user.getText(), new String(pass.getPassword())))
                JOptionPane.showMessageDialog(frame,"Registered!");
            else JOptionPane.showMessageDialog(frame,"User exists!");
        });

        frame.setVisible(true);
    }

    private JLabel label(String text){
        JLabel l = new JLabel(text);
        l.setForeground(Color.WHITE);
        return l;
    }
}