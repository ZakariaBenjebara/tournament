package com.nespresso.sofa.recruitement.tournament.fighter;

import com.nespresso.sofa.recruitement.tournament.ability.AbstractAbility;
import com.nespresso.sofa.recruitement.tournament.equipment.AbstractEquipment;
import com.nespresso.sofa.recruitement.tournament.weapon.AbstractWeapon;

import static com.nespresso.sofa.recruitement.tournament.weapon.AbstractWeapon.Factory.Type.ONE_HAND_SWORD;
import static com.nespresso.sofa.recruitement.tournament.weapon.AbstractWeapon.Factory.create;

public final class Swordsman extends AbstractFighter {

    public Swordsman() {
        super(new Body(100, 100), create(ONE_HAND_SWORD));
    }

    public Swordsman(String nameOfAbility) {
        super(new Body(100, 100), create(ONE_HAND_SWORD), nameOfAbility);
    }

    private Swordsman(Body body, AbstractWeapon weapon, AbstractEquipment defender, AbstractAbility ability) {
        super(body, weapon, defender, ability);
    }

    @Override
    protected Builder newBuilder() {
        return new Builder(this);
    }

    private final static class Builder extends AbstractFighter.Builder<Swordsman> {

        private Builder(AbstractFighter abstractFighter) {
            super(abstractFighter);
        }

        @Override
        public Swordsman build() {
            return new Swordsman(this.body, this.weapon, this.defender, this.ability);
        }
    }
}
