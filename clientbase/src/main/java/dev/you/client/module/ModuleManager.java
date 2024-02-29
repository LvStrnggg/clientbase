package dev.you.client.module;

import dev.you.client.module.modules.client.ClickGUI;

import java.util.ArrayList;
import java.util.List;

public final class ModuleManager {

    public ModuleManager() {
        addModules();
    }
    private List<Module> modules = new ArrayList<>();

    public List<Module> getEnabledModules() {
        List<Module> enabled = new ArrayList<>();

        for(Module module : modules) {
            if(module.isEnabled()) {
                enabled.add(module);
            }
        }

        return enabled;
    }

    public List<Module> getModulesInCategory(Category category) {
        List<Module> categoryModules = new ArrayList<>();

        for(Module module : modules) {
            if(module.getCategory().equals(category)) {
                categoryModules.add(module);
            }
        }

        return categoryModules;
    }

    public List<Module> getModules() {
        return modules;
    }

    public <T extends Module> T getModule(Class<T> moduleClass) {
        for(Module module : modules) {
            if(moduleClass.isAssignableFrom(module.getClass())) {
                return (T) module;
            }
        }

        return null;
    }

    public void addModules() {
        //Combat

        //Misc
        //Render

        //Client
        add(new ClickGUI());
    }

    public void add(Module module) {
        modules.add(module);
    }
}
