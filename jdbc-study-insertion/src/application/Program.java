package application;

import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.sql.Date.valueOf;


public class Program {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st = null;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            conn = DB.getConnection();

            st = conn.prepareStatement("INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)");

            st.setString(1, "Carl Purple");
            st.setString(2, "carl@gmail.com");
            LocalDate dateOfBirth = LocalDate.parse("22/04/1985", fmt);
            st.setDate(3, java.sql.Date.valueOf(dateOfBirth));
            st.setDouble(4, 3000.00);
            st.setInt(5, 4);

            int rowsAffected = st.executeUpdate();

            System.out.println("Done! rows affected: " + rowsAffected);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}