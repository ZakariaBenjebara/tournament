package com.nespresso.sofa.recruitement.tournament.equipment;

import com.nespresso.sofa.recruitement.tournament.Damage;
import com.nespresso.sofa.recruitement.tournament.attack.Attacker;
import com.nespresso.sofa.recruitement.tournament.weapon.AbstractWeapon;

final class HeavyCoat extends AbstractEquipment {

    HeavyCoat(AbstractEquipment nextEquipment) {
        super(nextEquipment);
    }

    @Override
    public Damage absorbWeaponAttack(final AbstractWeapon weapon) {
        return weapon.attackPower().decrease(new Damage(2));
    }

    @Override
    public Attacker accept(final Attacker attacker) {
        return attacker;
    }

    @Override
    public HeavyCoat copyOf(final AbstractEquipment nextEquipment) {
        return new HeavyCoat(nextEquipment);
    }

    @Override
    public String toString() {
        return "HeavyCoat{"+next()+"}";
    }
}
