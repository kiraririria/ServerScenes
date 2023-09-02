package kiraririria.serverscenes.client;

import kiraririria.serverscenes.util.AbstractMorphUtil;
import mchorse.metamorph.api.morphs.AbstractMorph;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

/**
 * [Called by ASM]
 * Patch Rendering of all available Morphs
 * Client Side only implicitly
 */

public class MorphRenderPatcher
{
    @SideOnly(Side.CLIENT)
    public static boolean isPatchable(AbstractMorph morph, EntityLivingBase entity, double d, double d1, double d2, float f, float f1)
    {
        return !(AbstractMorphUtil.getRenderFor(morph).isEmpty()) && !(Arrays.asList(AbstractMorphUtil.getRenderFor(morph).split(";")).contains(Minecraft.getMinecraft().player.getName()));
    }

    @SideOnly(Side.CLIENT)
    public static boolean isPatchable(AbstractMorph morph, EntityPlayer entity, int i, int i1, float f, float f1)
    {
        return !(AbstractMorphUtil.getRenderFor(morph).isEmpty()) && !(Arrays.asList(AbstractMorphUtil.getRenderFor(morph).split(";")).contains(Minecraft.getMinecraft().player.getName()));
    }

    @SideOnly(Side.CLIENT)
    public static void patchMorph(AbstractMorph morph, EntityLivingBase entity, double d, double d1, double d2, float f, float f1)
    {
//        TileEntity tile = entity.getEntityWorld().getTileEntity(new BlockPos(-182, 66, 258));
//        if (!(tile instanceof TileConditionModel)){ return;}
//        TileConditionModel tcm = (TileConditionModel)tile;
//        if (!(tcm.entity.getMorph() instanceof EntityMorph)){return;}
//        EntityMorph morph = (EntityMorph) tcm.entity.getMorph();
//        try
//        {
//            Field fieldEntity = EntityMorph.class.getDeclaredField("entity");
//            Method methodSetupBodyPart = EntityMorph.class.getDeclaredMethod("setupBodyPart");
//            Method methodReplaceUserTexture = EntityMorph.class.getDeclaredMethod("replaceUserTexture");
//            Method methodRestoreMobTexture= EntityMorph.class.getDeclaredMethod("restoreMobTexture");
//            methodSetupBodyPart.setAccessible(true);
//            methodReplaceUserTexture.setAccessible(true);
//            methodRestoreMobTexture.setAccessible(true);
//            fieldEntity.setAccessible(true);
//            EntityLivingBase morpEntity = (EntityLivingBase) fieldEntity.get(morph);
//            if (entity != null) {
//                RenderLivingBase render = morph.renderer;
//                if (render == null) {
//                    morph.getEntity(entity.world);
//                    render = morph.renderer;
//                }
//
//                if (render != null) {
//                    morpEntity.rotationYaw = entity.rotationYaw;
//                    morpEntity.rotationPitch = entity.rotationPitch;
//                    morpEntity.rotationYawHead = entity.rotationYawHead;
//                    morpEntity.renderYawOffset = entity.renderYawOffset;
//                    morpEntity.prevRotationYaw = entity.prevRotationYaw;
//                    morpEntity.prevRotationPitch = entity.prevRotationPitch;
//                    morpEntity.prevRotationYawHead = entity.prevRotationYawHead;
//                    morpEntity.prevRenderYawOffset = entity.prevRenderYawOffset;
//                    morph.parts.initBodyParts();
//                    if (!morph.parts.parts.isEmpty()) {
//                        morph.setupLimbs();
//                    }
//
//                    boolean wasSneak = false;
//                    ModelBase model = render.getMainModel();
//                    if (model instanceof ModelBiped) {
//                        wasSneak = ((ModelBiped)model).isSneak;
//                        ((ModelBiped)model).isSneak = entity.isSneaking();
//                    }
//
//                    EntityMorph.renderEntity = entity;
//
//                    methodSetupBodyPart.invoke(morph);
//                    methodReplaceUserTexture.invoke(morph);
//                    GlStateManager.pushMatrix();
//                    GlStateManager.translate(d, d1, d2);
//                    GlStateManager.scale(morph.scale, morph.scale, morph.scale);
//                    if (morpEntity instanceof EntityDragon) {
//                        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
//                        Minecraft.getMinecraft().getRenderManager().renderEntity(morpEntity, 0.0, 0.0, 0.0, f, f1, false);
//                    } else {
//                        Minecraft.getMinecraft().getRenderManager().renderEntity(morpEntity, 0.0, 0.0, 0.0, f, f1, false);
//                    }
//
//                    GlStateManager.popMatrix();
//                    methodRestoreMobTexture.invoke(morph);
//                    if (model instanceof ModelBiped) {
//                        ((ModelBiped)model).isSneak = wasSneak;
//                    }
//
//                    EntityMorph.renderEntity = null;
//                }
//
//            }
//            methodSetupBodyPart.setAccessible(false);
//            methodReplaceUserTexture.setAccessible(false);
//            methodRestoreMobTexture.setAccessible(false);
//            fieldEntity.setAccessible(false);
//        }catch (NoSuchFieldException e)
//        {
//            e.printStackTrace();
//        }
//        catch (IllegalAccessException e)
//        {
//            throw new RuntimeException(e);
//        }
//        catch (NoSuchMethodException e)
//        {
//            throw new RuntimeException(e);
//        }
//        catch (InvocationTargetException e)
//        {
//            throw new RuntimeException(e);
//        }
    }

    @SideOnly(Side.CLIENT)
    public static void patchMorph(AbstractMorph morph, EntityPlayer entity, int i, int i1, float f, float f1)
    {
    }
}