package fr.azur.auria.commands

import com.mojang.brigadier.Command
import com.mojang.brigadier.tree.LiteralCommandNode
import fr.azur.auria.item.LoreTools
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.command.brigadier.Commands
import org.bukkit.entity.Player

fun fixItemCommand(): LiteralCommandNode<CommandSourceStack> {
    return Commands.literal("fixitem")
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
        .build()
}