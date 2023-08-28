package kiraririria.serverscenes.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;

/**
 * [Called by ASM]
 * Patch Rendering of all available Morphs
 * Client Side only implicitly
 */

public class MorphRenderPatcher
{
    public static boolean isPatchable(EntityLivingBase npc, double d, double d1, double d2, float f, float f1)
    {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        return player.isSneaking();
    }

    public static void patchMorph(EntityLivingBase npc, double d, double d1, double d2, float f, float f1)
    {
    }
}