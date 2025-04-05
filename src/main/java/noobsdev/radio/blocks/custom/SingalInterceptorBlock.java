package noobsdev.radio.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import noobsdev.radio.blocks.custom.entity.SignalInterceptorBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class SingalInterceptorBlock extends BlockWithEntity implements BlockEntityProvider {

    protected SingalInterceptorBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient) {
            SignalInterceptorBlockEntity blockEntity = (SignalInterceptorBlockEntity) world.getBlockEntity(pos);
            if (blockEntity != null) {
                blockEntity.markDirty();

                Random random = new Random();

                // Генерация случайного числа от 1 до 999
                int number = random.nextInt(999) + 1;

                blockEntity.setFrequency(number);// Убедитесь, что блок обновляется
            }
        }
    }
}
