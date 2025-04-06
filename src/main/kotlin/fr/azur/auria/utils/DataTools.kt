package fr.azur.auria.utils

import net.minecraft.resources.ResourceLocation

object DataTools {
    fun getIdentifier(name:String): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath("auria",name)
    }
}