package com.zhaoch23.attackspeedmodifier.api;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Include a few attack speed utils
 *
 * @author zhaoch23
 * @version 1.0
 */
public class AttackSpeedUtils {

    public static final UUID[] CUSTOM_ATTACK_SPEED_MODIFIER_UUID = new UUID[] {
        UUID.fromString("e25bbae1-b92c-4b62-9567-3b15c304f2cf"),
        UUID.fromString("e0aa0755-1aac-4c83-ba94-8c9770b4ae94"),
        UUID.fromString("fe2eaae3-122f-497b-954e-72d6aee765d7")
    };

    public static final String CUSTOM_ATTACK_SPEED_MODIFIER_NAME = "CustomAttackSpeedModifier";

    /**
     * Get the player attack speed.
     *
     * @param player The player you want to get from
     * @return Attack speed in frequency hz (n swings per second)
     */
    public static double getPlayerAttackSpeed(Player player) {
        return player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getValue();
    }

    /**s
     * Adds an attack speed modifier to the specified player.
     * Each operation type is treated distinctly.
     *
     * <p>
     *     If you want to add 1.6 attack speed to player
     *     (the same as holding a diamond sword):
     * </p>
     * <pre>
     *     addCustomAttackSpeedModifier(player, 1.6, AttributeModifier.Operation.ADD_NUMBER);
     * </pre>
     *
     * @see <a href="https://hub.spigotmc.org/javadocs/spigot/org/bukkit/attribute/AttributeModifier.Operation.html">
     *      org.bukkit.attribute.AttributeModifier.Operation
     *      </a>
     *
     * @param player The player you want to add to.
     * @param modifierAmount The value you want to add to.
     * @param operation The operation taken on the base attribute (whether add, total multiply or base multiply)
     */
    public static void addCustomAttackSpeedModifier(Player player,
                                                    double modifierAmount,
                                                    org.bukkit.attribute.AttributeModifier.Operation operation) {
        EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
        // Remove existing modifier
        removeCustomAttackSpeedModifier(player, operation);

        AttributeModifier modifier = new AttributeModifier(CUSTOM_ATTACK_SPEED_MODIFIER_UUID[operation.ordinal()],
                CUSTOM_ATTACK_SPEED_MODIFIER_NAME + operation.ordinal(),
                modifierAmount,
                operation.ordinal());

        nmsPlayer.getAttributeInstance(GenericAttributes.g).b(modifier);
    }

    /**
     * Remove the custom attack speed with given operation type.
     *
     * @see <a href="https://hub.spigotmc.org/javadocs/spigot/org/bukkit/attribute/AttributeModifier.Operation.html">
     *      org.bukkit.attribute.AttributeModifier.Operation
     *      </a>
     *
     * @param player The player you want to remove from.
     * @param operation The operation taken on the base attribute.
     */
    public static void removeCustomAttackSpeedModifier(Player player,
                                                       org.bukkit.attribute.AttributeModifier.Operation operation) {
        EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
        UUID uuid= CUSTOM_ATTACK_SPEED_MODIFIER_UUID[operation.ordinal()];
        if (nmsPlayer.getAttributeInstance(GenericAttributes.g).a(uuid) != null) {
            nmsPlayer.getAttributeInstance(GenericAttributes.g).b(uuid);
        }
    }

}
