package com.fuzjajadrowa.donthidedays.mixin;

import com.fuzjajadrowa.donthidedays.debug.DebugEntryDays;
import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.client.gui.components.debug.DebugScreenEntryStatus;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(DebugScreenEntries.class)
public abstract class DebugScreenEntriesMixin {
    @Shadow
    private static Identifier register(String string, DebugScreenEntry debugScreenEntry) {
        return null;
    }

    @Unique
    private static Identifier DAYS_ID;

    @Inject(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/debug/DebugScreenEntries;register(Ljava/lang/String;Lnet/minecraft/client/gui/components/debug/DebugScreenEntry;)Lnet/minecraft/resources/Identifier;", ordinal = 0)
    )
    private static void registerDaysSafely(CallbackInfo ci) {
        DAYS_ID = register("days", new DebugEntryDays());
    }

    private static Map<Object, Object> injectIntoProfiles(Object k1, Object v1, Object k2, Object v2) {

        Map<Identifier, DebugScreenEntryStatus> defaultMap = new HashMap<>((Map<Identifier, DebugScreenEntryStatus>) v1);
        if (DAYS_ID != null) {
            defaultMap.put(DAYS_ID, DebugScreenEntryStatus.ALWAYS_ON);
        }

        Map<Identifier, DebugScreenEntryStatus> perfMap = new HashMap<>((Map<Identifier, DebugScreenEntryStatus>) v2);
        if (DAYS_ID != null) {
            perfMap.put(DAYS_ID, DebugScreenEntryStatus.ALWAYS_ON);
        }

        return Map.of(k1, Map.copyOf(defaultMap), k2, Map.copyOf(perfMap));
    }
}