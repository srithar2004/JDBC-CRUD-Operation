package MyPackage;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.sun.jdi.connect.spi.Connection;

//import java.sql.*;

public class DataBase {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		String url ="jdbc:mysql://localhost:3306/Employee";
		String Name ="root";
		String Password ="Srithar@2004";
		
		Connection con = DriverManager.getConnection(url,Name,Password);
		Scanner s = new Scanner(System.in);
		
		while(true) {
			System.out.println("======JDBC CRUD OPERATION======");
			System.out.println("1. Insert the Employee");
			System.out.println("2. Read the Employee");
			System.out.println("3. Update the Employee");
			System.out.println("4. Delete the Employee");
			System.out.println("5. Exit");
			
			System.out.println("Enter the Choice : ");
			int choice =s.nextInt();
			
			switch(choice) {
			case 1:
				System.out.println("Enter the ID: ");
				int ID =s.nextInt();
				
				System.out.println("Enter the Name: ");
				String name = s.next();
				
				System.out.println("Enter the Salary of the Employee: ");
				int Sal =s.nextInt();
				
				String query = "insert into EmployeeDetails values(?,?,?)";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, ID);
				ps.setString(2,name);
				ps.setInt(3, Sal);
				
				ps.executeUpdate();
				System.out.println("Employee Inserted!!");
				break;
				
			case 2:
				String q = "select * from EmployeeDetails";
				
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(q);
				
				while(rs.next()) {
					System.out.println(rs.getInt(1));
					System.out.println(rs.getString(2));
					System.out.println(rs.getInt(3));
				}
				
				break;
			case 3:
				String UpdateQuery = "Update EmployeeDetails set salary3 =? where id =?";
				
				System.out.println("Enter the Updated Salary: ");
				int NewSalary =s.nextInt();
				
				System.out.println("Enter the Id: ");
				int NId=s.nextInt();
				
				PreparedStatement PS = con.prepareStatement(UpdateQuery);
				
				PS.setInt(1, NewSalary);
				PS.setInt(2,NId);
				
				PS.executeUpdate();
				break;
			case 4:
				String DeleteQuery = "delete from EmployeeDetails where id =?";
				
				System.out.println("Enter the Id to delete: ");
				
				int iD =s.nextInt();
				
				PreparedStatement Ps = con.prepareStatement(DeleteQuery);
				Ps.setInt(1, iD);
				
				Ps.executeUpdate();
				
				System.out.println("Id is Deleted Successfully !!");
				break;
			
			case 5:
				con.close();
				s.close();
				
				System.out.println("GoodBye");
				return;
			
			default:
				System.out.println("Invalid Choice");
			}
			
			
		}
		
		
	}

}
