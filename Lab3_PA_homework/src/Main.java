import java.time.LocalDate;

public class Main{
    public static void main(String[] args){
        SocialNetwork network=new SocialNetwork();

        Company google=new Company("C1", "Google", "Tech");
        Company adobe=new Company("C2", "Adobe", "Design Software");

        Programmer john=new Programmer("P1", "John", LocalDate.of(1995, 5, 23), "Java");
        Programmer andrei=new Programmer("P2", "Andrei", LocalDate.of(1998, 10, 15), "Python");
        Designer ana=new Designer("P3", "Ana", LocalDate.of(1999, 2, 10), "Minimalist");

        network.addProfile(google);
        network.addProfile(adobe);
        network.addProfile(john);
        network.addProfile(andrei);
        network.addProfile(ana);

        john.addRelationship(google, "Software Engineer");
        john.addRelationship(andrei, "Prieten");

        ana.addRelationship(adobe, "UI/UX Designer");
        ana.addRelationship(john, "Coleg de facultate");
        ana.addRelationship(google, "Fost angajat");

        andrei.addRelationship(john, "Prieten la catarama");

        network.printNetwork();
    }
}