package com.fuzjajadrowa.donthidedays.debug;

import net.minecraft.client.gui.components.debug.DebugScreenDisplayer;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jetbrains.annotations.Nullable;

public class DebugEntryDays implements DebugScreenEntry {
    @Override
    public void display(DebugScreenDisplayer debugScreenDisplayer, @Nullable Level level, @Nullable LevelChunk levelChunk, @Nullable LevelChunk levelChunk2) {
        if (level instanceof ServerLevel serverLevel) {
            debugScreenDisplayer.addLine(String.format("Day %d", serverLevel.getDayCount()));
        }
    }
}