package com.nespresso.sofa.recruitement.tournament.weapon;

import com.nespresso.sofa.recruitement.tournament.Damage;
import com.nespresso.sofa.recruitement.tournament.attack.Attacker;
import com.nespresso.sofa.recruitement.tournament.attack.AttackerVisitable;

import static com.nespresso.sofa.recruitement.tournament.Damage.EMPTY_DAMAGE;

public abstract class AbstractWeapon implements AttackerVisitable {

    public final static AbstractWeapon NIL = new AbstractWeapon(EMPTY_DAMAGE) {
        @Override
        public Attacker accept(Attacker attacker) {
            return attacker;
        }

        @Override
        public AbstractWeapon weaponDamage(final Damage specialAttack) {
            return this;
        }
    };

    protected final Damage attackPower;

    protected AbstractWeapon(Damage attackPower) {
        this.attackPower = attackPower;
    }

    public final Damage attackPower() {
        return attackPower;
    }

    @Override
    public Attacker accept(final Attacker attacker) {
        return attacker.increaseDamage(attackPower);
    }

    public abstract AbstractWeapon weaponDamage(Damage specialAttack);

    public final static class Factory {

        private Factory() {}

        public static AbstractWeapon create(final String nameOfEquipment) {
            final Type type = Type.fromString(nameOfEquipment);
            return create(type);
        }

        public static AbstractWeapon create(final Type typeOfSword) {
            switch (typeOfSword) {
                case ONE_HAND_SWORD:
                    return new OneHandSword();
                case GREAT_SWORD:
                    return new GreatSword();
                case AXE:
                    return new Axe();
                default:
                    throw new IllegalArgumentException();
            }
        }

        public enum Type {
            ONE_HAND_SWORD, AXE, GREAT_SWORD, NIL;

            public static Type fromString(final String nameOfEquipment) {
                switch (nameOfEquipment) {
                    case "1H-Sword":
                        return ONE_HAND_SWORD;
                    case "Axe":
                    case "axe":
                        return AXE;
                    case "G-Sword":
                        return GREAT_SWORD;
                    default:
                        return NIL;
                }
            }
        }
    }
}
