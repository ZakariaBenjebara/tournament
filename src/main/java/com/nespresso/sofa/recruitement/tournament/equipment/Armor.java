package com.nespresso.sofa.recruitement.tournament.equipment;

import com.nespresso.sofa.recruitement.tournament.Damage;
import com.nespresso.sofa.recruitement.tournament.attack.Attacker;
import com.nespresso.sofa.recruitement.tournament.weapon.AbstractWeapon;

final class Armor extends AbstractEquipment {

    Armor(AbstractEquipment nextEquipment) {
        super(nextEquipment);
    }

    @Override
    public Damage absorbWeaponAttack(final AbstractWeapon weapon) {
        final Damage damage = weapon.attackPower().decrease(new Damage(3));
        return (hasNext())
            ? next().absorbWeaponAttack(weapon.weaponDamage(damage))
            : damage;
    }

    @Override
    public Attacker accept(final Attacker attacker) {
        return attacker.decreaseDamage(new Damage(1));
    }

    @Override
    public Armor copyOf(final AbstractEquipment nextEquipment) {
        return new Armor(nextEquipment);
    }

    @Override
    public String toString() {
        return "Armor{"+next()+"}";
    }
}
