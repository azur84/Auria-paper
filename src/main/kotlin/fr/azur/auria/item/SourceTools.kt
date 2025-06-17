package fr.azur.auria.item

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

object SourceTools {
    val sourceKey = NamespacedKey("auria","source")
    fun setSource(item: ItemStack, source: String) {
        item.editPersistentDataContainer {
            it.set(sourceKey, PersistentDataType.STRING,source)
        }
    }
    fun getSource(item: ItemStack): String {
        return item.persistentDataContainer.getOrDefault(sourceKey, PersistentDataType.STRING, "minecraft")
    }
    fun formatSource(name: String): Component {
        return Component.translatable("source.item.${name}").color(NamedTextColor.BLUE).decorate(TextDecoration.ITALIC)
    }
}