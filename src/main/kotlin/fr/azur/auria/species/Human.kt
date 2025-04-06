package fr.azur.auria.species

import org.bukkit.NamespacedKey
import org.bukkit.entity.Player

class Human : Specie {
    override val key: NamespacedKey = NamespacedKey("auria", "human")
    override fun initPLayer(player: Player) {}
}