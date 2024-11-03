import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/books?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = ""; // Cambia si tienes una contraseña

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("¡Conexión exitosa!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
