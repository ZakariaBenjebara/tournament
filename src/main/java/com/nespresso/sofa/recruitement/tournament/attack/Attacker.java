package com.nespresso.sofa.recruitement.tournament.attack;

import com.nespresso.sofa.recruitement.tournament.Damage;

public final class Attacker {

    private final Damage attackPower;

    public Attacker(Damage attackPower) {
        this.attackPower = attackPower;
    }

    public Attacker increaseDamage(final Damage damage) {
        return new Attacker(attackPower.increase(damage));
    }

    public Attacker decreaseDamage(final Damage damage) {
        return new Attacker(attackPower.decrease(damage));
    }

    public Attacker increaseByPowerOfTwo() {
        return new Attacker(attackPower.powerOfTwo());
    }

    public Damage prepareAttack() {
        return attackPower;
    }
}
