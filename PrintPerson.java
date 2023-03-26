package CourseProject.XMLParser;

public class PrintPerson {

    public String printPerson(Person person) {
        return("ID: " + person.getID() + " Gender: " + person.getGender() + " Age: " + person.getAge() +
                "\n\tName: " + person.getName() +
                "\n\tAddress: " + person.getAddress() +
                "\n\tPosition: " + person.getPosition());
    }

}
