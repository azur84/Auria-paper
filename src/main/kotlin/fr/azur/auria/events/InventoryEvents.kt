package fr.azur.auria.events

import fr.azur.auria.menus.species.ChooseMenu
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class InventoryEvents: Listener {
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val inventory = event.clickedInventory
        if (inventory == null) return
        val holder = inventory.getHolder(false)
        if (holder is ChooseMenu) {
            holder.clickEvent(event)
        }
    }
}