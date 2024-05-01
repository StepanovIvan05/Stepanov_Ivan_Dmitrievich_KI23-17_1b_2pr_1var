import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The `Main` class contains the main method to execute the program. It provides a menu-driven
 * interface to interact with the `Person` class. Users can add persons, edit their information,
 * display all people, sort people by different fields, calculate weight to normal for a person, and
 * exit the program.
 */
public class Main {
  private static final Scanner scanner = new Scanner(System.in);
  private static ArrayList<Person> people = new ArrayList<>();
  private static final Logger logger = Logger.getLogger(Main.class.getName());

  public static void main(String[] args) {
    ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    String fileName = "people.dat";
    boolean isBeginning = false;
    boolean isRunning = true;
    while (isRunning) {
      System.out.println(
        """
        Menu:
        1. Load data from file
        2. Save data in file
        3. Add Empty person
        4. Add person with data
        5. Edit person's field
        6. Display all people
        7. Sort people by...
        8. Weight to normal
        9. Filter by field
        10. Removing duplicates from an array
        11. Total growth of objects
        12. Age statistics when grouped by gender
        13. Exit
        Enter your choice:\s"""
      );
      try {
        int index;
        String choice = scanner.nextLine();
        Person person;
        if (!isBeginning) {
          person = new Person("Vasya", 50, 15, "male", 155, true, people.size());
          people.add(person);
          person = new Person("Anna", 60, 25, "female", 165, false, people.size());
          people.add(person);
          person = new Person("John", 70, 35, "male", 175, true, people.size());
          people.add(person);
          person = new Person("Olga", 55, 45, "female", 160, false, people.size());
          people.add(person);
          person = new Person("Alex", 80, 55, "male", 180, true, people.size());
          people.add(person);
          person = new Person("Maria", 65, 65, "female", 170, false, people.size());
          people.add(person);
          person = new Person("Peter", 90, 75, "male", 185, true, people.size());
          people.add(person);
          isBeginning = true;
        }
        switch (choice) {
          case "1":
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
              people = (ArrayList<Person>) ois.readObject();
              System.out.println("The list of people has been successfully uploaded from the file:");
            } catch (IOException | ClassNotFoundException e) {
              System.err.println("Error loading the list from a file: " + e.getMessage());
            }
            break;
          case "2":
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
              oos.writeObject(people);
              System.out.println("The list of people has been successfully saved to a file.");
            } catch (IOException e) {
              System.err.println("Error saving the list to a file: " + e.getMessage());
            }
            break;
          case "3":
            people.add(new Person(people.size()));
            System.out.println("Empty person has been entered");
            break;
          case "4":
            double weight, height;
            int age;
            try {
              System.out.print("Enter name: ");
              String name = scanner.nextLine();

              System.out.print("Enter weight: ");
              weight = scanner.nextDouble();

              System.out.print("Enter age: ");
              age = scanner.nextInt();
              scanner.nextLine();

              System.out.print("Enter gender(male, female): ");
              String gender = scanner.nextLine();

              System.out.print("Enter height: ");
              height = scanner.nextDouble();

              System.out.print("Enter white or not(true, false): ");
              boolean whiteOrNot = scanner.nextBoolean();

              person = new Person(name, weight, age, gender, height, whiteOrNot, people.size());
              people.add(person);
            } catch (EmptyStringException | NegativeValueException e) {
              throw new RuntimeException("Error when input parameters", e);
            } catch (AssertionError e) {
              throw new RuntimeException("Error when input gender");
            } finally{
              scanner.nextLine();
            }
            System.out.println("Values have been entered");
            break;
          case "5":
            System.out.print("Enter index of person to edit: ");
            index = scanner.nextInt();
            scanner.nextLine();
            if (index >= 0 && index < people.size()) {
              person = people.get(index);
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
                    person.setIsWhite(scanner.nextBoolean());
                    break;
                  default:
                    System.out.println("Invalid field!");
                }
              } catch (EmptyStringException | NegativeValueException e) {
                throw new RuntimeException("Error when correcting parameters", e);
              } catch (AssertionError e) {
                throw new RuntimeException("Error when correcting gender");
              }
              System.out.println("Value was correcting");
            } else {
              System.out.println("Invalid index!");
            }
            break;
          case "6":
            if (people.isEmpty()) {
              System.out.println("No people");
              break;
            }
            Stream<Person> listStream = people.stream();
            listStream.forEach(System.out::println);
            listStream.close();
            break;
          case "7":
            if (people.isEmpty()) {
              System.out.println("No people");
              break;
            }
            System.out.print("Enter field to sort by (name/weight/age/gender/height/isWhite): ");
            String field = scanner.nextLine();
            switch (field) {
              case "name":
                people.sort(Comparator.comparing(Person::getName));
                setIndexes();
                System.out.println("People sorted by name:");
                break;
              case "weight":
                people.sort(Comparator.comparing(Person::getWeight));
                setIndexes();
                System.out.println("People sorted by weight:");
                break;
              case "age":
                people.sort(Comparator.comparing(Person::getAge));
                setIndexes();
                System.out.println("People sorted by age:");
                break;
              case "gender":
                people.sort(Comparator.comparing(Person::getGender));
                setIndexes();
                System.out.println("People sorted by gender:");
                break;
              case "height":
                people.sort(Comparator.comparingDouble(Person::getHeight));
                setIndexes();
                System.out.println("People sorted by height:");
                break;
              case "isWhite":
                people.sort(Comparator.comparing(Person::getIsWhite));
                setIndexes();
                System.out.println("People sorted by white or not");
                break;
              default:
                System.out.println("Invalid field!");
            }
            break;
          case "8":
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
          case "9":
            personFilter();
            scanner.nextLine();
            break;
          case "10":
            people = (ArrayList<Person>) people.stream().distinct().collect(Collectors.toList());
            setIndexes();
            people.forEach(System.out::println);
            System.out.println("Duplicates have been deleted");
            break;
          case "11":
            sumHeight();
            break;
          case "12":
            createGroup();
            break;
          case "13":
            isRunning = false;
            break;
          default:
            System.out.println("Invalid choice!");
        }
      } catch (InputMismatchException e) {
        logger.severe("Error when input: " + e.getMessage());
        scanner.nextLine();
      } catch (RuntimeException e) {
        System.out.println(getExceptionMessageChain(e));
      } catch (EmptyStringException | NegativeValueException e) {

      }
    }
    scanner.close();
  }

  public static void setIndexes(){
    for(int i = 0; i < people.size(); i++){
      people.get(i).setIndex(i);
    }
  }

  /** The method for filtering by the selected field */
  public static void personFilter() {
    System.out.println(
      """
      Select the field to filter:
      1. Age
      2. Weight
      """);
    String choice = scanner.nextLine();
    Stream<Person> personList = people.stream();
    int value;
    switch (choice) {
      case "1":
        System.out.println("Enter the lower age limit");
        value = scanner.nextInt();
        Stream<Person> filterPerson = personList.filter(p -> p.getAge() >= value);
        filterPerson.forEach(System.out::println);
        break;
      case "2":
        System.out.println("Enter the lower weight limit");
        value = scanner.nextInt();
        Stream<Person> filterList = personList.filter(p -> p.getWeight() >= value);
        filterList.forEach(System.out::println);
        break;
      default:
        System.out.println("Incorrect choice");
    }
  }

  /** The method calculates the sum of all the growth of all people and outputs it to the console */
  public static void sumHeight() {
    Stream<Person> listPerson = people.stream();
    OptionalDouble result = listPerson.mapToDouble(Person::getHeight).reduce(Double::sum);
    if (result.isPresent()) {
      System.out.println("The total growth of all people: " + result.getAsDouble());
    } else {
      System.out.println("No people");
    }
    listPerson.close();
  }

  /** The method groups the data by gender and outputs the average age values */
  public static void createGroup() {
    Map<String, IntSummaryStatistics> summaryStatisticsByGender =
            people.stream()
                    .collect(
                            Collectors.groupingBy(
                                    Person::getGender, Collectors.summarizingInt(Person::getAge)));

    summaryStatisticsByGender.forEach(
            (gender, stats) -> {
              System.out.println("Gender " + gender + ":");
              System.out.println("Number of people: " + stats.getCount());
              System.out.println("Average age: " + (int) stats.getAverage());
              System.out.println("Total age: " + stats.getSum());
              System.out.println("Min age: " + stats.getMin());
              System.out.println("Max age: " + stats.getMax());
            });
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
