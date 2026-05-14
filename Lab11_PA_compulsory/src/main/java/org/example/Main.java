package org.example;
import org.example.entity.Player;
import org.example.entity.Question;
import org.example.repository.PlayerRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GamePU");
        PlayerRepository playerRepo = new PlayerRepository(emf);

        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        playerRepo.create(p1);
        playerRepo.create(p2);

        System.out.println("Saved players. Now fetching...");
        
        Player fetched = playerRepo.findById(p1.getId());
        if (fetched != null) {
            System.out.println("Fetched player: " + fetched.getName());
        }

        emf.close();
        System.out.println("JPA Test completed successfully.");
    }
}
