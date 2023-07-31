package kiraririria.serverscenes.core;

import kiraririria.serverscenes.Serverscenes;
import kiraririria.serverscenes.core.transformers.RenderCustomNpcTransformer;
import org.objectweb.asm.tree.*;

import java.util.Iterator;

public class SSCoreClassTransformer extends CoreClassTransformer
{
    public static final String RenderCustomNpcName = "noppes.npcs.client.renderer.RenderNPCInterface";

    private RenderCustomNpcTransformer renderCustomNpcTransformer = new RenderCustomNpcTransformer();

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass)
    {
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