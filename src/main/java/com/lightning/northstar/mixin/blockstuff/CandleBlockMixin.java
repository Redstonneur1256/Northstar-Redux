package com.lightning.northstar.mixin.blockstuff;

import com.lightning.northstar.world.OxygenStuff;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CandleBlock.class)
public class CandleBlockMixin {
    
    @Inject(method = "updateShape", at = @At("TAIL"), cancellable = true)
    public void updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, 
    LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos, CallbackInfoReturnable<BlockState> info) {
        try {
            if(pState.is(BlockTags.CANDLES)) {
                System.out.println(OxygenStuff.hasOxygen(pCurrentPos,((Level)pLevel).dimension()));
                if(!OxygenStuff.hasOxygen(pCurrentPos,((Level)pLevel).dimension()) && pState.getValue(CandleBlock.LIT)) {
                    if(!pState.canSurvive(pLevel, pCurrentPos)) {
                        info.setReturnValue(Blocks.AIR.defaultBlockState());
                        return;
                    }
                    pLevel.playSound(null, pCurrentPos, SoundEvents.CANDLE_EXTINGUISH, SoundSource.BLOCKS, 1, 0);
                    info.setReturnValue(Blocks.CANDLE.defaultBlockState().setValue(CandleBlock.CANDLES, pState.getValue(CandleBlock.CANDLES)).setValue(CandleBlock.WATERLOGGED,                 pState.getValue(CandleBlock.WATERLOGGED)).setValue(CandleBlock.LIT, false));
                }
            }
        } catch (Exception e) {
            //oops
        }
    }

}
