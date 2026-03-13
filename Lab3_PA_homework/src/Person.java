import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Clasa Person implementeaza interfetele Profile si Comparable.
 */
public class Person implements Profile, Comparable<Person> {
    private String id;
    private String name;
    private LocalDate dataNastere;

    private Map<Profile,String> relationships;
    public Person(String id,String name,LocalDate dataNastere){
        this.id=id;
        this.name=name;
        this.dataNastere=dataNastere;
        this.relationships=new HashMap<>();
    }
    //Adauga o relatie in harta;
    public void addRelationship(Profile profile, String type){
        relationships.put(profile,type);
    }

    public Map<Profile,String> getRelationship(){
        return relationships;
    }

    //Metodele obligatorii din interfata Profile
    @Override
    public String getId(){
        return id;
    }

    @Override
    public String getName(){
        return name;
    }
    //Metoda obligatorie din interfata Comparable(sortare)
    @Override
    public int compareTo(Person other){
        return this.name.compareTo(other.name);
    }
    @Override
    public String toString(){
        return "Person{name=" + name + ", dataNastere=" + dataNastere + "}";}
}


