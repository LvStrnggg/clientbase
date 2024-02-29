package dev.you.client;

import net.fabricmc.api.ModInitializer;

public final class Main implements ModInitializer {
    @Override
    public void onInitialize() {
        new Client();
    }
}
