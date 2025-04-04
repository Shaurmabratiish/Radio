package noobsdev.radio.blocks.player;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class PlayerData extends PlayerEntity {


    public PlayerData(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Override
    public boolean isSpectator() {
        return false;
    }

    @Override
    public boolean isCreative() {
        return false;
    }

    private static final HashMap<PlayerEntity, Boolean> playerRadioStatus = new HashMap<>();

    public void resetStatus() {
        playerRadioStatus.clear();
    }

    public void setRadioStatus(PlayerEntity player, boolean status) {
        playerRadioStatus.put(player, status);
    }

    public boolean getRadioStatus(PlayerEntity player) {
        return playerRadioStatus.getOrDefault(player, false);
    }

    public void removePlayer(PlayerEntity player) {
        playerRadioStatus.remove(player);
    }

    public static void register() {

    }

}
