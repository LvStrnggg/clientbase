package dev.you.client.gui;

import dev.you.client.Client;
import dev.you.client.gui.components.ModuleButton;
import dev.you.client.gui.components.settings.RenderableSetting;
import dev.you.client.module.Category;
import dev.you.client.module.Module;
import dev.you.client.utils.TextRenderer;
import dev.you.client.utils.Utils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public final class Window {
    public List<ModuleButton> moduleButtons = new ArrayList<>();
    protected MinecraftClient mc = Client.mc;
    private int x, y;
    private final int width, height;
    private final Category category;
    public boolean dragging, extended;
    int dragX, dragY;
    public Window(int x, int y, int width, int height, Category category) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.dragging = false;
        this.extended = true;
        this.height = height;
        this.category = category;

        int offset = height;
        for(Module module : Client.INSTANCE.getModuleManager().getModulesInCategory(category)) {
            moduleButtons.add(new ModuleButton(this, module, offset));
            offset += height;
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fillGradient(x, y, x + width, y + height, Utils.getMainColor(255).darker().getRGB(), Utils.getMainColor(255).darker().darker().darker().getRGB());
        TextRenderer.drawCenteredMinecraftText(category.name, context, x + (width/2), y + 6, Color.WHITE.getRGB());
        TextRenderer.drawMinecraftText(extended ? "+" : "-", context, x + getWidth() - 20, y + 6, Color.WHITE.getRGB());

        if(!extended) return;

        for(ModuleButton moduleButton : moduleButtons) {
            moduleButton.render(context, mouseX, mouseY, delta);
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(isHovered(mouseX, mouseY)) {
            switch (button) {
                case 0: {
                    dragging = true;
                    dragX = (int) (mouseX - x);
                    dragY = (int) (mouseY - y);
                    break;
                }
                case 1: {
                    extended = !extended;
                    break;
                }
            }
        }

        for(ModuleButton moduleButton : moduleButtons) {
            moduleButton.mouseClicked(mouseX, mouseY, button);
        }
    }

    public void mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        for(ModuleButton moduleButton : moduleButtons) {
            moduleButton.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }
    }

    public void updateButtons() {
        int offset = height;

        for(ModuleButton moduleButton : moduleButtons) {
            moduleButton.offset = offset;
            offset += height;

            if(moduleButton.extended) {
                for(RenderableSetting renderableSetting : moduleButton.settings) {
                    offset += height;
                }
            }
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        if(button == 0 && dragging) {
            dragging = false;
        }

        for(ModuleButton moduleButton : moduleButtons) {
            moduleButton.mouseReleased(mouseX, mouseY, button);
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return (mouseX > x && mouseX < x + width) && (mouseY > y && mouseY < y + height);
    }

    public void updatePosition(double mouseX, double mouseY) {
        if(dragging) {
            x = (int) (mouseX - dragX);
            y = (int) (mouseY - dragY);
        }
    }
}
