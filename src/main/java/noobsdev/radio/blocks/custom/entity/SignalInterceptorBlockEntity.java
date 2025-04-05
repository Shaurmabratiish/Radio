package noobsdev.radio.blocks.custom.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class SignalInterceptorBlockEntity extends BlockEntity {

    public int frequency;

    public SignalInterceptorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }


    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
    public void setFrequency(int freq) {
        this.frequency = freq;
    }
    public int getFrequency () {
        return frequency;
    }
}
