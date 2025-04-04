package noobsdev.radio.blocks.custom.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import noobsdev.radio.blocks.player.PlayerData;

import java.util.ArrayList;
import java.util.List;

public class RadioBlockEntity  extends BlockEntity {

    private String author;

    public RadioBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocksEntity.RADIO_BLOCK_ENTITY_BLOCK_ENTITY_TYPE, pos, state);
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putString("Author", author);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world != null && !world.isClient) {

            List<PlayerEntity> playersInRadius = getPlayersInRadius(world, getPos(), 5.0);
            // Здесь вы можете делать что-то с игроками
            for (PlayerEntity player : playersInRadius) {
                player.sendMessage(Text.of("Вы находитесь рядом с радио!"), false);
                PlayerData data = (PlayerData) player;

                data.resetStatus();

                data.setRadioStatus(player, true);

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

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.author = nbt.getString("Author");
    }
}
