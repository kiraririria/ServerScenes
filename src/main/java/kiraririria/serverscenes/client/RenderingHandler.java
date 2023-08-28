package kiraririria.serverscenes.client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;

/**
 * [Called by ASM]
 * Handle Rendering of CustomNPCs
 * Client Side only implicitly
 */
public class RenderingHandler
{
    public static boolean isCanceledNPC(EntityCreature npc, double d, double d1, double d2, float f, float f1)
    {
        if (npc.getName().equals("Тест"))
        {
            return false;
        }
        return false;
    }

    public static void renderNPC(EntityCreature npc, double d, double d1, double d2, float f, float f1)
    {
    }

    public static boolean isCanceledShadows(Entity npc, double d, double d1, double d2, float f, float f1)
    {
        if (npc.getName().equals("Невидимка"))
        {
            return true;
        }
        else if (npc.getName().equals("БЕГИ!"))
        {
            return !Minecraft.getMinecraft().player.isSprinting();
        }
        return false;
    }

    public static void renderShadows(Entity npc, double d, double d1, double d2, float f, float f1)
    {
    }
}
