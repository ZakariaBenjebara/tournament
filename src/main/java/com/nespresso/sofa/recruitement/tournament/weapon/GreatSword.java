package com.nespresso.sofa.recruitement.tournament.weapon;

import com.nespresso.sofa.recruitement.tournament.Damage;

final class GreatSword extends AbstractWeapon {

    private int attackCounter = 1;

    GreatSword() {
        super(new Damage(12));
    }

    private GreatSword(Damage attackPower) {
        super(attackPower);
    }

    @Override
    public AbstractWeapon weaponDamage(final Damage specialAttack) {
        if (attackCounter == 3) {
            attackCounter = 1;
            return NIL;
        }
        attackCounter++;

        if (specialAttack == Damage.EMPTY_DAMAGE) {
            return this;
        }

        return new GreatSword(specialAttack);
    }
}
