package dev.you.client;

import dev.you.client.event.EventManager;
import dev.you.client.module.ModuleManager;
import net.minecraft.client.MinecraftClient;

public final class Client {
    public static Client INSTANCE;
    public final EventManager eventManager;
    public static MinecraftClient mc;
    private final ModuleManager moduleManager;

    public Client() {
        INSTANCE = this;
        mc = MinecraftClient.getInstance();
        eventManager = new EventManager();
        moduleManager = new ModuleManager();
    }
    public ModuleManager getModuleManager() {
        return moduleManager;
    }
}
