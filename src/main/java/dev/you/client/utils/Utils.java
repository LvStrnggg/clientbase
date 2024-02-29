package dev.you.client.utils;

import dev.you.client.module.modules.client.ClickGUI;
import dev.you.client.Client;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import java.awt.*;

public final class Utils {
    public static Entity findNearestEntity(PlayerEntity toPlayer, float radius, boolean seeOnly) {
        float mr = Float.MAX_VALUE;
        Entity entity = null;

        assert Client.mc.world != null;
        for(Entity e : Client.mc.world.getEntities()) {
            float d = e.distanceTo(toPlayer);

            if(e != toPlayer && d <= radius && Client.mc.player.canSee(e) == seeOnly) {
                if(d < mr) {
                    mr = d;
                    entity = e;
                }
            }
        }
        return entity;
    }

    public static Color getMainColor(int alpha) {
        int red = ClickGUI.red.getValueInt();
        int green = ClickGUI.green.getValueInt();
        int blue = ClickGUI.blue.getValueInt();

        return new Color(red, green, blue, alpha);
    }
}
