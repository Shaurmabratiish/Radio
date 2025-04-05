package noobsdev.radio.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import noobsdev.radio.blocks.custom.entity.ModBlocksEntity;
import noobsdev.radio.blocks.custom.entity.RadioBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class RadioBlock extends BlockWithEntity implements BlockEntityProvider {


    public RadioBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if(!world.isClient) {
            BlockEntity entity = world.getBlockEntity(pos);

            if(entity instanceof RadioBlockEntity radioBlockEntity) {
                if(player.isSneaking()) {
                    radioBlockEntity.setFrequency(radioBlockEntity.getFrequency() - 1);
                }else {
                    radioBlockEntity.setFrequency(radioBlockEntity.getFrequency() + 1);
                }

                world.updateListeners(pos, state, state, 3);

                radioBlockEntity.markDirty();
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient) {
            RadioBlockEntity blockEntity = (RadioBlockEntity) world.getBlockEntity(pos);
            if (blockEntity != null) {
                blockEntity.setAuthor(placer.getName().getString());
                blockEntity.markDirty();

                Random random = new Random();

                // Генерация случайного числа от 1 до 999
                int number = random.nextInt(999) + 1;

                blockEntity.setFrequency(number);// Убедитесь, что блок обновляется
            }
        }
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RadioBlockEntity(pos,state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlocksEntity.RADIO_BLOCK_ENTITY_BLOCK_ENTITY_TYPE,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1,pos,state1));
    }
}
