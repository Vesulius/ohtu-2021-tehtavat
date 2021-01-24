/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vesalouh
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void rightAmountPlayersInTeam() {
        assertEquals(3, this.stats.team("EDM").size());
    }
    
    @Test
    public void knowsTopPlayer() {
        assertEquals("Gretzky", this.stats.topScorers(1).get(0).getName());
    }
    
    @Test
    public void canFindPlayer() {
        assertTrue(this.stats.search("Kurri") instanceof Player);
    }
    
    @Test
    public void cantFindPlayer() {
        assertTrue(this.stats.search("nobody") == null);
    }
}
