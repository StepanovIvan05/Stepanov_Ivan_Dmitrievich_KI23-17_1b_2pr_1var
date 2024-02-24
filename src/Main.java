import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

/**
 * The `Main` class contains the main method to execute the program. It provides a menu-driven
 * interface to interact with the `Person` class. Users can add persons, edit their information,
 * display all people, sort people by different fields, calculate weight to normal for a person, and
 * exit the program.
 */
public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Person> people = new ArrayList<>();

    boolean isRunning = true;
    while (isRunning) {
      System.out.println("\nMenu:");
      System.out.println("1. Add empty person");
      System.out.println("2. Add person with data");
      System.out.println("3. Edit person's field");
      System.out.println("4. Display all people");
      System.out.println("5. Sort people by...");
      System.out.println("6. Weight to normal");
      System.out.println("7. Exit");
      System.out.print("Enter your choice: ");
      boolean incorrect = false;
      try {
        int index = 0;
        int choice = scanner.nextInt();

        switch (choice) {
          case 1:
            people.add(new Person());
            break;
          case 2:
            double weight, height;
            int age;
            scanner.nextLine(); // Consume newline

            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            if (Objects.equals(name, "")) {
              incorrect = true;
              throw new Exception();
            }

            System.out.print("Enter weight: ");
            weight = scanner.nextDouble();
            if (weight <= 0) {
              incorrect = true;
              throw new Exception();
            }

            System.out.print("Enter age: ");
            age = scanner.nextInt();
            scanner.nextLine();
            if (age < 0) {
              incorrect = true;
              throw new Exception();
            }

            System.out.print("Enter gender(male, female): ");
            String gender = scanner.nextLine();
            if (!Objects.equals(gender, "male") && !Objects.equals(gender, "female")) {
              incorrect = true;
              throw new Exception();
            }

            System.out.print("Enter height: ");
            height = scanner.nextDouble();
            if (height <= 0) {
              incorrect = true;
              throw new Exception();
            }

            people.add(new Person(name, weight, age, gender, height));
            break;
          case 3:
            System.out.print("Enter index of person to edit: ");
            index = scanner.nextInt();
            if (index >= 0 && index < people.size()) {
              Person person = people.get(index);
              scanner.nextLine(); // Consume newline
              System.out.print("Enter field to edit (name/weight/age/gender/height): ");
              String field = scanner.nextLine();
              switch (field) {
                case "name":
                  System.out.print("Enter new name: ");
                  person.setName(scanner.nextLine());
                  break;
                case "weight":
                  System.out.print("Enter new weight: ");
                  person.setWeight(scanner.nextDouble());
                  break;
                case "age":
                  System.out.print("Enter new age: ");
                  person.setAge(scanner.nextInt());
                  break;
                case "gender":
                  System.out.print("Enter new gender: ");
                  person.setGender(scanner.nextLine());
                  break;
                case "height":
                  System.out.print("Enter new height: ");
                  person.setHeight(scanner.nextDouble());
                  break;
                default:
                  System.out.println("Invalid field!");
              }
            } else {
              System.out.println("Invalid index!");
            }
            break;
          case 4:
            for (Person person : people) {
              person.displayInfo(index);
              System.out.println();
              index++;
            }
            break;
          case 5:
            System.out.print("Enter field to sort by (name/weight/age/gender/height): ");
            scanner.nextLine();
            String field = scanner.nextLine();
            switch (field) {
              case "name":
                people.sort(Comparator.comparing(Person::getName));
                System.out.println("People sorted by name:");
                break;
              case "weight":
                people.sort(Comparator.comparing(Person::getWeight));
                System.out.println("People sorted by weight:");
                break;
              case "age":
                people.sort(Comparator.comparing(Person::getAge));
                System.out.println("People sorted by age:");
                break;
              case "gender":
                people.sort(Comparator.comparing(Person::getGender));
                System.out.println("People sorted by gender:");
                break;
              case "height":
                people.sort(Comparator.comparingDouble(Person::getHeight));
                System.out.println("People sorted by height:");
                break;
              default:
                System.out.println("Invalid field!");
            }
            break;
          case 6:
            System.out.print("Enter index of person to know weight to normal: ");
            index = scanner.nextInt();
            if (index >= 0 && index < people.size()) {
              Person person = people.get(index);
              scanner.nextLine(); // Consume newline
              System.out.println(person.weight_to_normal());
            } else {
              System.out.println("Invalid index!");
            }
            break;
          case 7:
            isRunning = false;
            break;
          default:
            System.out.println("Invalid choice!");
        }
      } catch (CustomException e) {
        System.out.println(e.getMessage());
      } catch (Exception e) {
        if (incorrect) {
          System.out.println("Invalid value");
        } else {
          scanner.nextLine();
          System.out.println("Error input");
        }
      }
    }
    scanner.close();
  }
}
