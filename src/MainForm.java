import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainForm {
    private JButton btn_Datos;
    private JTable table;
    public JPanel mainPanel;

    public MainForm() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Autor");
        model.addColumn("Título");
        model.addColumn("Edición");
        model.addColumn("Copyright");
        table.setModel(model);

        btn_Datos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = DatabaseConnection.getConnection();
                try {
                    String query = "SELECT CONCAT(a.FirstName, ' ', a.LastName) AS Autor, " +
                            "t.Title, t.EditionNumber, t.Copyright " +
                            "FROM Authors a " +
                            "JOIN AuthorISBN ai ON a.AuthorID = ai.AuthorID " +
                            "JOIN Titles t ON ai.ISBN = t.ISBN";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    // Limpia la tabla antes de cargar nuevos datos
                    model.setRowCount(0);

                    // Agrega los datos obtenidos al modelo de la tabla
                    while (resultSet.next()) {
                        model.addRow(new Object[]{
                                resultSet.getString("Autor"),
                                resultSet.getString("Title"),
                                resultSet.getInt("EditionNumber"),
                                resultSet.getString("Copyright")
                        });
                    }

                    JOptionPane.showMessageDialog(null, "Datos cargados exitosamente");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + ex.getMessage());
                    ex.printStackTrace();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close(); // Cierra la conexión al final
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
