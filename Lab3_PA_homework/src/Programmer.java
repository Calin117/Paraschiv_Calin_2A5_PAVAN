import java.time.LocalDate;

public class Programmer extends Person{
    private String favLanguage;

    public Programmer(String id, String name, LocalDate dataNastere,String favLanguage){
        super(id,name,dataNastere);
        this.favLanguage=favLanguage;
    }
    @Override
    public String toString() {
        return "Programmer{name=" + getName() + ", language=" + favLanguage + "}";
    }
}
