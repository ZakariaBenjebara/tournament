package com.nespresso.sofa.recruitement.tournament.ability;

import com.nespresso.sofa.recruitement.tournament.attack.Attacker;
import com.nespresso.sofa.recruitement.tournament.fighter.Body;

final class Veteran extends AbstractAbility<Body> {

    Veteran(final Body value) {
        super(value, State.DISABLED);
    }

    private Veteran(Body value, State state) {
        super(value, state);
    }

    @Override
    public Attacker accept(final Attacker attacker) {
        checkState();
        return (state == State.ENABLED)
            ? attacker.increaseByPowerOfTwo()
            : attacker;
    }

    private void checkState() {
        state = value.percentage() < 30 ? State.ENABLED : state;
    }

    @Override
    public Veteran copyOf(final Object value) {
        return new Veteran((Body) value, state);
    }
}
