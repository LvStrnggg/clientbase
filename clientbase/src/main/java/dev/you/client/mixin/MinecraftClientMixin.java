package dev.you.client.mixin;

import dev.you.client.event.EventManager;
import dev.you.client.event.events.TickListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow @Nullable public ClientWorld world;

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if(world != null) {
            TickListener.TickEvent event = new TickListener.TickEvent();

            EventManager.fire(event);
        }
    }
}
