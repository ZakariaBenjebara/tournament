package com.nespresso.sofa.recruitement.tournament.ability;

import com.nespresso.sofa.recruitement.tournament.Damage;
import com.nespresso.sofa.recruitement.tournament.attack.Attacker;

final class Vicious extends AbstractAbility<Damage> {

    private int blowCounter = 0;

    Vicious() {
        super(new Damage(20), State.ENABLED);
    }

    private Vicious(State state, int blowCounter) {
        super(new Damage(20), state);
        this.blowCounter = blowCounter;
    }

    @Override
    public Attacker accept(final Attacker attacker) {
        checkState();

        if (state == State.ENABLED) {
            return attacker.increaseDamage(value);
        }

        return attacker;
    }

    private void checkState() {
        if (blowCounter > 2) {
            return;
        }
        blowCounter++;
        state = blowCounter > 2 ? State.DISABLED : state;
    }

    @Override
    public Vicious copyOf(Object value) {
        return new Vicious(state, blowCounter);
    }
}
