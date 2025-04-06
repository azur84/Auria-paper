package fr.azur.auria.events

import fr.azur.auria.utils.EnchantBookModelData
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent


class EnchantedBookEvent : Listener {

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        event.cursor.let { EnchantBookModelData.applyModel(it) }
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        event.item?.let { EnchantBookModelData.applyModel(it) }
    }

    @EventHandler
    fun onPlayerPickup(event: EntityPickupItemEvent) {
        EnchantBookModelData.applyModel(event.item.itemStack)
    }
}