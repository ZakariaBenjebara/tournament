package com.nespresso.sofa.recruitement.tournament;

import com.nespresso.sofa.recruitement.tournament.fighter.Highlander;
import com.nespresso.sofa.recruitement.tournament.fighter.Swordsman;
import com.nespresso.sofa.recruitement.tournament.fighter.Viking;
import org.junit.Test;

import static com.nespresso.sofa.recruitement.tournament.Fight.*;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * This is a duel simulation
 * <p>
 * Blow exchange are sequential, A engage B means that A will give the first blow, then B will respond
 */
public class TournamentTest {

    /**
     * A Swordsman has 100 hit points and use a 1 hand sword that does 5 dmg
     * A Viking has 120 hit points and use a 1 hand axe that does 6 dmg
     */
    @Test
    public void SwordsmanVsViking() {

        final Result result = prepareFight().opponent(new Swordsman())
                                            .versus(new Viking())
                                            .begin()
                                            .result();

        assertThat(result.opponent().hitPoints()).isEqualTo(0);
        assertThat(result.versus().hitPoints()).isEqualTo(35);
        assertThat(result.opponentPrice()).isEqualTo(Result.Price.LOSER);
        assertThat(result.versusPrice()).isEqualTo(Result.Price.WINNER);
    }

    /**
     * a buckler cancel all the damages of a blow one time out of two
     * a buckler is destroyed after blocking 3 blow from an axe
     */
    @Test
    public void SwordsmanWithBucklerVsVikingWithBuckler() {

        final Result result = prepareFight().opponent(new Swordsman().equip("buckler"))
                                            .versus(new Viking().equip("buckler"))
                                            .begin()
                                            .result();

        assertThat(result.opponent().hitPoints()).isEqualTo(0);
        assertThat(result.versus().hitPoints()).isEqualTo(70);
        assertThat(result.opponentPrice()).isEqualTo(Result.Price.LOSER);
        assertThat(result.versusPrice()).isEqualTo(Result.Price.WINNER);
    }

    /**
     * a buckler cancel all the damages of a blow one time out of two
     * a buckler is destroyed after blocking 3 blow from an axe
     */
    @Test
    public void SwordsmanWithCoatVsVikingWithCoats() {

        final Result result = prepareFight().opponent(new Swordsman().equip("heavy coat"))
                                            .versus(new Viking().equip("viking coat"))
                                            .begin()
                                            .result();

        assertThat(result.opponent().hitPoints()).isEqualTo(0);
        assertThat(result.versus().hitPoints()).isEqualTo(120);
        assertThat(result.opponentPrice()).isEqualTo(Result.Price.LOSER);
        assertThat(result.versusPrice()).isEqualTo(Result.Price.WINNER);
    }

    /**
     * an Highlander as 150 hit points and fight with a Great Sword
     * a Great Sword is a two handed sword deliver 12 damages, but can attack only 2 every 3
     * an armor : reduce all received damages by 3 & reduce delivered damages by one
     */
    @Test
    public void ArmoredSwordsmanVsViking() {

        final Result result = prepareFight().opponent(new Swordsman().equip("buckler")
                                                                     .equip("armor"))
                                            .versus(new Highlander())
                                            .begin()
                                            .result();

        assertThat(result.opponent().hitPoints()).isEqualTo(0);
        assertThat(result.versus().hitPoints()).isEqualTo(10);
        assertThat(result.opponentPrice()).isEqualTo(Result.Price.LOSER);
        assertThat(result.versusPrice()).isEqualTo(Result.Price.WINNER);
    }

    /**
     * an heavy coat : reduce all received damages by 2
     */
    @Test
    public void ArmoredAndHeavyCoatSwordsmanVsViking() {

        final Result result = prepareFight().opponent(new Swordsman().equip("buckler")
                                                                     .equip("armor")
                                                                     .equip("heavy coat"))
                                            .versus(new Highlander())
                                            .begin()
                                            .result();

        assertThat(result.opponent().hitPoints()).isEqualTo(16);
        assertThat(result.versus().hitPoints()).isEqualTo(0);
        assertThat(result.opponentPrice()).isEqualTo(Result.Price.WINNER);
        assertThat(result.versusPrice()).isEqualTo(Result.Price.LOSER);
    }

    /**
     * a vicious Swordsman is a Swordsman that put poison on his weapon.
     * poison increase 20 damages on two first blows
     * a veteran Highlander goes Berserk once his hit points are under 30% of his initial total
     * once Berserk, he doubles his damages
     */
    @Test
    public void ViciousSwordsmanVsVeteranHighlander() {

        final Result result = prepareFight().opponent(new Swordsman("Vicious").equip("axe")
                                                                              .equip("buckler")
                                                                              .equip("armor"))
                                            .versus(new Highlander("Veteran"))
                                            .begin()
                                            .result();

        assertThat(result.opponent().hitPoints()).isEqualTo(1);
        assertThat(result.versus().hitPoints()).isEqualTo(0);
        assertThat(result.opponentPrice()).isEqualTo(Result.Price.WINNER);
        assertThat(result.versusPrice()).isEqualTo(Result.Price.LOSER);
    }
}
