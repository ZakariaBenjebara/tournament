package com.nespresso.sofa.recruitement.tournament.equipment;

import com.nespresso.sofa.recruitement.tournament.Damage;
import com.nespresso.sofa.recruitement.tournament.attack.Attacker;
import com.nespresso.sofa.recruitement.tournament.weapon.AbstractWeapon;
import com.nespresso.sofa.recruitement.tournament.weapon.Impactful;

import static com.nespresso.sofa.recruitement.tournament.Damage.EMPTY_DAMAGE;

final class Buckler extends AbstractEquipment {

    private enum State {
        DESTROYED, FINE
    }

    private boolean cancelAttack = false;

    private State state = State.FINE;

    private int hardAttackBlockingCounter = 0;

    Buckler(AbstractEquipment nextEquipment) {
        super(nextEquipment);
    }

    @Override
    public Damage absorbWeaponAttack(final AbstractWeapon weapon) {
        if (cancelAttack || state == State.DESTROYED) {
            cancelAttack = false;

            return (hasNext())
                ? next().absorbWeaponAttack(weapon)
                : weapon.attackPower();
        }
        return absorbAttack(weapon);
    }

    private Damage absorbAttack(final AbstractWeapon weapon) {
        if (weapon.getClass().isAnnotationPresent(Impactful.class)) {
            hardAttackBlockingCounter++;
        }

        if (hardAttackBlockingCounter == 3) {
            state = State.DESTROYED;
        }

        if (weapon != AbstractWeapon.NIL) {
            cancelAttack = true;
        }

        return EMPTY_DAMAGE;
    }

    @Override
    public Attacker accept(final Attacker attacker) {
        if (hasNext()) {
            return next().accept(attacker);
        }
        return attacker;
    }

    @Override
    public Buckler copyOf(final AbstractEquipment nextEquipment) {
        return new Buckler(nextEquipment);
    }

    @Override
    public String toString() {
        return "Buckler{"+next()+"}";
    }
}
