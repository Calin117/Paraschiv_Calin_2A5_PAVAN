/** Clasa care reprezinta un aeroport. */
public final class Airport extends Location{
    private int nrTerminals;

    public Airport(String name,double x, double y, int nrTerminals){
        super(name, x, y);
        this.nrTerminals=nrTerminals;
    }
    public int getNrTerminals(){ return nrTerminals;}
    public void setNrTerminals(int nrTerminals){ this.nrTerminals=nrTerminals;}

    @Override
    public String toString(){
        return "Airport{"+"name="+getName()+",nrTerminals="+nrTerminals;
    }
}
