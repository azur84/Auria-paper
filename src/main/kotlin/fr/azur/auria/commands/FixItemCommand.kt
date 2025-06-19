package fr.azur.auria.commands

import com.mojang.brigadier.Command
import fr.azur.auria.items.data.LoreTools
import io.papermc.paper.command.brigadier.Commands
import org.bukkit.entity.Player

object FixItemCommand {
    val command = Commands.literal("fix-items")
        .executes { ctx ->
            val executor = ctx.source.executor
            if (executor is Player) {
                executor.inventory.forEach {
                    if (!it.isEmpty) {
                        LoreTools.fixItemLore(it)
                    }
                }
            }
            Command.SINGLE_SUCCESS
        }
        .build()!!
}