package demo;

import java.util.List;
import java.util.Scanner;

public class PetStoreSystem {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n");
            System.out.println("Enter your choice:");
            System.out.println("1. Add new pet details");
            System.out.println("2. Update pet price and Vaccination Status");
            System.out.println("3. Delete pet details");
            System.out.println("4. View all pets");
            System.out.println("5. Count pets by category");
            System.out.println("6. Find by price");
            System.out.println("7. Find by vaccination status for pet type");
            System.out.println("8. Exit");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    int result = PetDAO.addPetDetails();
                    if (result > 0) {
                        System.out.println("\nPET ADDED SUCCESSFULLY!");
                    } else {
                        System.out.println("FAILED TO ADD PET. PLEASE TRY AGAIN.");
                    }
                    break;
                case 2:
                    boolean result1 = PetDAO.updatePetPriceAndVaccinationStatus();
                    if (result1) {
                        System.out.println("UPDATE IS DONE!");
                    } else {
                        System.out.println("UPDATE IS FAILED!");
                    }
                    break;
                case 3:
                    int r = PetDAO.deletePetDetails();
                    if (r > 0) {
                        System.out.println("PET DELETED SUCCESSFULLY!");
                    } else {
                        System.out.println("FAILED TO DELETE PET");
                    }
                    break;
                case 4:
                     
                	List<Pet> l = PetDAO.showAllPets();
                	if(l.isEmpty())
                	{
                		System.out.println("THERE ARE NO PETS IN THE TABLE.");
                	}
                    break;
                case 5:
                    int count = 0;
                    count = PetDAO.countPetsByCategory();
                    if(count==0)
                	{
                		System.out.println("THERE IS NO PET IN THAT CATEGORY.");
                	}
                    break;
                case 6:
                	
                	List<Pet>  result11=PetDAO.searchByPrice();
                	if(result11.isEmpty())
                	{
                		System.out.println("THERE IS NO PET IN THAT RANGE.");
                	}
                	
                    break;
                case 7:
                	
                    int c=PetDAO.countByVaccinationStatusForPetType();
                	if(c==0)
                	{
                		System.out.println("THERE IS NO PET IN THAT VACCINATED IN THAT TYPE.");
                	}
                    
                    break;
                case 8:
                    System.out.println("EXITING THE PROGRAM.");
                    break;
                default:
                    System.out.println("INVALID CHOIC PLEASE TRY AGAIN.");
            }
        } while (choice != 8);
        sc.close(); // Close the scanner
    }
}
