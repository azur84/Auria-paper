package fr.azur.auria.events

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerEvents : Listener {
    @EventHandler
    fun onPlayerJoin(e:PlayerJoinEvent) {
        val header = Component.text("Auria",NamedTextColor.AQUA)
        val footer = Component.text("Auria",NamedTextColor.AQUA)
        e.player.sendPlayerListHeaderAndFooter(header,footer)
    }
}