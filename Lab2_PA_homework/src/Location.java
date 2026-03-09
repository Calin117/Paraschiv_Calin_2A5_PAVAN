import java.util.Objects;
/**
 * Clasa abstracta si sealed pentru o locatie de pe harta
 */
public abstract sealed class Location permits City, Airport, GasStation {
    private String name;
    private double x;
    private double y;
    public Location(String name,double x,double y){
        this.name=name;
        this.x=x;
        this.y=y;
    }
    public String getName(){
        return name;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Doua locatii sunt egale daca au acelasi nume si coordonate identice
     */
    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj==null || getClass()!=obj.getClass()) return false;
        Location location=(Location) obj;
        return Double.compare(location.x,x)==0 && Double.compare(location.y,y)==0 && Objects.equals(name,location.name);
    }
    @Override
    public String toString(){
        return "Location{ "+"name="+name+",x="+x+", y="+y+" }";
    }
}
