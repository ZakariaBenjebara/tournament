package com.nespresso.sofa.recruitement.tournament.fighter;

import com.nespresso.sofa.recruitement.tournament.ability.AbstractAbility;
import com.nespresso.sofa.recruitement.tournament.equipment.AbstractEquipment;
import com.nespresso.sofa.recruitement.tournament.weapon.AbstractWeapon;

import static com.nespresso.sofa.recruitement.tournament.weapon.AbstractWeapon.Factory.Type.GREAT_SWORD;
import static com.nespresso.sofa.recruitement.tournament.weapon.AbstractWeapon.Factory.create;

public final class Highlander extends AbstractFighter {

    public Highlander() {
        super(new Body(150, 150), create(GREAT_SWORD));
    }

    public Highlander(String nameOfAbility) {
        super(new Body(150, 150), create(GREAT_SWORD), nameOfAbility);
    }

    private Highlander(Body body, AbstractWeapon weapon, AbstractEquipment defender, AbstractAbility ability) {
        super(body, weapon, defender, ability);
    }

    @Override
    protected Builder newBuilder() {
        return new Builder(this);
    }

    private final static class Builder extends AbstractFighter.Builder<Highlander> {

        private Builder(AbstractFighter abstractFighter) {
            super(abstractFighter);
        }

        @Override
        public Highlander build() {
            return new Highlander(this.body, this.weapon, this.defender, this.ability);
        }
    }
}
