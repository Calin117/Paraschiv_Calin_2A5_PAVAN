public class Road {
    private String type;
    private double length;
    private int speedlim;

    public Road(String type,double length,int speedlimit){
        this.type=type;
        this.speedlim=speedlimit;
        this.length=length;
    }
    public String getType(){
        return type;
    }
    public double getLength(){
        return length;
    }
    public int getSpeedlim(){
        return speedlim;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setLength(double length) {
        this.length = length;
    }
    public void setSpeedLimit(int speedlim) {
        this.speedlim = speedlim;
    }

    @Override
    public String toString(){
        return "Road{ "+"type="+type+", length="+length+", speedlim="+speedlim+" }";
    }
}
