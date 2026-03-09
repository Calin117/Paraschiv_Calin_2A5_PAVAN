import java.util.Objects;
/**
 * Clasa care reprezinta un drum intre doua locatii.
 */
public class Road {
    private RoadType type;
    private double length;
    private int speedlim;
    private Location A;
    private Location B;

    public Road(RoadType type,double length,int speedlim,Location A,Location B){
        this.type=type;
        this.speedlim=speedlim;
        this.length=length;
        this.A=A;
        this.B=B;
    }
    public RoadType getType() { return type; }
    public double getLength() { return length; }
    public int getSpeedLim() { return speedlim; }
    public Location getA() { return A; }
    public Location getB() { return B; }
    public void setType(RoadType type) {
        this.type = type;
    }
    public void setLength(double length) {
        this.length = length;
    }
    public void setSpeedLim(int speedlim) {
        this.speedlim = speedlim;
    }

    /**
     * Un drum de la A la B este considerat egal cu un drum de la B la A.
     */
    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj==null || getClass()!=obj.getClass()) return false;
        Road road=(Road)obj;
        return (Objects.equals(A, road.A) && Objects.equals(B, road.B)) || (Objects.equals(A, road.B) && Objects.equals(B, road.A));
    }
    @Override
    public String toString(){
        return "Road{ "+"type="+type+", length="+length+", speedlim="+speedlim+" }";
    }
}
