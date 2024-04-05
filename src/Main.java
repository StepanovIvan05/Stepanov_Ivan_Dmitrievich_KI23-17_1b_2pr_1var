import java.util.*;
import java.util.logging.*;

/**
 * The `Main` class contains the main method to execute the program. It provides a menu-driven
 * interface to interact with the `Person` class. Users can add persons, edit their information,
 * display all people, sort people by different fields, calculate weight to normal for a person, and
 * exit the program.
 */
public class Main {
  private static final Logger logger = Logger.getLogger(Main.class.getName());

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Person> people = new ArrayList<>();
    ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);

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
      try {
        int index = 0;
        int choice = scanner.nextInt();
        Person person;

        switch (choice) {
          case 1:
            people.add(new Person());
            break;
          case 2:
            double weight, height;
            int age;
            scanner.nextLine(); // Consume newline
            person = new Person();

            try {
              System.out.print("Enter name: ");
              String name = scanner.nextLine();
              person.setName(name);

              System.out.print("Enter weight: ");
              weight = scanner.nextDouble();
              person.setWeight(weight);

              System.out.print("Enter age: ");
              age = scanner.nextInt();
              scanner.nextLine();
              person.setAge(age);

              System.out.print("Enter gender(male, female): ");
              String gender = scanner.nextLine();
              person.setGender(gender);

              System.out.print("Enter height: ");
              height = scanner.nextDouble();
              person.setHeight(height);

              System.out.print("Enter white or not(true, false): ");
              boolean whiteOrNot = scanner.nextBoolean();
              person.setWhiteOrNot(whiteOrNot);

              people.add(person);
            } catch (EmptyStringException | NegativeValueException e) {
              throw new RuntimeException("Error when input parameters", e);
            } catch (AssertionError e) {
              throw new RuntimeException("Error when input gender");
            }
            System.out.println("Values have been entered");
            break;
          case 3:
            System.out.print("Enter index of person to edit: ");
            index = scanner.nextInt();
            if (index >= 0 && index < people.size()) {
              person = people.get(index);
              scanner.nextLine(); // Consume newline
              System.out.print("Enter field to edit (name/weight/age/gender/height/whiteOrNot): ");
              String field = scanner.nextLine();
              try {
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
                    System.out.print("Enter new gender(male, female): ");
                    person.setGender(scanner.nextLine());
                    break;
                  case "height":
                    System.out.print("Enter new height: ");
                    person.setHeight(scanner.nextDouble());
                    break;
                  case "whiteOrNot":
                    System.out.print("Enter new white or not(true, false): ");
                    person.setWhiteOrNot(scanner.nextBoolean());
                    break;
                  default:
                    System.out.println("Invalid field!");
                }
              } catch (EmptyStringException | NegativeValueException e) {
                throw new RuntimeException("Error when correcting parameters", e);
              } catch (AssertionError e) {
                throw new RuntimeException("Error when correcting gender");
              }
            } else {
              System.out.println("Invalid index!");
            }
            System.out.println("Value was correcting");
            break;
          case 4:
            if (people.isEmpty()) {
              System.out.println("Not people");
              break;
            }
            for (Person persona : people) {
              persona.displayInfo(index);
              System.out.println();
              index++;
            }
            break;
          case 5:
            System.out.print("Enter field to sort by (name/weight/age/gender/height/whiteOrNot): ");
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
              case "whiteOrNot":
                people.sort(Comparator.comparing(Person::getWhiteOrNot));
                System.out.println("People sorted by white or not");
                break;
              default:
                System.out.println("Invalid field!");
            }
            break;
          case 6:
            System.out.print("Enter index of person to know weight to normal: ");
            index = scanner.nextInt();
            if (index >= 0 && index < people.size()) {
              person = people.get(index);
              scanner.nextLine(); // Consume newline
              System.out.println(person.weightToNormal());
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
      } catch (InputMismatchException e) {
        logger.severe("Error when input: " + e.getMessage());
        scanner.nextLine();
        System.out.println("Invalid value");
      } catch (RuntimeException e) {
        System.out.println(getExceptionMessageChain(e));
      }
    }
    scanner.close();
  }

  /**
   * Retrieves the exception message chain from the given throwable and its causes.
   *
   * @param throwable the exception from which to retrieve the message chain
   * @return a list of strings containing the exception messages from the chain
   */
  public static List<String> getExceptionMessageChain(Throwable throwable) {
    List<String> result = new ArrayList<>();
    while (throwable != null) {
      result.add(throwable.getMessage()); // Add the error message to the list
      throwable = throwable.getCause(); // Get the cause of the next exception in the chain
    }
    return result;
  }
}
