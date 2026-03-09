import java.util.*;
/**
 * Clasa care gestioneaza instantele problemei (hartile).
 */
public class BestRouteProblem {
    private List<Location> locations=new ArrayList<>();
    private List<Road> roads=new ArrayList<>();

    /** Adauga o locatie daca nu este duplicata. */
    public void addLocation(Location location){
        if(!locations.contains(location)){
            locations.add(location);
        }else{
            System.out.println("Location " + location.getName() + " already exists");
        }
    }

    /** Adauga un drum daca nu este duplicat. */
    public void addRoad(Road road){
        if(!roads.contains(road)){
            roads.add(road);
        }else{
            System.out.println("This road already exists");
        }
    }

    /** Verifica daca harta are minim 2 locatii si daca drumurile sunt valide. */
    public boolean isValid(){
        if(locations.size()<2) return false;
        for(Road road : roads){
            if(!locations.contains(road.getA()) || !locations.contains(road.getB()))
                return false;
        }
        return true;
    }

    /** Algoritm BFS pentru a gasi daca exista drum intre start si destinatie. */
    public boolean isPossible(Location start,Location dest){
        if(!locations.contains(start) || !locations.contains(dest))
            return false;
        Queue<Location> queue=new LinkedList<>();
        List<Location> visited = new ArrayList<>();
        queue.add(start);
        visited.add(start);

        while(!queue.isEmpty()){
            Location curr=queue.poll();
            if(curr.equals(dest))
                return true;
            for(Road road: roads){
                Location nextloc=null;
                if(road.getA().equals((curr))){
                    nextloc=road.getB();
                }else if(road.getB().equals(curr)){
                    nextloc=road.getA();
                }

                if(nextloc!=null && !visited.contains(nextloc)){
                    visited.add(nextloc);
                    queue.add(nextloc);
                }
            }
        }
        return false;
    }

    /** Afiseaza detaliile problemei. */
    public void print(){
        System.out.println("Problem Locations:");
        for(Location loc : locations)
            System.out.println("-"+ loc);

        System.out.println("Problem Roads:");
        for(Road r: roads)
            System.out.println("-"+r);
    }
}
