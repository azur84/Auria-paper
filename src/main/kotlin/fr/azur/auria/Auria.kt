package fr.azur.auria

import fr.azur.auria.events.PlayerEvents
import fr.azur.auria.events.ScareCreeperEvents
import fr.azur.auria.species.Ailuros
import fr.azur.auria.species.Human
import fr.azur.auria.species.Lypos
import fr.azur.auria.species.Specie
import org.bukkit.plugin.java.JavaPlugin

class Auria : JavaPlugin() {

    override fun onEnable() {
        server.pluginManager.registerEvents(PlayerEvents(), this)
        server.pluginManager.registerEvents(ScareCreeperEvents(),this)
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
