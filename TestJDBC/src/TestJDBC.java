import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJDBC {

    public static void main(String[] args) throws Exception {

        // 1) Charger le driver PostgreSQL (si tu utilises le .jar)
        Class.forName("org.postgresql.Driver");

        // 2) Ouvrir la connexion vers ta base PostgreSQL
        //    dbname = jdbc_tp, host = localhost, port = 5432
        Connection cx = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/JanaHaddad_database",
                "postgres",
                "passwordJana22"
        );

        // 3) Créer un Statement
        Statement st = cx.createStatement();

        // 4) Exécuter une requête sur TA table
        ResultSet rs = st.executeQuery("SELECT matricule, nom, prenom, age\n" +
                "FROM software_students\n" +
                "WHERE age >= 21;\n");

        // 5) Lire les résultats
        while (rs.next()) {
            int matricule = rs.getInt("matricule");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            int age = rs.getInt("age"); // si age est NULL => 0

            System.out.println(
                    "Matricule: " + matricule + " | " +
                            prenom + " " + nom + " a " + age + " ans"
            );
        }

        // 6) Fermer
        rs.close();
        st.close();
        cx.close();
    }
}