package com.lightning.northstar.compat.jei.subcategory;

import com.lightning.northstar.compat.jei.animations.AnimatedEngraver;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.compat.jei.category.sequencedAssembly.SequencedAssemblySubCategory;
import com.simibubi.create.content.processing.sequenced.SequencedRecipe;
import net.minecraft.client.gui.GuiGraphics;

public class AssemblyEngraving extends SequencedAssemblySubCategory {

    private final AnimatedEngraver engraver = new AnimatedEngraver();

    public AssemblyEngraving() {
        super(25);
    }

    @Override
    public void draw(SequencedRecipe<?> recipe, GuiGraphics graphics, double mouseX, double mouseY, int index) {
        PoseStack pose = graphics.pose();

        pose.pushPose();
        pose.translate(-5.5, 46.3, 0);
        pose.scale(.625f, .625f, .625f);
        engraver.draw(graphics, getWidth() / 2, 30);
        pose.popPose();
    }

}
