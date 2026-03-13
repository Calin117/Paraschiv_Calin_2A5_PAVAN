import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Main{
    public static void main(String[] args){
        //Cream lista ce accepta toate clasele ce implementeaza Profile
        List<Profile> relatii= new ArrayList<>();

        //Adaugam Persoane si Companii in lista
        relatii.add(new Person("P1","Tomas"));
        relatii.add(new Company("C1","Apple"));
        relatii.add(new Person("P2","Andrei"));
        relatii.add(new Company("C2","Microsoft"));

        System.out.println("Lista inainte de sortare");
        for(Profile p: relatii){
            System.out.println(p);
        }

        //Sortam lista folosing Comparatorul in functie de nume
        relatii.sort(Comparator.comparing(Profile::getName));

        System.out.println("\nLista dupa sortare cu Comparator");
        for(Profile p: relatii){
            System.out.println(p);
        }
    }
}