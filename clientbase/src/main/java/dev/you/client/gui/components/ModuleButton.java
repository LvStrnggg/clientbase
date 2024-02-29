package dev.you.client.gui.components;

import dev.you.client.gui.Window;
import dev.you.client.gui.components.settings.CheckBox;
import dev.you.client.gui.components.settings.ModeBox;
import dev.you.client.gui.components.settings.RenderableSetting;
import dev.you.client.gui.components.settings.Slider;
import dev.you.client.module.Module;
import dev.you.client.module.setting.BooleanSetting;
import dev.you.client.module.setting.ModeSetting;
import dev.you.client.module.setting.NumberSetting;
import dev.you.client.module.setting.Setting;
import dev.you.client.utils.TextRenderer;
import dev.you.client.utils.Utils;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class ModuleButton {
    public List<RenderableSetting> settings = new ArrayList<>();
    public dev.you.client.gui.Window parent;
    public Module module;
    public int offset;
    public boolean extended;
    public int settingOffset;
    public ModuleButton(Window parent, Module module, int offset) {
        this.parent = parent;
        this.module = module;
        this.offset = offset;
        this.extended = false;

        settingOffset = parent.getHeight();
        for(Setting setting : module.getSettings()) {
            if(setting instanceof BooleanSetting booleanSetting) {
                settings.add(new CheckBox(this, booleanSetting, settingOffset));
            } else if(setting instanceof NumberSetting numberSetting) {
                settings.add(new Slider(this, numberSetting, settingOffset));
            } else if(setting instanceof ModeSetting modeSetting) {
                settings.add(new ModeBox(this, modeSetting, settingOffset));
            }
            settingOffset += parent.getHeight();
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(parent.getX(), parent.getY() + offset, parent.getX() +  parent.getWidth(), parent.getY() + parent.getHeight() + offset, new Color(35,35,35,175).getRGB());
        TextRenderer.drawCenteredMinecraftText(module.getName(), context, parent.getX() + (parent.getWidth()/2), parent.getY() + offset + 8, module.isEnabled() ? Utils.getMainColor(255).getRGB() : Color.WHITE.getRGB());

        if(isHovered(mouseX, mouseY)) {
            context.fill(parent.getX(), parent.getY() + offset, parent.getX() +  parent.getWidth(), parent.getY() + parent.getHeight() + offset, new Color(255,255,255,10).getRGB());
        }

        if(extended) {
            for(RenderableSetting renderableSetting : settings) {
                renderableSetting.render(context, mouseX, mouseY, delta);
            }
        }
    }

    public void mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if(extended) {
            for (RenderableSetting renderableSetting : settings) {
                renderableSetting.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(isHovered(mouseX, mouseY)) {
            if(button == 0) {
                module.toggle();
            }

            if(button == 1) {
                extended = !extended;
                parent.updateButtons();
            }
        }
        if(extended) {
            for (RenderableSetting renderableSetting : settings) {
                renderableSetting.mouseClicked(mouseX, mouseY, button);
            }
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        for(RenderableSetting renderableSetting : settings) {
            renderableSetting.mouseReleased(mouseX, mouseY, button);
        }
    }

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > parent.getX()
                && mouseX < parent.getX() + parent.getWidth()
                && mouseY > parent.getY() + offset
                && mouseY < parent.getY() + offset + parent.getHeight();
    }
}
