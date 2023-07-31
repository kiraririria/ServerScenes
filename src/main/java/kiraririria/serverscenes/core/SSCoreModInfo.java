package kiraririria.serverscenes.core;

import net.minecraftforge.fml.common.DummyModContainer;

public class SSCoreModInfo extends DummyModContainer
{

    @Override
    public String getName()
    {
        return "SS Core mod";
    }

    @Override
    public String getModId()
    {
        return "sscore";
    }

    @Override
    public Object getMod()
    {
        return null;
    }

    @Override
    public String getVersion()
    {
        return "%VERSION%";
    }

}