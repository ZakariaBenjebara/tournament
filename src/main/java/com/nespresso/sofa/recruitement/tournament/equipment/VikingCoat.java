package com.nespresso.sofa.recruitement.tournament.equipment;

import com.nespresso.sofa.recruitement.tournament.Damage;
import com.nespresso.sofa.recruitement.tournament.attack.Attacker;
import com.nespresso.sofa.recruitement.tournament.weapon.AbstractWeapon;

final class VikingCoat extends AbstractEquipment {

    VikingCoat(AbstractEquipment nextEquipment) {
        super(nextEquipment);
    }

    @Override
    public Damage absorbWeaponAttack(final AbstractWeapon weapon) {
        return weapon.attackPower().decrease(new Damage(5));
    }

    @Override
    public Attacker accept(final Attacker attacker) {
        return attacker;
    }

    @Override
    public VikingCoat copyOf(final AbstractEquipment nextEquipment) {
        return new VikingCoat(nextEquipment);
    }

    @Override
    public String toString() {
        return "VikingCoat{"+next()+"}";
    }
}
