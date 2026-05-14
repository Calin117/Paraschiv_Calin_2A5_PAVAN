package org.example.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
    
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;
    
    @Column(nullable = false)
    private int score;

    public Result() {}
    public Result(Game game, Player player, int score) {
        this.game = game;
        this.player = player;
        this.score = score;
    }

    public Long getId() { return id; }
    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }
    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}
