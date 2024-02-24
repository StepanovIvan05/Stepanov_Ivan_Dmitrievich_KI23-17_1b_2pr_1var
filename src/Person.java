import java.util.Objects;

/**
 * The `Person` class represents a basic model of a person with attributes like name, weight,
 * height, age, and gender. It provides constructors, getters, and setters for these attributes,
 * along with a method to display information about the person. Additionally, it includes a method
 * to calculate the difference between the person's current weight and the ideal weight based on
 * height, age, and gender.
 */
class Person {
  private String name;
  private double weight;
  private double height;
  private int age;
  private String gender;

  // Default constructor
  public Person() {
    this.name = "";
    this.weight = 0.0;
    this.age = 0;
    this.gender = "male";
    this.height = 0.0;
  }

  // Parameterized constructor
  public Person(String name, double weight, int age, String gender, double height) {
    this.name = name;
    this.weight = weight;
    this.age = age;
    this.gender = gender;
    this.height = height;
  }

  // Getters and setters for all fields
  public String getName() {
    return name;
  }

  public void setName(String name) throws CustomException {
    if (Objects.equals(name, "")) {
      throw new CustomException("Empty data");
    }
    this.name = name;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) throws CustomException {
    if (weight < 0) {
      throw new CustomException("Invalid value");
    }
    this.weight = weight;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) throws CustomException {
    if (age < 0) {
      throw new CustomException("Invalid value");
    }
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) throws CustomException {
    if (!Objects.equals(gender, "male") && !Objects.equals(gender, "female")) {
      throw new CustomException("Invalid value");
    }
    this.gender = gender;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) throws CustomException {
    if (height < 0) {
      throw new CustomException("Invalid value");
    }
    this.height = height;
  }

  // Method to display information about the person
  public void displayInfo(int index) {
    System.out.println("Index: " + index);
    System.out.println("Name: " + name);
    System.out.println("Weight: " + weight);
    System.out.println("Age: " + age);
    System.out.println("Gender: " + gender);
    System.out.println("Height: " + height);
  }

  /**
   * Method to calculate the difference between the person's current weight and the ideal weight
   * based on height, age, and gender. Ideal weight is calculated using different formulas for males
   * and females.
   *
   * @return the difference between the current weight and the ideal weight
   */
  public double weight_to_normal() {
    double weight;
    if (Objects.equals(this.gender, "male")) {
      weight = 50 + (this.height - 150) * 0.72 + (double) (this.age - 21) / 4;
    } else {
      weight = 50 + (this.height - 150) * 0.36 + (double) (this.age - 21) / 5;
    }
    return this.weight - weight;
  }
}
