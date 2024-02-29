package dev.you.client.gui;

import dev.you.client.Client;
import dev.you.client.module.Category;
import dev.you.client.module.modules.client.ClickGUI;
import dev.you.client.utils.RenderUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

import static dev.you.client.Client.mc;

public final class ClickGui extends Screen {
    List<Window> windows = new ArrayList<>();
    public ClickGui() {
        super(Text.empty());

        int offsetX = 50;
        for(Category category : Category.values()) {
            windows.add(new Window(offsetX, 40, 240, 30, category));
            offsetX += 260;
        }

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        RenderUtils.unscaledProjection();
        mouseX *= (int) MinecraftClient.getInstance().getWindow().getScaleFactor();
        mouseY *= (int) MinecraftClient.getInstance().getWindow().getScaleFactor();
        super.render(context, mouseX, mouseY, delta);

        for(Window window : windows) {
            window.render(context, mouseX, mouseY, delta);
            window.updatePosition(mouseX, mouseY);

            if(window.dragging) {
                if (window.getX() < 0) {
                    window.setX(0);
                }

                if (window.getY() < 0) {
                    window.setY(0);
                }

                if (window.getX() + window.getWidth() > mc.getWindow().getWidth()) {
                    window.setX(window.getX() - window.getWidth());
                }

                if (window.getY() + window.getHeight() > mc.getWindow().getHeight()) {
                    window.setY(window.getY() - window.getHeight());
                }
            }
        }

        RenderUtils.scaledProjection();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        mouseX *= (int) MinecraftClient.getInstance().getWindow().getScaleFactor();
        mouseY *= (int) MinecraftClient.getInstance().getWindow().getScaleFactor();

        for(Window window : windows) {
            window.mouseClicked(mouseX, mouseY, button);
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        mouseX *= (int) MinecraftClient.getInstance().getWindow().getScaleFactor();
        mouseY *= (int) MinecraftClient.getInstance().getWindow().getScaleFactor();
        for(Window window : windows) {
            window.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public void close() {
        Client.INSTANCE.getModuleManager().getModule(ClickGUI.class).setEnabled(false);
        super.close();
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        mouseX *= (int) MinecraftClient.getInstance().getWindow().getScaleFactor();
        mouseY *= (int) MinecraftClient.getInstance().getWindow().getScaleFactor();

        for(Window window : windows) {
            window.mouseReleased(mouseX, mouseY, button);
        }

        return super.mouseReleased(mouseX, mouseY, button);
    }
}
