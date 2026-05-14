package org.example.repository;
import org.example.entity.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class PlayerRepository extends AbstractRepository<Player, Long> {
    public PlayerRepository(EntityManagerFactory emf) {
        super(emf, Player.class);
    }

    public List<Player> findByName(String name) {
        EntityManager em = emf.createEntityManager();
        List<Player> players = em.createQuery("SELECT p FROM Player p WHERE p.name = :name", Player.class)
                                 .setParameter("name", name)
                                 .getResultList();
        em.close();
        return players;
    }
}
