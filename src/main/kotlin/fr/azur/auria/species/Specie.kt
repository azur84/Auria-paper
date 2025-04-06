package fr.azur.auria.species

import org.bukkit.NamespacedKey
import org.bukkit.Registry
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.attribute.AttributeModifier.Operation
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.persistence.PersistentDataType


interface Specie : Listener {
    val key: NamespacedKey
    fun initPLayer(player: Player)

    companion object {
        private val specieKey = NamespacedKey("auria", "specie")
        private var registeredSpecies: HashMap<NamespacedKey, Specie> = hashMapOf()

        fun addModifier(player: Player, attribute: Attribute, value: Double, operation: Operation, key: String) {
            player.getAttribute(attribute)
                ?.addModifier(
                    AttributeModifier(
                        NamespacedKey("auria", "spe_$key"),
                        value,
                        operation
                    )
                )
        }

        private fun removeModifiers(player: Player) {
            Registry.ATTRIBUTE.iterator().forEach { attribute ->
                val instance = player.getAttribute(attribute)
                if (instance != null) {
                    val modifiers = instance.modifiers
                    if (modifiers.isNotEmpty()) {
                        modifiers.forEach { modifier ->
                            if (modifier.key.key.startsWith("spe_")) {
                                instance.removeModifier(modifier.key)
                            }
                        }
                    }
                }
            }
        }

        fun setSpecie(player: Player, specie: Specie) {
            removeModifiers(player)
            val data = player.persistentDataContainer
            data.set(
                specieKey,
                PersistentDataType.STRING,
                specie.key.asString()
            )
            specie.initPLayer(player)
        }

        fun getSpecie(player: Player): Specie? {
            val data = player.persistentDataContainer
            val keystr = data.getOrDefault(specieKey, PersistentDataType.STRING, "auria:human")
            val key = NamespacedKey.fromString(keystr)
            val specie = registeredSpecies[key]
            return specie
        }

        val speciesKeyString: List<String>
            get(): List<String> {
                return registeredSpecies.map { specie ->
                    specie.key.asString()
                }
            }

        fun getSpecieByKey(namespacedKey: NamespacedKey): Specie? {
            return registeredSpecies[namespacedKey]
        }

        fun registerSpecie(specie: Specie) {
            registeredSpecies[specie.key] = specie
        }
    }
}