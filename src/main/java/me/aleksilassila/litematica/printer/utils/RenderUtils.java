package me.aleksilassila.litematica.printer.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
//#if MC >= 260100
//$$ import net.minecraft.client.gui.GuiGraphicsExtractor;
//#else
import net.minecraft.client.gui.GuiGraphics;
//#endif

import java.awt.*;

// 这应该是渲染工具而不是字符串工具
public class RenderUtils {
    public static final Minecraft client = Minecraft.getInstance();
    private static PoseStack poseStack;
    //#if MC >= 260100
    //$$ private static GuiGraphicsExtractor guiGraphicsExtractor;
    //#else
    private static GuiGraphics guiGraphics;
    //#endif

    public static void initMatrix(PoseStack poseStack) {
        RenderUtils.poseStack = poseStack;
    }

    //#if MC >= 260100
    //$$ public static void initGuiGraphicsExtractor(GuiGraphicsExtractor extractor) {
    //$$     RenderUtils.guiGraphicsExtractor = extractor;
    //$$ }
    //#else
    public static void initGuiGraphics(GuiGraphics guiGraphics) {
        RenderUtils.guiGraphics = guiGraphics;
    }
    //#endif

    public static void ensureInitialized() {
        //#if MC >= 260100
        //$$ if (guiGraphicsExtractor == null) {
        //$$     throw new NullPointerException("GuiGraphicsExtractor is null! Call initGuiGraphicsExtractor first.");
        //$$ }
        //#elseif MC > 11904
        if (guiGraphics == null) {
            throw new NullPointerException("GuiGraphics is null! Call initGuiGraphics first.");
        }
        //#else
        //$$ if (poseStack == null) {
        //$$     throw new NullPointerException("PoseStack is null! Call initMatrix first.");
        //$$ }
        //#endif
    }

    public static void drawString(String text, int x, int y, Color color, boolean withShadow) {
        drawString(text, x, y, color, withShadow, false);
    }

    public static void drawString(String text, int x, int y, Color color, boolean withShadow, boolean centered) {
        if (centered) {
            x -= client.font.width(text) / 2;
        }
        ensureInitialized();
        //#if MC >= 260100
        //$$ guiGraphicsExtractor.text(client.font, text, x, y, color.getRGB(), withShadow);
        //#elseif MC > 11904
        guiGraphics.drawString(client.font, text, x, y, color.getRGB(), withShadow);
        //#else
        //$$ if (withShadow) {
        //$$    client.font.drawShadow(poseStack, text, x, y, color.getRGB());
        //$$ } else {
        //$$    client.font.draw(poseStack, text, x, y, color.getRGB());
        //$$ }
        //#endif
    }

    public static void fill(int x1, int y1, int x2, int y2, Color color) {
        ensureInitialized();
        //#if MC >= 260100
        //$$ guiGraphicsExtractor.fill(x1, y1, x2, y2, color.getRGB());
        //#elseif MC > 11904
        guiGraphics.fill(x1, y1, x2, y2, color.getRGB());
        //#else
        //$$ GuiComponent.fill(poseStack, x1, y1, x2, y2, color.getRGB());
        //#endif
    }
}
