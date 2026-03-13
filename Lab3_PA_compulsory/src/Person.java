/**
 * Clasa Person implementeaza interfetele Profile si Comparable.
 */
public class Person implements Profile, Comparable<Person> {
    private String id;
    private String name;

    public Person(String id,String name){
        this.id=id;
        this.name=name;
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
        return "Person{id=" + id + ", name=" + name + "}";
    }
}


