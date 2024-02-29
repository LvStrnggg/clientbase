package dev.you.client.module.modules.client;

import dev.you.client.gui.ClickGui;
import dev.you.client.module.Category;
import dev.you.client.module.Module;
import dev.you.client.module.setting.NumberSetting;
import org.lwjgl.glfw.GLFW;

public class ClickGUI extends Module {
    public static final NumberSetting red = new NumberSetting("Red",0,255,153,1);
    public static final NumberSetting green = new NumberSetting("Green", 0, 255, 0,1);
    public static final NumberSetting blue = new NumberSetting("Blue",0,255,0,1);
    public ClickGUI() {
        super("Click Gui", "Click Gui", GLFW.GLFW_KEY_RIGHT_CONTROL, Category.CLIENT);
        addSettings(red, green, blue);
    }

    @Override
    public void onEnable() {
        mc.setScreen(new ClickGui());
        super.onEnable();
    }

    @Override
    public void onDisable() {
        if(mc.currentScreen instanceof ClickGui) {
            mc.setScreen(null);
        }
        super.onDisable();
    }
}
