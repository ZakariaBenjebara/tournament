package com.nespresso.sofa.recruitement.tournament.equipment;

import com.nespresso.sofa.recruitement.tournament.Damage;
import com.nespresso.sofa.recruitement.tournament.attack.Attacker;
import com.nespresso.sofa.recruitement.tournament.attack.AttackerVisitable;
import com.nespresso.sofa.recruitement.tournament.weapon.AbstractWeapon;

import java.util.Iterator;

public abstract class AbstractEquipment implements AttackerVisitable, Iterator<AbstractEquipment> {

    private final AbstractEquipment nextEquipment;

    protected AbstractEquipment(AbstractEquipment nextEquipment) {
        this.nextEquipment = nextEquipment;
    }

    @Override
    public final AbstractEquipment next() {
        return nextEquipment;
    }

    @Override
    public final boolean hasNext() {
        return nextEquipment != NIL;
    }

    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final Iterator<AbstractEquipment> iterator() {
        return this;
    }

    public static <T extends AbstractEquipment> T copyOf(T abstractEquipment, AbstractEquipment nextEquipment) {
        return abstractEquipment.copyOf(nextEquipment);
    }

    protected abstract <T extends AbstractEquipment> T copyOf(AbstractEquipment nextEquipment);

    public abstract Damage absorbWeaponAttack(final AbstractWeapon weapon);

    public static final AbstractEquipment NIL = new AbstractEquipment(AbstractEquipment.NIL) {

        @Override
        public Damage absorbWeaponAttack(AbstractWeapon weapon) {
            return Damage.EMPTY_DAMAGE;
        }

        @Override
        public Attacker accept(Attacker attacker) {
            return attacker;
        }

        @Override
        public AbstractEquipment copyOf(AbstractEquipment nextEquipment) {
            return this;
        }

        @Override
        public String toString() {
            return "NIL";
        }
    };

    public final static class Factory {

        private Factory() {}

        public static AbstractEquipment create(final String nameOfEquipment, AbstractEquipment next) {
            final Type type = Type.fromString(nameOfEquipment);
            switch (type) {
                case BUCKLER:
                    return new Buckler(next);
                case ARMOR:
                    return new Armor(next);
                case HEAVY_COAT:
                    return new HeavyCoat(next);
                case VIKING_COAT:
                    return new VikingCoat(next);
                default:
                    throw new IllegalArgumentException();
            }
        }

        public enum Type {
            BUCKLER, ARMOR, HEAVY_COAT, VIKING_COAT, NIL;

            public static Type fromString(final String nameOfEquipment) {
                switch (nameOfEquipment) {
                    case "buckler":
                    case "bouclier":
                        return BUCKLER;
                    case "armor":
                    case "armure":
                        return ARMOR;
                    case "heavy coat":
                    case "manteau lourd":
                        return HEAVY_COAT;
                    case "viking coat":
                    case "manteau viking":
                        return VIKING_COAT;
                    default:
                        return NIL;
                }
            }
        }
    }
}
