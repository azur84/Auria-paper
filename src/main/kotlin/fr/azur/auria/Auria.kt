package fr.azur.auria

import fr.azur.auria.events.EnchantedBookEvent
import fr.azur.auria.events.PlayerEvent
import fr.azur.auria.events.ScareCreeperEvent
import fr.azur.auria.species.Ailuros
import fr.azur.auria.species.Human
import fr.azur.auria.species.Lypos
import fr.azur.auria.species.Specie
import org.bukkit.plugin.java.JavaPlugin

class Auria : JavaPlugin() {

    override fun onEnable() {
        server.pluginManager.registerEvents(PlayerEvent(), this)
        server.pluginManager.registerEvents(EnchantedBookEvent(),this)
        server.pluginManager.registerEvents(ScareCreeperEvent(),this)
        registerSpecies()
    }

    override fun onDisable() {

    }

    private fun registerSpecies() {
        Specie.registerSpecie(Human())
        Specie.registerSpecie(Lypos())
        Specie.registerSpecie(Ailuros())
    }
}
