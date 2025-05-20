import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://experiment.mysql.database.azure.com:3306/registry-java";
        String user = "experimental";
        String password = "dev@4322";
        Connection connection = DriverManager.getConnection(url, user, password);

        if(connection != null) {
            System.out.println("Connected to the database!");
            Statement statement = connection.createStatement();

            // Create tables
            String group_sql = "CREATE TABLE IF NOT EXISTS teams (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255))";
            String participant_sql = "CREATE TABLE IF NOT EXISTS participants (pid INT AUTO_INCREMENT PRIMARY KEY, p_name VARCHAR(255), p_age INT, p_email VARCHAR(255), team_id INT, FOREIGN KEY (team_id) REFERENCES teams(id))";
            String facilitators_sql = "CREATE TABLE IF NOT EXISTS facilitators (fid INT AUTO_INCREMENT PRIMARY KEY, f_name VARCHAR(255), f_email VARCHAR(255), team_id INT, FOREIGN KEY (team_id) REFERENCES teams(id))";

            //Insert sample data
            //String insert_group_sql = "INSERT INTO teams (name) VALUES ('Team C')";
            //String insert_participant_sql = "INSERT INTO participants (p_name, p_age, p_email, team_id) VALUES ('John Doe', 25, 'johndone@gmail.com', 1), ('Jane Smith', 30, 'janeSmith@hotmail.com', 2)";
            //String insert_facilitators_sql = "INSERT INTO facilitators (f_name, f_email, team_id) VALUES ('Alice Brown', 'aliceB@gmail.com', 1), ('Bob White', 'bobWhite@hotmail.com', 2)";	

            //Delete records
            //String delete_group_sql = "DELETE FROM teams WHERE id = 3 or id = 4";


            statement.executeUpdate(group_sql);
            statement.executeUpdate(participant_sql);
            statement.executeUpdate(facilitators_sql);

            System.out.println("Tables created successfully!");

            //statement.executeUpdate(insert_group_sql);
            //statement.executeUpdate(insert_participant_sql);
            //statement.executeUpdate(insert_facilitators_sql);

            System.out.println("Sample data inserted successfully!");

            //statement.executeUpdate(delete_group_sql);
            System.out.println("Records deleted successfully!");

            String update_sql = "UPDATE teams SET name = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update_sql);
            preparedStatement.setString(1, "Team D");
            preparedStatement.setInt(2, 5);
            preparedStatement.executeUpdate();
            System.out.println("Records updated successfully!");

            // Close the statement and connection
            statement.close();
            connection.close();
        } else {
            System.out.println("Failed to connect to the database.");
        }
        
    }
}
