package com.nespresso.sofa.recruitement.tournament.equipment;

import com.nespresso.sofa.recruitement.tournament.Damage;
import com.nespresso.sofa.recruitement.tournament.weapon.AbstractWeapon;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class BucklerTest {

    private static final Damage WEAPON_DAMAGE = new Damage(5);

    private AbstractEquipment equipment;

    @Before
    public void init() {
        equipment = AbstractEquipment.Factory.create("buckler", AbstractEquipment.NIL);
        assertThat(equipment instanceof Buckler).isTrue();
    }

    @Test
    public void test_Buckler_Block_First_Attack() {
        final AbstractWeapon oneHandSwordWeapon = AbstractWeapon.Factory.create("1H-Sword");
        final Damage damageAbsorbed = equipment.absorbWeaponAttack(oneHandSwordWeapon);

        assertThat(oneHandSwordWeapon.attackPower()).isEqualTo(new Damage(5));
        assertThat(damageAbsorbed).isEqualTo(Damage.EMPTY_DAMAGE);
    }

    @Test
    public void test_Buckler_Block_Only_One_Attack_Every_Two_Blow() {
        final AbstractWeapon oneHandSwordWeapon = AbstractWeapon.Factory.create("1H-Sword");
        final Damage firstDirectAttackDamage = equipment.absorbWeaponAttack(oneHandSwordWeapon);
        final Damage secondDirectAttackDamage = equipment.absorbWeaponAttack(oneHandSwordWeapon);

        assertThat(oneHandSwordWeapon.attackPower()).isEqualTo(WEAPON_DAMAGE);
        assertThat(firstDirectAttackDamage).isEqualTo(Damage.EMPTY_DAMAGE);
        assertThat(secondDirectAttackDamage).isEqualTo(oneHandSwordWeapon.attackPower());
    }
}