package com.nespresso.sofa.recruitement.tournament.ability;

import com.nespresso.sofa.recruitement.tournament.attack.Attacker;
import com.nespresso.sofa.recruitement.tournament.attack.AttackerVisitable;
import com.nespresso.sofa.recruitement.tournament.fighter.Body;

public abstract class AbstractAbility<T> implements AttackerVisitable {

    public static final AbstractAbility NIL = new AbstractAbility(null, State.DISABLED) {
        @Override
        public Attacker accept(final Attacker attacker) {
            return attacker;
        }

        @Override
        public AbstractAbility copyOf(Object value) {
            return this;
        }
    };

    protected enum State {
        ENABLED, DISABLED
    }

    protected final T value;

    protected State state;

    protected AbstractAbility(T value, State state) {
        this.value = value;
        this.state = state;
    }

    protected abstract AbstractAbility copyOf(Object value);

    public static <T> AbstractAbility copyOf(AbstractAbility ability, T value) {
        return ability.copyOf(value);
    }

    public static final class Factory {

        private Factory() {} // prevent instance

        public static AbstractAbility create(final String nameOfAbility, final Object... type) {
            if ("Vicious".equals(nameOfAbility)) {
                return new Vicious();
            } else if ("Veteran".equals(nameOfAbility)) {
                return new Veteran((Body) type[0]);
            }
            throw new IllegalArgumentException();
        }
    }
}
