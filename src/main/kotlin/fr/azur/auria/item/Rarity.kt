package fr.azur.auria.item

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

enum class Rarity(val color: TextColor,val rarityName: Component) {
    Common(NamedTextColor.WHITE, Component.translatable("rarity.item.common")),
    Uncommon(NamedTextColor.GOLD, Component.translatable("rarity.item.uncommon")),
    Rare(NamedTextColor.AQUA, Component.translatable("rarity.item.rare")),
    Epic(NamedTextColor.DARK_PURPLE, Component.translatable("rarity.item.epic")),
    Legendary(NamedTextColor.DARK_RED, Component.translatable("rarity.item.legendary")),
    Exotic(NamedTextColor.DARK_GREEN, Component.translatable("rarity.item.exotic"));
    companion object {
        val rarityKey = NamespacedKey("auria","rarity")

        fun getRarity(item: ItemStack) {

        }

        fun setRarity(item: ItemStack,rarity: Rarity) {
            item.itemMeta = item.itemMeta.apply {
                persistentDataContainer.set(rarityKey, PersistentDataType.INTEGER,rarity.ordinal)
                if (hasItemName()) itemName(itemName().color(rarity.color).decorations(emptyMap())) else itemName(item.effectiveName().color(rarity.color).decorations(emptyMap()))
            }
        }
    }
}