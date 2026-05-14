package org.example.entity;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date gameDate;

    @OneToMany(mappedBy = "game")
    private List<Result> results;

    public Game() {
        this.gameDate = new Date();
    }

    public Long getId() { return id; }
    public Date getGameDate() { return gameDate; }
    public void setGameDate(Date gameDate) { this.gameDate = gameDate; }
    public List<Result> getResults() { return results; }
    public void setResults(List<Result> results) { this.results = results; }
}
