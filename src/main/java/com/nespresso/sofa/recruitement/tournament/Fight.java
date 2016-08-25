package com.nespresso.sofa.recruitement.tournament;

import com.nespresso.sofa.recruitement.tournament.fighter.AbstractFighter;

import java.util.Iterator;

import static java.util.Objects.requireNonNull;

public final class Fight {

    private final Round round;

    private Fight(Round round) {
        this.round = round;
    }

    public static Organizer prepareFight() {
        return new Organizer();
    }

    public Fight begin() {
        while (round.hasNext()) {
            return new Fight(round.next()).begin();
        }
        return this;
    }

    public Result result() {
        requireNonNull(round, "The battle must be executed");
        return (round.number % 2 != 0)
            ? new Result(round.fighter, round.versus)
            : new Result(round.versus, round.fighter);
    }

    public static final class Organizer {

        private AbstractFighter opponent;

        private Organizer() {}

        public Organizer opponent(AbstractFighter opponent) {
            requireNonNull(opponent, "Missing the opponents");
            this.opponent = opponent;
            return this;
        }

        public Fight versus(final AbstractFighter versus) {
            requireNonNull(opponent, "Missing the opponents");
            requireNonNull(versus, "Missing the adversary");
            return new Fight(new Round(1, opponent, versus));
        }
    }

    public static final class Result {

        private final AbstractFighter opponent;

        private final AbstractFighter versus;

        private Result(AbstractFighter opponent, AbstractFighter versus) {
            this.opponent = requireNonNull(opponent);
            this.versus = requireNonNull(versus);
        }

        public AbstractFighter opponent() {
            return opponent;
        }

        public AbstractFighter versus() {
            return versus;
        }

        public Price opponentPrice() {
            return price(opponent);
        }

        public Price versusPrice() {
            return price(versus);
        }

        private Price price(AbstractFighter abstractFighter) {
            return Price.price(this, abstractFighter);
        }

        public enum Price {
            WINNER, LOSER, DRAW;

            private static Price price(final Result result, final AbstractFighter fighter) {
                final AbstractFighter adversary = (fighter.equals(result.opponent()))
                                                 ? result.versus()
                                                 : result.opponent();
                if (fighter.hitPoints() == adversary.hitPoints()) {
                    return DRAW;
                } else if (fighter.hitPoints() > 0) {
                    return WINNER;
                }
                return LOSER;
            }
        }
    }

    private static final class Round implements Iterator<Round> {

        private final int number;

        private final AbstractFighter fighter;

        private final AbstractFighter versus;

        private Round(int number, AbstractFighter fighter, AbstractFighter versus) {
            this.number = number;
            this.fighter = fighter;
            this.versus = versus;
        }

        @Override
        public boolean hasNext() {
            return fighter.hitPoints() > 0;
        }

        @Override
        public Round next() {
            return new Round(number + 1, fighter.engage(versus), fighter);
        }
    }
}
