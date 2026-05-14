package org.example.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "player")
    private List<Result> results;

    public Player() {}
    public Player(String name) { this.name = name; }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Result> getResults() { return results; }
    public void setResults(List<Result> results) { this.results = results; }
}
