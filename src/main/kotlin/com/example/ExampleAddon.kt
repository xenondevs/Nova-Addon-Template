package com.example

import xyz.xenondevs.nova.addon.Addon

class ExampleAddon : Addon() {
    
    override fun init() {
        // Called before some things like RecipeType are registered. So this is the only place to register them.
    }
    
    override fun onEnable() {
        // Called when the addon is enabled.
    }
    
    override fun onDisable() {
        // Called when the addon is disabled.
    }
}