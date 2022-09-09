import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            //https://jdbc.postgresql.org/documentation/use/ is de link die ik gebruik heb gemaakt om de
            //connectie vast te stellen
            String url = "jdbc:postgresql://localhost/ovchip";
            //we maken gebruik van Properties
            Properties props = new Properties();

            //inloggegevens
            props.setProperty("user", "postgres");
            props.setProperty("password", "password");

            Connection conn = DriverManager.getConnection(url, props);

            Statement statement = conn.createStatement();
            //selecteer alles van reiziger
            ResultSet resultSet = statement.executeQuery("SELECT * FROM reiziger");

            //netheid van de output
            System.out.println("Alle reizigers:");
            //zolang er een volgende gebruiker is
            while (resultSet.next()) {
                //ga de tabel langs en vraag de volgende gegevens op
                String reizigerId = resultSet.getString("reiziger_id");
                String voorletters = resultSet.getString("voorletters");
                String tussenvoegsel = resultSet.getString("tussenvoegsel");
                String achternaam = resultSet.getString("achternaam");
                String geboortedatum = resultSet.getString("geboortedatum");
                //check of er wel of niet een tussenvoegsel is, String.format wordt gebruikt om het netjes uit te printen
                if (tussenvoegsel == null) {
                    System.out.println(String.format("#%s: %s. %s (%s)", reizigerId, voorletters, achternaam, geboortedatum));
                } else {
                    System.out.println(String.format("#%s: %s. %s %s (%s)", reizigerId, voorletters, tussenvoegsel, achternaam, geboortedatum));
                }
            }
            //we sluiten de connectie dan ook keurig af
            resultSet.close();
            statement.close();
        }catch(SQLException exc){
            System.out.println("SQLException, " + exc);
        }
    }
}