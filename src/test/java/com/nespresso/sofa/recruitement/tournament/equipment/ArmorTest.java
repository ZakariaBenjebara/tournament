package com.nespresso.sofa.recruitement.tournament.equipment;

import com.nespresso.sofa.recruitement.tournament.Damage;
import com.nespresso.sofa.recruitement.tournament.weapon.AbstractWeapon;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ArmorTest {

    private static final Damage WEAPON_DAMAGE = new Damage(5);

    private static final Damage DIRECT_DAMAGE = new Damage(2);

    private AbstractEquipment equipment;

    @Before
    public void init() {
        equipment = AbstractEquipment.Factory.create("armor", AbstractEquipment.NIL);
        assertThat(equipment instanceof Armor).isTrue();
    }

    @Test
    public void test_Armor_Absorb_The_Required_Damage() {
        final AbstractWeapon oneHandSwordWeapon = AbstractWeapon.Factory.create("1H-Sword");
        final Damage directAttackDamage = equipment.absorbWeaponAttack(oneHandSwordWeapon);

        assertThat(oneHandSwordWeapon.attackPower()).isEqualTo(WEAPON_DAMAGE);
        assertThat(directAttackDamage).isEqualTo(DIRECT_DAMAGE);
    }
}