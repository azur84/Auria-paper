package fr.azur.auria.events

import fr.azur.auria.species.Ailuros
import fr.azur.auria.species.Specie
import org.bukkit.Location
import org.bukkit.entity.Creeper
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityTargetLivingEntityEvent


class ScareCreeperEvents : Listener {

    @EventHandler
    fun onEntityTarget(event: EntityTargetLivingEntityEvent) {
        if (event.entity is Creeper && event.target is Player && Specie.getSpecie(event.target as Player) is Ailuros) {
            val creeper = event.entity as Creeper
            val player: Player = event.target as Player

            val creeperloc = creeper.location
            val playerloc = player.location

            val x0 = playerloc.x - creeperloc.x
            val z0 = playerloc.z - creeperloc.z

            val x1 = creeperloc.x + (0 - x0)
            val z1 = creeperloc.z + (0 - z0)

            val targetLoc = Location(creeper.world,x1,creeperloc.z,z1)

            creeper.pathfinder.moveTo(targetLoc)

            event.isCancelled = true
        }
    }
}