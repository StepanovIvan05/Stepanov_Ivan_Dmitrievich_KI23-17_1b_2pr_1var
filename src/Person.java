import java.io.Serializable;
import java.util.Objects;

/**
 * The `Person` class represents a basic model of a person with attributes like name, weight,
 * height, age, gender, isWhite. It provides constructors, getters, and setters for these
 * attributes, along with a method to display information about the person. Additionally, it
 * includes a method to calculate the difference between the person's current weight and the ideal
 * weight based on height, age, and gender.
 */
class Person implements Serializable {
  private int index;
  private String name;
  private double weight;
  private double height;
  private int age;
  private String gender;
  private boolean isWhite;

  /**
   * Default constructor for the Person class.
   *
   * @param index the index of the person
   */
  public Person(int index) {
    try {
      setIndex(index);
      setName("Noname");
      setWeight(0.0);
      setAge(0);
      setGender("male");
      setHeight(0.0);
      setIsWhite(true);
    } catch (Exception e) {
    }
  }

  /**
   * Constructor with parameters for the Person class.
   *
   * @param name the name of the person
   * @param weight the weight of the person
   * @param age the age of the person
   * @param gender the gender of the person
   * @param height the height of the person
   * @param isWhite whether the person is white or not
   * @param index the index of the person
   * @throws EmptyStringException if the name is empty
   * @throws NegativeValueException if weight, age, or height is negative
   */
  public Person(
      String name, double weight, int age, String gender, double height, boolean isWhite, int index)
      throws EmptyStringException, NegativeValueException {
    setIndex(index);
    setName(name);
    setWeight(weight);
    setAge(age);
    setGender(gender);
    setHeight(height);
    setIsWhite(isWhite);
  }

  // Getters and setters for all fields
  public String getName() {
    return name;
  }

  public void setName(String name) throws EmptyStringException{
    if (Objects.equals(name, "")) {
      throw new EmptyStringException("Empty data");
    }
    this.name = name;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) throws NegativeValueException {
    if (weight < 0) {
      throw new NegativeValueException("The value is less than zero");
    }
    this.weight = weight;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) throws NegativeValueException {
    if (age < 0) {
      throw new NegativeValueException("The value is less than zero");
    }
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    assert (Objects.equals(gender, "male") || Objects.equals(gender, "female"));
    this.gender = gender;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) throws NegativeValueException {
    if (height < 0) {
      throw new NegativeValueException("The value is less than zero");
    }
    this.height = height;
  }

  public boolean getIsWhite() {
    return isWhite;
  }

  public void setIsWhite(boolean isWhite) {
    this.isWhite = isWhite;
  }

  public void setIndex(int index) {
    this.index = index;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return age == person.age
        && Objects.equals(name, person.name)
        && weight == person.weight
        && height == person.height
        && Objects.equals(gender, person.gender)
        && isWhite == person.isWhite;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, age, weight, height, gender, isWhite);
  }

  @Override
  public String toString() {
    return "Index: "
        + index
        + "\n"
        + "Name: "
        + name
        + "\n"
        + "Weight: "
        + weight
        + "\n"
        + "Age: "
        + age
        + "\n"
        + "Gender: "
        + gender
        + "\n"
        + "Height: "
        + height
        + "\n"
        + "Is white?: "
        + isWhite
        + "\n";
  }

  /**
   * Method to calculate the difference between the person's current weight and the ideal weight
   * based on height, age, and gender. Ideal weight is calculated using different formulas for males
   * and females.
   *
   * @return the difference between the current weight and the ideal weight
   */
  public double weightToNormal() {
    double weight;
    if (Objects.equals(this.gender, "male")) {
      weight = 50 + (this.height - 150) * 0.72 + (double) (this.age - 21) / 4;
    } else {
      weight = 50 + (this.height - 150) * 0.36 + (double) (this.age - 21) / 5;
    }
    return this.weight - weight;
  }
}
