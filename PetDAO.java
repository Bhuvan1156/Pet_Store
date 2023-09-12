package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetDAO {
    private static Scanner sc = new Scanner(System.in);
    

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl.corp.amdocs.com", "SYSTEM",
                    "Amdocs123"); 
        } catch (Exception e) {
            System.out.println("Error in connection");
        }
        return con;
    }

    public static int addPetDetails() {
        System.out.println("Enter pet details:");
        System.out.println("if the detailes are of wrong Data Type then the Program will EXIT!");
        System.out.print("(string)Category: ");
        String category = sc.nextLine();
        try 
        {
            if (!isAlphabetic(category)) 
            {
                throw new InvalidAlphabetException("Input contains non-alphabetic characters.");
            }
            
        } catch (InvalidAlphabetException e) 
        {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
        System.out.print("(string)Type: ");
        String type = sc.nextLine();
        try 
        {
            if (!isAlphabetic(type)) 
            {
                throw new InvalidAlphabetException("Input contains non-alphabetic characters.");
            }
            // Process the input string here if it contains only alphabetic characters
        } catch (InvalidAlphabetException e) 
        {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
        System.out.print("(string)Color: ");
        String color = sc.nextLine();
        try 
        {
            if (!isAlphabetic(color)) 
            {
                throw new InvalidAlphabetException("Input contains non-alphabetic characters.");
            }
            // Process the input string here if it contains only alphabetic characters
        } catch (InvalidAlphabetException e) 
        {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
        System.out.print("(int)Age: ");
        int age = sc.nextInt();
        System.out.print("(int)Price: ");
        double price = sc.nextDouble();
        System.out.print("(boolean)Is Vaccinated (true/false): ");
        boolean isVaccinated = sc.nextBoolean();
      
        sc.nextLine();
        System.out.print("Food Habits: ");
        String foodHabits = sc.nextLine();

        int rowsAffected = 0;
        try {
            String sql = "INSERT INTO Pets (petCategory, petType, color, age, price, isVaccinated, foodHabits) VALUES(?, ?, ?, ?, ?, ?, ?)";
            try (Connection con = getConnection();
                    PreparedStatement s = con.prepareStatement(sql)) {
                s.setString(1, category);
                s.setString(2, type);
                s.setString(3, color);
                s.setInt(4, age);
                s.setDouble(5, price);
                s.setBoolean(6, isVaccinated);
                s.setString(7, foodHabits);

                rowsAffected = s.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public static List<Pet> showAllPets() {
        List<Pet> petList = new ArrayList<>();
        String sql = "SELECT * FROM pets";
        try (Connection con = getConnection(); PreparedStatement preparedStatement = con.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Pet p = new Pet();
                p.setPetID(resultSet.getInt(1));
                p.setPetCategory(resultSet.getString("PETCATEGORY"));
                p.setPetType(resultSet.getString("petType"));
                p.setColor(resultSet.getString("color"));
                p.setAge(resultSet.getInt("age"));
                p.setPrice(resultSet.getDouble("price"));
                p.setVaccinated(resultSet.getBoolean("isVaccinated"));
                p.setFoodHabits(resultSet.getString("foodHabits"));
                petList.add(p);
            }

          
            for (Pet pet : petList) {
                System.out.println(pet.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return petList;
    }

    public static int deletePetDetails() {
        int rowsAffected = 0;
        System.out.println("Enter the id of the pet you want to delete:");
        int petId = sc.nextInt();
        sc.nextLine(); 

        String sql = "DELETE FROM pets WHERE PETID=?";
        try (Connection con = getConnection(); PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, petId);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public static boolean updatePetPriceAndVaccinationStatus() {
        boolean updated = false;
        System.out.println("Enter the id for which you want to update:");
        int id = sc.nextInt();
        System.out.println("Enter the new price:");
        double new_price = sc.nextDouble();
        System.out.println("Enter the new vaccination status (true/false):");
        boolean new_status = sc.nextBoolean();
        

        String sql = "UPDATE pets SET price = ?, isVaccinated = ? WHERE petid = ?";
        try (Connection con = getConnection(); PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setDouble(1, new_price);
            preparedStatement.setBoolean(2, new_status);
            preparedStatement.setInt(3, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                updated = true;
                System.out.println("Pet information updated successfully.");
            } else {
                System.out.println("No pet found with the specified ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    public static int countPetsByCategory() {
    	
        int count = 0;
        System.out.println("Enter the category by which you want to count:");
        String category = sc.nextLine();
        try 
        {
            if (!isAlphabetic(category)) 
            {
                throw new InvalidAlphabetException("Input contains non-alphabetic characters.");
            }
            
        } catch (InvalidAlphabetException e) 
        {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
    
        String sql = "SELECT COUNT(*) FROM pets WHERE petCategory=?";
        try (Connection con = getConnection(); PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, category);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
                System.out.println("Total count is: " + count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static List<Pet> searchByPrice() {
        System.out.println("Enter the minimum price:");
        double minPrice = sc.nextDouble();
        System.out.println("Enter the maximum price:");
        double maxPrice = sc.nextDouble();
        sc.nextLine(); 

        List<Pet> result = new ArrayList<>();
        String sql = "SELECT * FROM pets WHERE price >= ? AND price <= ?";
        try (Connection con = getConnection(); PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Pet p = new Pet();
                p.setPetCategory(resultSet.getString("PETCATEGORY"));
                p.setPetType(resultSet.getString("petType"));
                p.setColor(resultSet.getString("color"));
                p.setAge(resultSet.getInt("age"));
                p.setPrice(resultSet.getDouble("price"));
                p.setVaccinated(resultSet.getBoolean("isVaccinated"));
                p.setFoodHabits(resultSet.getString("foodHabits"));
                result.add(p);
            }
            
            for (Pet pet : result) {
                System.out.println(pet.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int countByVaccinationStatusForPetType() 
    {
        System.out.println("Enter the pet type:");
        String petType = sc.nextLine();
        try 
        {
            if (!isAlphabetic(petType)) 
            {
                throw new InvalidAlphabetException("Input contains non-alphabetic characters.");
            }
            
        } catch (InvalidAlphabetException e) 
        {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
        int count = 0;
        String sql = "SELECT COUNT(isVaccinated) FROM pets WHERE petType = ?";
        try (Connection con = getConnection(); PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, petType);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
                System.out.println("Total count for pet type " + petType + " and vaccination status True is: " + count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return count;
    }
    public static boolean isAlphabetic(String input) {
        return input.matches("^[a-zA-Z]+$");
    }
    
}
