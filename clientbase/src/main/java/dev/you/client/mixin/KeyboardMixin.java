package dev.you.client.mixin;

import dev.you.client.Client;
import dev.you.client.module.Module;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "onKey", at = @At("HEAD"))
    private void onPress(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if(window == this.client.getWindow().getHandle()) {
            if(this.client.currentScreen == null) {
                for(Module module : Client.INSTANCE.getModuleManager().getModules()) {
                    if(key == module.getKey() && action == GLFW.GLFW_PRESS) {
                        module.toggle();
                    }
                }
            }
        }
    }
}
