package fr.azur.auria

import fr.azur.auria.commands.FixItemCommand
import fr.azur.auria.commands.SpeciesCommand
import fr.azur.auria.commands.TestCommand
import io.papermc.paper.plugin.bootstrap.BootstrapContext
import io.papermc.paper.plugin.bootstrap.PluginBootstrap
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import io.papermc.paper.registry.data.EnchantmentRegistryEntry
import io.papermc.paper.registry.event.RegistryEvents
import io.papermc.paper.registry.keys.EnchantmentKeys
import io.papermc.paper.registry.keys.tags.EnchantmentTagKeys
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import org.bukkit.inventory.EquipmentSlotGroup

class AuriaBootstrap : PluginBootstrap {
    override fun bootstrap(context: BootstrapContext) {
        val lifecycleManager = context.lifecycleManager

        lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS) { commands ->
            commands.registrar().register(FixItemCommand.command)
            commands.registrar().register(SpeciesCommand.command)
            commands.registrar().register(TestCommand.command)
        }

        lifecycleManager.registerEventHandler(
            RegistryEvents.ENCHANTMENT.freeze().newHandler { event ->
                event.registry()
                    .register(EnchantmentKeys.create(Key.key("auria:water_protection"))) { b ->
                        b.description(Component.text("Water Protection"))
                            .supportedItems(event.getOrCreateTag(ItemTypeTagKeys.ENCHANTABLE_ARMOR))
                            .exclusiveWith(event.getOrCreateTag(EnchantmentTagKeys.EXCLUSIVE_SET_ARMOR))
                            .weight(5)
                            .maxLevel(4)
                            .minimumCost(EnchantmentRegistryEntry.EnchantmentCost.of(10, 8))
                            .maximumCost(EnchantmentRegistryEntry.EnchantmentCost.of(18, 8))
                            .anvilCost(2)
                            .activeSlots(EquipmentSlotGroup.ARMOR)
                    }
            })
    }
}