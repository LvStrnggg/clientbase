package dev.you.client.module;

import dev.you.client.Client;
import dev.you.client.event.EventManager;
import dev.you.client.module.setting.Setting;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Module {
    private List<Setting> settings = new ArrayList<>();
    public EventManager eventManager = Client.INSTANCE.eventManager;
    protected MinecraftClient mc = MinecraftClient.getInstance();
    private String name;
    private String description;
    private boolean enabled;
    private int key;
    private Category category;

    public Module(String name, String description, int key, Category category) {
        this.name = name;
        this.description = description;
        enabled = false;
        this.key = key;
        this.category = category;
    }

    public void toggle() {
        enabled = !enabled;
        if(enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }
    public void onEnable() {}
    public void onDisable() {}
    public List<Setting> getSettings() {
        return settings;
    }

    public void addSetting(Setting setting) {
        this.settings.add(setting);
    }
    public void addSettings(Setting... settings) {
        this.settings.addAll(Arrays.asList(settings));
    }
    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getKey() {
        return key;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if(enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
