package fr.azur.auria.item

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

enum class Rarity(val color: TextColor, val rarityName: Component) {
    Common(NamedTextColor.WHITE, Component.translatable("rarity.item.common")),
    Uncommon(NamedTextColor.GOLD, Component.translatable("rarity.item.uncommon")),
    Rare(NamedTextColor.AQUA, Component.translatable("rarity.item.rare")),
    Epic(NamedTextColor.LIGHT_PURPLE, Component.translatable("rarity.item.epic")),
    Legendary(NamedTextColor.RED, Component.translatable("rarity.item.legendary")),
    Exotic(NamedTextColor.GREEN, Component.translatable("rarity.item.exotic"));

    companion object {
        val rarityKey = NamespacedKey("auria", "rarity")
        fun setRarity(item: ItemStack, rarity: Rarity) {
            item.itemMeta = item.itemMeta.apply {
                persistentDataContainer.apply {
                    set(rarityKey, PersistentDataType.INTEGER, rarity.ordinal)
                }
                if (!hasItemName()) return
                itemName(itemName().color(rarity.color))
            }
        }

        fun getRarity(item: ItemStack): Rarity {
            val rarityOrdinal = item.persistentDataContainer.getOrDefault(
                rarityKey,
                PersistentDataType.INTEGER,
                if (item.itemMeta.hasRarity()) item.itemMeta.rarity.ordinal else 0
            )
            return Rarity.entries[rarityOrdinal]
        }
    }
}