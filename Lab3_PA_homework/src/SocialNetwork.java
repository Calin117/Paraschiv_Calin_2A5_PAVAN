import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class SocialNetwork {
    private List<Profile> profiles=new ArrayList<>();

    public void addProfile(Profile p){
        if(!profiles.contains(p)){
            profiles.add(p);
        }
    }
    /**
     * Calculeaza importanta: numarul relatiilor initiate de profil + de cate ori apare el la altii
     */
    public int getImportance(Profile profile){
        int importance=0;
        if(profile instanceof Person)
            importance+=((Person)profile).getRelationship().size();

        for(Profile p:profiles){
            if(p instanceof Person && p!=profile){
                Person person=(Person) p;
                if(person.getRelationship().containsKey(profile))
                    importance++;
            }
        }
        return importance;
    }

    public void printNetwork(){
        profiles.sort(Comparator.comparingInt(this::getImportance).reversed());

        System.out.println("Social Network:");
        for(Profile p : profiles){
            System.out.println("Importanta "+getImportance(p)+" | "+p.toString());
        }
    }
}
