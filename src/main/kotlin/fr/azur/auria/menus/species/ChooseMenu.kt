package fr.azur.auria.menus.species

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

class ChooseMenu : InventoryHolder {
    private val inventory = Bukkit.createInventory(this, 54, Component.text(""))

    constructor() {

    }

    override fun getInventory(): Inventory {
        return inventory
    }

    fun clickEvent(event: InventoryClickEvent) {
        event.isCancelled = true
    }
}