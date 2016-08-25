package com.nespresso.sofa.recruitement.tournament.fighter;

import com.nespresso.sofa.recruitement.tournament.Damage;

public final class Body {

    private final int initialHealth;

    private final int currentHealth;

    Body(int initialHealth, int currentHealth) {
        this.initialHealth = initialHealth;
        this.currentHealth = currentHealth;
    }

    final AbstractFighter resistToDamage(final Damage attackPower, final AbstractFighter abstractFighter) {
        final int damage = attackPower.absorbFrom(currentHealth);
        return abstractFighter.newBuilder().withBody(copyOf(damage)).build();
    }

    static Body copyOf(final Body body, int currentHealth) {
        return body.copyOf(currentHealth);
    }

    int hitPoints() {
        return currentHealth <= 0 ? 0 : currentHealth;
    }

    public int percentage() {
        return (currentHealth * 100) / initialHealth;
    }

    private Body copyOf(int currentHealth) {
        return new Body(this.initialHealth, currentHealth);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Body{");
        sb.append("initialHealth=").append(initialHealth);
        sb.append(", currentHealth=").append(currentHealth);
        sb.append('}');
        return sb.toString();
    }
}
