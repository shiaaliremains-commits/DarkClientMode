package my.darkclientmod.client.mixin;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin {

    @Shadow protected int leftPos;
    @Shadow protected int topPos;
    @Shadow protected int imageWidth;
    @Shadow protected int imageHeight;

    // رسم طبقة تعتيم ناعمة وفخمة (Dark Mode Tint) خلف الصناديق والشنطة
    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void onApplyDarkTheme(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        // طبقة دارك مود داكنة بنسبة 45% تدمج مع الواجهة وتخليها أسود كلاسيكي فخم
        graphics.fill(leftPos, topPos, leftPos + imageWidth, topPos + imageHeight, 0x7705070B);
        
        // إطار نيون أسود داكن محيط بالواجهة
        graphics.fill(leftPos, topPos, leftPos + imageWidth, topPos + 1, 0xFF1B2230);
        graphics.fill(leftPos, topPos + imageHeight - 1, leftPos + imageWidth, topPos + imageHeight, 0xFF1B2230);
        graphics.fill(leftPos, topPos, leftPos + 1, topPos + imageHeight, 0xFF1B2230);
        graphics.fill(leftPos + imageWidth - 1, topPos, leftPos + imageWidth, topPos + imageHeight, 0xFF1B2230);
    }
}
