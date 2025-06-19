package fr.azur.auria.commands

import com.mojang.brigadier.Command
import fr.azur.auria.items.data.Rarity
import io.papermc.paper.command.brigadier.Commands
import org.bukkit.entity.Player

object TestCommand {
    val command = Commands.literal("test").executes { ctx ->
        val player = ctx.source.executor as Player
        Rarity.setRarity(player.inventory.itemInMainHand, Rarity.Legendary)
        Command.SINGLE_SUCCESS
    }.build()!!
}