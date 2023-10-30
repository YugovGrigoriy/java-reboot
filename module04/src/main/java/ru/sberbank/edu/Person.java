package ru.sberbank.edu;


public class Person implements Comparable<Person> {

    private String name;
    private String age;

    private String city;


    public Person(String name, String age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (!this.getClass().equals(obj.getClass()))
            return false;
        Person human = (Person) obj;
        return name.equalsIgnoreCase(human.name) && city.equalsIgnoreCase(human.city) && age.equals(human.age);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode() + age.toLowerCase().hashCode() + city.toLowerCase().hashCode();
    }

    @Override
    public int compareTo(Person person) {
        if (this.city.compareToIgnoreCase(person.city) == 0) {
            return this.name.compareToIgnoreCase(person.name);
        } else {
            return this.city.compareToIgnoreCase(person.city);
        }
    }
}



