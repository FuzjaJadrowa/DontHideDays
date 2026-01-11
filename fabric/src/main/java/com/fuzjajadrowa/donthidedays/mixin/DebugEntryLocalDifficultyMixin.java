package com.fuzjajadrowa.donthidedays.mixin;

import net.minecraft.client.gui.components.debug.DebugEntryLocalDifficulty;
import net.minecraft.client.gui.components.debug.DebugScreenDisplayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DebugEntryLocalDifficulty.class)
public class DebugEntryLocalDifficultyMixin {
    @Redirect(
            method = "display",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/debug/DebugScreenDisplayer;addLine(Ljava/lang/String;)V")
    )
    private void modifyLocalDifficultyLine(DebugScreenDisplayer displayer, String originalText) {
        if (originalText.contains(" (Day")) {
            String newText = originalText.split(" \\(Day")[0];
            displayer.addLine(newText);
        } else {
            displayer.addLine(originalText);
        }
    }
}