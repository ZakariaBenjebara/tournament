package com.nespresso.sofa.recruitement.tournament.fighter;

import com.nespresso.sofa.recruitement.tournament.Damage;
import com.nespresso.sofa.recruitement.tournament.ability.AbstractAbility;
import com.nespresso.sofa.recruitement.tournament.attack.Attacker;
import com.nespresso.sofa.recruitement.tournament.equipment.AbstractEquipment;
import com.nespresso.sofa.recruitement.tournament.weapon.AbstractWeapon;

import java.util.Iterator;
import java.util.Objects;

public abstract class AbstractFighter {

    private final Body body;

    private final AbstractWeapon weapon;

    private final AbstractEquipment defender;

    private final AbstractAbility ability;

    protected AbstractFighter(Body body, AbstractWeapon weapon, AbstractEquipment defender, AbstractAbility ability) {
        this.body = body;
        this.weapon = weapon;
        this.defender = defender;
        this.ability = ability;
    }

    protected AbstractFighter(Body body, AbstractWeapon weapon) {
        this(body, weapon, AbstractEquipment.NIL, AbstractAbility.NIL);
    }

    protected AbstractFighter(Body body, AbstractWeapon weapon, String nameOfAbility) {
        this(body, weapon, AbstractEquipment.NIL,
                AbstractAbility.Factory.create(nameOfAbility, body));
    }

    public final AbstractFighter engage(final AbstractFighter enemy) {
        return enemy.defendAttack(attack(prepareForAttack()));
    }

    // for type safe usage you must add a wildcard eq: .<Swordman>equip(nameOfEquipment)
    @SuppressWarnings("isn't type safe")
    public final <T extends AbstractFighter> T equip(final String nameOfEquipment) {
        final Builder<T> builder = newBuilder();
        return builder.equip(nameOfEquipment).build();
    }

    public final int hitPoints() {
        return body.hitPoints();
    }

    private AbstractWeapon attack(final Damage damage) {
        return weapon.weaponDamage(damage);
    }

    private AbstractFighter defendAttack(final AbstractWeapon weapon) {
        Damage weaponDamage = weapon.attackPower();
        if (defender != AbstractEquipment.NIL) {
            weaponDamage = defender.absorbWeaponAttack(weapon);
        }
        return body.resistToDamage(weaponDamage, this);
    }

    private final Damage prepareForAttack() {
        Attacker attacker = new Attacker(Damage.EMPTY_DAMAGE);
        attacker = weapon.accept(attacker);
        attacker = defender.accept(attacker);
        attacker = ability.accept(attacker);
        return attacker.prepareAttack();
    }

    protected abstract <T extends AbstractFighter> Builder<T> newBuilder();

    protected static abstract class Builder<T extends AbstractFighter> {

        protected Body body;

        protected AbstractWeapon weapon = AbstractWeapon.NIL;

        protected AbstractEquipment defender = AbstractEquipment.NIL;

        protected AbstractAbility ability = AbstractAbility.NIL;

        Builder(AbstractFighter abstractFighter) {
            this.body = abstractFighter.body;
            this.weapon = abstractFighter.weapon;
            this.defender = abstractFighter.defender;
            this.ability = abstractFighter.ability;
        }

        protected Builder<T> withBody(Body body) {
            this.body = body;
            this.ability = AbstractAbility.copyOf(ability, body);
            return this;
        }

        private Builder<T> equip(final String nameOfEquipment) {
            if (AbstractWeapon.Factory.Type.fromString(nameOfEquipment) != AbstractWeapon.Factory.Type.NIL) {
                makeWeapon(nameOfEquipment);
            } else if (AbstractEquipment.Factory.Type.fromString(nameOfEquipment) != AbstractEquipment.Factory.Type.NIL) {
                makeEquipmentSuccessor(nameOfEquipment);
            }
            return this;
        }

        private void makeWeapon(final String nameOfWeapon) {
            weapon = AbstractWeapon.Factory.create(nameOfWeapon);
        }

        private void makeEquipmentSuccessor(final String nameOfEquipment) {
            // TODO(zgeeks): that code section need some improvements
            if (defender == AbstractEquipment.NIL) {
                defender = AbstractEquipment.Factory.create(nameOfEquipment, AbstractEquipment.NIL);
            }
            else {
                if (!defender.hasNext()) {
                    defender = AbstractEquipment.copyOf(defender, AbstractEquipment.Factory.create(nameOfEquipment, AbstractEquipment.NIL));
                }
                else {
                    AbstractEquipment previous = defender;
                    Iterator<AbstractEquipment> iterator = defender.iterator();
                    while (!iterator.hasNext()) {
                        previous = iterator.next();
                        iterator = iterator.next().iterator();
                    }
                    AbstractEquipment next = iterator.next();
                    next = AbstractEquipment.copyOf(next, AbstractEquipment.Factory.create(nameOfEquipment, AbstractEquipment.NIL));
                    previous = AbstractEquipment.copyOf(previous, next);
                    defender = previous;
                }
            }
        }

        protected abstract T build();
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, weapon, defender, ability);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractFighter) {
            final AbstractFighter that = (AbstractFighter) obj;
            return Objects.equals(body, that.body)
                    && Objects.equals(weapon, that.weapon)
                    && Objects.equals(defender, that.defender)
                    && Objects.equals(ability, that.ability);
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractFighter{");
        sb.append("weapon=").append(weapon);
        sb.append(", body=").append(body);
        sb.append(", defender=").append(defender);
        sb.append(", ability=").append(ability);
        sb.append('}');
        return sb.toString();
    }
}
