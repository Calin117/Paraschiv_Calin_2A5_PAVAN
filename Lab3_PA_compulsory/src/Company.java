/**
 * Clasa Company implementeaza interfetele Profile si Comparable.
 */
public class Company implements Profile, Comparable<Company> {
    private String id;
    private String name;

    public Company(String id, String name) {
        this.id = id;
        this.name = name;
    }
    //Metodele obligatorii din interfata Profile
    @Override
    public String getId() {
        return id;
    }
    @Override
    public String getName() {
        return name;
    }
    //Metoda obligatorie din interfata Comparable(sortare)
    @Override
    public int compareTo(Company other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Company{id=" + id + ", name=" + name + "}";
    }
}
