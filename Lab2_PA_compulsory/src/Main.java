public class Main{
    public static void main(String[] args){

        Location bucuresti = new Location("Bucuresti", "City", 50.0, 50.0);
        Location otopeni = new Location("Otopeni", "Airport", 48.0, 52.0);
        System.out.println(bucuresti);
        System.out.println(otopeni);

        Road dn1 = new Road("Autostrada",100,120);
        System.out.println(dn1);
    }
}