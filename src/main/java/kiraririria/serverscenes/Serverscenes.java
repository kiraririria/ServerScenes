package kiraririria.serverscenes;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Serverscenes.MODID, name = Serverscenes.NAME, version = Serverscenes.VERSION)
public class Serverscenes
{
    public static final String MODID = "serverscenes";
    public static final String NAME = "ServerScenes";
    public static final String VERSION = "1.0";

    /**
     * [Handled by ASM]
     * Name of Morphs' Nbt tag
     * Contains the names of players for whom the scene is rendered
     */
    public static final String NBT_RENDER_FOR = "renderFor";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }

    /**
     * Ty krasivaya vnutri, nichego ne govori..
     * I tantsuy pod yevrobit! I tantsuy pod yevrobit!
     */
    public static void log(String message)
    {
        System.out.println("\u001B[35mSSCOREMOD: " + message + "\u001B[0m");
    }
}
