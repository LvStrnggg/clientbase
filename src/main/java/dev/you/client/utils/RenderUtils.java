package dev.you.client.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.systems.VertexSorter;
import dev.you.client.Client;
import org.joml.Matrix4f;

public final class RenderUtils {
    public static VertexSorter vertexSorter;
    public static boolean rendering3D = true;

    public static void unscaledProjection() {
        vertexSorter = RenderSystem.getVertexSorting();
        RenderSystem.setProjectionMatrix(new Matrix4f().setOrtho(0, Client.mc.getWindow().getFramebufferWidth(), Client.mc.getWindow().getFramebufferHeight(), 0, 1000, 21000), VertexSorter.BY_Z);
        rendering3D = false;
    }

    public static void scaledProjection() {
        RenderSystem.setProjectionMatrix(new Matrix4f().setOrtho(0, (float) (Client.mc.getWindow().getFramebufferWidth() / Client.mc.getWindow().getScaleFactor()), (float) (Client.mc.getWindow().getFramebufferHeight() / Client.mc.getWindow().getScaleFactor()), 0, 1000, 21000), vertexSorter);
        rendering3D = true;
    }
}
