package kiraririria.serverscenes.core;

import kiraririria.serverscenes.Serverscenes;
import kiraririria.serverscenes.core.transformers.AbstractMorphTransformer;
import kiraririria.serverscenes.core.transformers.AbstractMorphUtilTransformer;
import kiraririria.serverscenes.core.transformers.RenderCustomNpcTransformer;
import kiraririria.serverscenes.core.transformers.RenderMorphTransformer;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import java.util.Iterator;

public class SSCoreClassTransformer extends CoreClassTransformer
{
    public static final String RenderCustomNpcName = "noppes.npcs.client.renderer.RenderNPCInterface";
    public static final String AbstractMorphName = "mchorse.metamorph.api.morphs.AbstractMorph";
    public static final String AbstractMorphUtilName = "kiraririria.serverscenes.util.AbstractMorphUtil";

    private RenderCustomNpcTransformer renderCustomNpcTransformer = new RenderCustomNpcTransformer();
    private AbstractMorphTransformer abstractMorphTransformer = new AbstractMorphTransformer();

    private RenderMorphTransformer renderMorphTransformer = new RenderMorphTransformer();
    private AbstractMorphUtilTransformer abstractMorphUtilTransformer = new AbstractMorphUtilTransformer();

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass)
    {
        String regex = ".*\\bmorphs\\.[a-zA-Z]+";
        if (name.equals(AbstractMorphUtilName))
        {
            Serverscenes.log("<]---AbstractMorphUtil---[>");
            return this.abstractMorphUtilTransformer.transform(name, basicClass);
        }
        if (name.equals(AbstractMorphName))
        {
            Serverscenes.log("<]---AbstractMorph---[>");
            return this.abstractMorphTransformer.transform(name, basicClass);
        }
        if (name.matches(regex))
        {
            Serverscenes.log("<]---" + name + "---[>");
            return this.renderMorphTransformer.transform(name, basicClass);
        }
        if (name.equals(RenderCustomNpcName))
        {
            Serverscenes.log("<]---RenderNPCInterface---[>");
            return this.renderCustomNpcTransformer.transform(name, basicClass);
        }
        return basicClass;
    }
    public static void debugInstructions(InsnList list)
    {
        debugInstructions(list, Integer.MAX_VALUE);
    }

    public static void debugInstructions(InsnList list, int max)
    {
        Iterator<AbstractInsnNode> nodes = list.iterator();

        int i = 0;

        while (nodes.hasNext())
        {
            AbstractInsnNode node = nodes.next();

            System.out.println("Offset: " + i + " " + node.getClass().getSimpleName() + " " + debugNode(node));

            if (i >= max)
            {
                break;
            }

            i++;
        }
    }

    public static String debugNode(AbstractInsnNode node)
    {
        if (node instanceof LabelNode)
        {
            return "label " + ((LabelNode) node).getLabel().toString();
        }
        else if (node instanceof LineNumberNode)
        {
            return "line " + String.valueOf(((LineNumberNode) node).line);
        }
        else if (node instanceof MethodInsnNode)
        {
            MethodInsnNode method = (MethodInsnNode) node;

            return method.getOpcode() + " " + method.owner + "." + method.name + ":" + method.desc;
        }
        else if (node instanceof FieldInsnNode)
        {
            FieldInsnNode field = (FieldInsnNode) node;

            return field.getOpcode() + " " + field.owner + "." + field.name + ":" + field.desc;
        }
        else if (node instanceof VarInsnNode)
        {
            VarInsnNode var = (VarInsnNode) node;

            return "opcode " + var.getOpcode() + " var " + var.var;
        }
        else if (node instanceof LdcInsnNode)
        {
            LdcInsnNode ldc = (LdcInsnNode) node;

            return "LDC " + ldc.cst.toString();
        }

        return "opcode " + String.valueOf(node.getOpcode());
    }
}