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
import org.spongepowered.asm.mixin.injection.Redirect;
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

    @Redirect(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Ljava/util/Map;of(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/Map;")
    )
    private static Map<Object, Object> injectIntoDefaultProfile(
            Object k1, Object v1, Object k2, Object v2, Object k3, Object v3,
            Object k4, Object v4, Object k5, Object v5, Object k6, Object v6,
            Object k7, Object v7, Object k8, Object v8, Object k9, Object v9) {

        Map<Object, Object> map = new HashMap<>();
        map.put(k1, v1); map.put(k2, v2); map.put(k3, v3);
        map.put(k4, v4); map.put(k5, v5); map.put(k6, v6);
        map.put(k7, v7); map.put(k8, v8); map.put(k9, v9);

        if (DAYS_ID != null) {
            map.put(DAYS_ID, DebugScreenEntryStatus.ALWAYS_ON);
        }
        return Map.copyOf(map);
    }

    @Redirect(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Ljava/util/Map;of(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/Map;")
    )
    private static Map<Object, Object> injectIntoPerformanceProfile(
            Object k1, Object v1, Object k2, Object v2, Object k3, Object v3,
            Object k4, Object v4, Object k5, Object v5) {

        Map<Object, Object> map = new HashMap<>();
        map.put(k1, v1); map.put(k2, v2); map.put(k3, v3);
        map.put(k4, v4); map.put(k5, v5);

        if (DAYS_ID != null) {
            map.put(DAYS_ID, DebugScreenEntryStatus.ALWAYS_ON);
        }
        return Map.copyOf(map);
    }
}