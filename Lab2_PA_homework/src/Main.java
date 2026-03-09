/** Clasa principala de testare. */
public class Main{
    public static void main(String[] args){

        City bucuresti = new City("Bucuresti", 50.0, 50.0, 2000000);
        Airport otopeni = new Airport("Otopeni", 48.0, 52.0, 3);
        GasStation petrom = new GasStation("Petrom A1", 30.0, 30.0, 7.5);
        City pitesti = new City("Pitesti", 20.0, 20.0, 150000);

        Road a1 = new Road(RoadType.autostrada, 120, 130, bucuresti, pitesti);
        Road dn1 = new Road(RoadType.expres, 15, 100, bucuresti, otopeni);

        BestRouteProblem problem = new BestRouteProblem();
        problem.addLocation(bucuresti);
        problem.addLocation(otopeni);
        problem.addLocation(pitesti);
        problem.addLocation(petrom);

        // Testam duplicatele
        problem.addLocation(new City("Bucuresti", 50.0, 50.0, 2000000));

        problem.addRoad(a1);
        problem.addRoad(dn1);

        problem.print();
        System.out.println("\nIs problem valid? " + problem.isValid());
        System.out.println("Is Otopeni reachable from Pitesti? " + problem.isPossible(pitesti, otopeni));
        System.out.println("Is Otopeni reachable from Petrom? " + problem.isPossible(petrom, otopeni));
    }
}