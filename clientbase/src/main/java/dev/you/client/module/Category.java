package dev.you.client.module;

public enum Category {
    COMBAT("Combat"), MISC("Misc"), RENDER("Render"), CLIENT("Client");
    public final String name;
    Category(String name) {
        this.name = name;
    }
}
