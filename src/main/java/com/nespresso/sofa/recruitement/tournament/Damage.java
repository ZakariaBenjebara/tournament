package com.nespresso.sofa.recruitement.tournament;

import java.util.Objects;

public final class Damage {

    public static final Damage EMPTY_DAMAGE = new Damage(0);

    private final int value;

    public Damage(int value) {
        this.value = value;
    }

    public int absorbFrom(int from) {
        final int damageAbsorbed = from - value;
        return (damageAbsorbed < 0) ? 0 : damageAbsorbed;
    }

    public Damage increase(final Damage damage) {
        final int newValue = this.value + damage.value;
        return (newValue <= 0) ? EMPTY_DAMAGE : new Damage(newValue);
    }

    public Damage decrease(final Damage damage) {
        final int newValue = this.value - damage.value;
        return (newValue <= 0) ? EMPTY_DAMAGE : new Damage(newValue);
    }

    public Damage powerOfTwo() {
        return increase(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Damage) {
            final Damage that = (Damage) o;
            return Objects.equals(value, that.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "{" +
                "\"value\" :" + value +
                '}';
    }
}
