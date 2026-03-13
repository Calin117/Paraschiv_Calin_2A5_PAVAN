import java.time.LocalDate;

public class Designer extends Person{
    private String designStyle;

    public Designer(String id, String name, LocalDate dataNastere,String designStyle){
        super(id,name,dataNastere);
        this.designStyle=designStyle;
    }
    @Override
    public String toString() {
        return "Designer{name=" + getName() + ", style=" + designStyle + "";
    }
}
