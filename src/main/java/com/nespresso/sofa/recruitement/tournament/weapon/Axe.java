package com.nespresso.sofa.recruitement.tournament.weapon;

import com.nespresso.sofa.recruitement.tournament.Damage;

@Impactful
final class Axe extends AbstractWeapon {

    Axe() {
        super(new Damage(6));
    }

    private Axe(Damage attackPower) {
        super(attackPower);
    }

    @Override
    public AbstractWeapon weaponDamage(final Damage specialAttack) {
        if (specialAttack == Damage.EMPTY_DAMAGE) {
            return this;
        }
        return new Axe(specialAttack);
    }
}
