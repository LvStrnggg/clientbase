package dev.you.client.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.you.client.utils.RenderUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(DrawContext context, float tickDelta, CallbackInfo ci) {
        RenderUtils.unscaledProjection();
        RenderUtils.scaledProjection();
        RenderSystem.applyModelViewMatrix();
    }
}
