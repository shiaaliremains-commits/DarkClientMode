package my.darkclientmod.client.mixin;

import net.minecraft.client.gui.components.AbstractWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractWidget.class)
public abstract class AbstractWidgetMixin {

    // تحويل لون النص إلى الذهبي النيون #FFD700 مثل كتب التطويرات عند مرور الماوس
    @Inject(method = {"getLabelTextColor", "getFGColor"}, at = @At("HEAD"), cancellable = true, require = 0)
    private void onGetButtonTextColor(CallbackInfoReturnable<Integer> cir) {
        AbstractWidget widget = (AbstractWidget) (Object) this;
        if (widget.active && widget.isHoveredOrFocused()) {
            cir.setReturnValue(0xFFFFD700);
        }
    }
}
