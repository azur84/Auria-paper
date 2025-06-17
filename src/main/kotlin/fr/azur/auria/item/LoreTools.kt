package fr.azur.auria.item

import net.kyori.adventure.text.Component
import org.bukkit.inventory.ItemStack

object LoreTools {
    fun fixItemLore(item: ItemStack, consumer: (item: ItemStack) -> List<Component>): ItemStack {
        val lore = buildList {
            val rarity = Rarity.getRarity(item)
            add(rarity.rarityName.color(rarity.color))
            addAll(consumer(item))
            add(SourceTools.formatSource(SourceTools.getSource(item)))
        }
        item.lore(lore)
        return item
    }

    fun fixItemLore(item: ItemStack) = fixItemLore(item) { emptyList() }
}