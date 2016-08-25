package com.nespresso.sofa.recruitement.tournament.fighter;

import com.nespresso.sofa.recruitement.tournament.ability.AbstractAbility;
import com.nespresso.sofa.recruitement.tournament.equipment.AbstractEquipment;
import com.nespresso.sofa.recruitement.tournament.weapon.AbstractWeapon;

import static com.nespresso.sofa.recruitement.tournament.weapon.AbstractWeapon.Factory.Type.AXE;
import static com.nespresso.sofa.recruitement.tournament.weapon.AbstractWeapon.Factory.create;

public final class Viking extends AbstractFighter {

    public Viking() {
        super(new Body(120, 120), create(AXE));
    }

    public Viking(String nameOfAbility) {
        super(new Body(120, 120), create(AXE), nameOfAbility);
    }

    private Viking(Body body, AbstractWeapon weapon, AbstractEquipment defender, AbstractAbility ability) {
        super(body, weapon, defender, ability);
    }

    @Override
    protected Builder newBuilder() {
        return new Builder(this);
    }

    private final static class Builder extends AbstractFighter.Builder<Viking> {

        private Builder(AbstractFighter abstractFighter) {
            super(abstractFighter);
        }

        @Override
        public Viking build() {
            return new Viking(this.body, this.weapon, this.defender, this.ability);
        }
    }
}
