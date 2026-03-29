import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DashboardUI {

    public DashboardUI(UserManager manager) {
        JFrame frame = new JFrame("Dashboard");
        frame.setSize(500, 300);

        DefaultTableModel model = new DefaultTableModel(new String[]{"Users"},0);

        for(User u: manager.getUsers())
            model.addRow(new Object[]{u.getUsername()});

        JTable table = new JTable(model);
        JButton delete = new JButton("Delete Selected");

        delete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row != -1){
                String user = model.getValueAt(row,0).toString();
                manager.deleteUser(user);
                model.removeRow(row);
            }
        });

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(delete, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}