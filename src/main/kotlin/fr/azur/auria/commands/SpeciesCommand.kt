package fr.azur.auria.commands

import com.mojang.brigadier.Command
import fr.azur.auria.menus.species.ChooseMenu
import fr.azur.auria.species.Specie
import io.papermc.paper.command.brigadier.Commands
import io.papermc.paper.command.brigadier.argument.ArgumentTypes
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver
import net.kyori.adventure.text.Component
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player

object SpeciesCommand {
    val command = Commands.literal("species")
        .then(
            Commands.literal("set")
                .then(
                    Commands.argument("players", ArgumentTypes.player())
                        .then(
                            Commands.argument("specie", ArgumentTypes.namespacedKey())
                                .suggests { _, builder ->
                                    Specie.speciesKeyString
                                        .filter { entry ->
                                            entry.lowercase().startsWith(builder.remainingLowerCase)
                                        }.forEach(builder::suggest);
                                    builder.buildFuture()
                                }.executes { ctx ->
                                    val key = ctx.getArgument("specie", NamespacedKey::class.java)
                                    val resolver =
                                        ctx.getArgument("players", PlayerSelectorArgumentResolver::class.java)
                                    val targets = resolver.resolve(ctx.source)
                                    val specie = Specie.getSpecieByKey(key)
                                    if (specie != null) {
                                        targets.forEach { player ->
                                            Specie.setSpecie(player, specie)
                                        }
                                    }
                                    Command.SINGLE_SUCCESS
                                }
                        )
                )
        )
        .then(
            Commands.literal("get")
                .then(
                    Commands.argument("player", ArgumentTypes.player())
                        .executes { ctx ->
                            val resolver = ctx.getArgument("player", PlayerSelectorArgumentResolver::class.java)
                            val target = resolver.resolve(ctx.source)
                            val specie = Specie.getSpecie(target.first())
                            ctx.source.sender.sendMessage(Component.text(specie?.key?.asString() ?: "no race"))
                            Command.SINGLE_SUCCESS
                        }
                )
        )
        .then(Commands.literal("gui").executes { ctx ->
            val executor = ctx.source.executor
            if (executor is Player) {
                executor.openInventory(ChooseMenu().inventory)
            }
            Command.SINGLE_SUCCESS
        }).build()!!
}