package fr.azur.auria.commands

import com.mojang.brigadier.Command
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.command.brigadier.Commands
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object TestCommands {
    val testCommand: LiteralArgumentBuilder<CommandSourceStack> = Commands.literal("test").executes { ctx ->
        val player = ctx.source as Player

        val location = player.location

        location.y -= 1

        val item = ItemStack(Material.DROPPER)

        Command.SINGLE_SUCCESS
    }
}