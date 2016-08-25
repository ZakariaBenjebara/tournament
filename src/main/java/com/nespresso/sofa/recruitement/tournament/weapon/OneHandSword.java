package com.nespresso.sofa.recruitement.tournament.weapon;

import com.nespresso.sofa.recruitement.tournament.Damage;

final class OneHandSword extends AbstractWeapon {

    OneHandSword() {
        super(new Damage(5));
    }

    private OneHandSword(Damage attackPower) {
        super(attackPower);
    }

    @Override
    public AbstractWeapon weaponDamage(final Damage specialAttack) {
        if (specialAttack == Damage.EMPTY_DAMAGE ) {
            return this;
        }
        return new OneHandSword(specialAttack);
    }
}
