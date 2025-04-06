package fr.azur.auria.species

import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.entity.Player

class Lypos:Specie {
    override val key: NamespacedKey = NamespacedKey("auria","lypos")
    override fun initPLayer(player: Player) {
        Specie.addModifier(
            player,
            Attribute.MOVEMENT_SPEED,
            0.3,
            AttributeModifier.Operation.MULTIPLY_SCALAR_1,
            "lypos_speed"
        )
        Specie.addModifier(
            player,
            Attribute.JUMP_STRENGTH,
            0.15,
            AttributeModifier.Operation.MULTIPLY_SCALAR_1,
            "lypos_jump"
        )
    }
}