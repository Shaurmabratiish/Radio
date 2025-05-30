package noobsdev.radio.blocks.custom.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import noobsdev.radio.blocks.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RadioBlockEntity  extends BlockEntity {

    private String author;
    private int frequency;

    public RadioBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocksEntity.RADIO_BLOCK_ENTITY_BLOCK_ENTITY_TYPE, pos, state);
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setFrequency(int freq) {
        this.frequency = freq;
    }
    public int getFrequency () {
        return frequency;
    }


    public String getAuthor() {
        return author;
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putString("Author", author);
        nbt.putInt("Frequency", frequency);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world != null && !world.isClient) {

            List<PlayerEntity> playersInRadius = getPlayersInRadius(world, getPos(), 5.0);
            // Здесь вы можете делать что-то с игроками
            for (PlayerEntity player : playersInRadius) {

                BlockEntity entity = world.getBlockEntity(pos);

                if (entity instanceof RadioBlockEntity radioBlockEntity) {

                    int n = radioBlockEntity.getFrequency();

                    player.sendMessage(Text.of(" §fУказанная частота: §b" + n + "§f."), true);
                    resetStatus();
                    setRadioStatus(player, true);

                }

            }
        }
    }

    private List<PlayerEntity> getPlayersInRadius(World world, BlockPos blockPos, double radius) {
        List<PlayerEntity> playersInRadius = new ArrayList<>();

        for (PlayerEntity player : world.getPlayers()) {
            if (player.squaredDistanceTo(blockPos.getX(), blockPos.getY(), blockPos.getZ()) <= radius * radius) {
                playersInRadius.add(player);
            }
        }

        return playersInRadius;
    }

    private static void setRadioStatus(PlayerEntity player, boolean status) {
        ModBlocks.playerRadioStatus.put(player, status);
    }

    private static boolean getRadioStatus(PlayerEntity player) {
        return ModBlocks.playerRadioStatus.getOrDefault(player, false);
    }

    private static void resetStatus() {
        ModBlocks.playerRadioStatus.clear();
    }

    private static void removePlayer(PlayerEntity player) {
        ModBlocks.playerRadioStatus.remove(player);
    }


    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.author = nbt.getString("Author");
        this.frequency = nbt.getInt("Frequency");
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
