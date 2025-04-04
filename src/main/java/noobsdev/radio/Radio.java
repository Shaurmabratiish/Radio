package noobsdev.radio;

import net.fabricmc.api.ModInitializer;
import noobsdev.radio.blocks.ModBlocks;
import noobsdev.radio.blocks.custom.entity.ModBlocksEntity;
import noobsdev.radio.blocks.player.PlayerData;
import noobsdev.radio.items.ModItemGroups;

public class Radio implements ModInitializer {

    public static final String MOD_ID = "radio";

    @Override
    public void onInitialize() {
        PlayerData.register();
        ModBlocksEntity.registerBlocksEntities();
        ModBlocks.registerModBlocks();
        ModItemGroups.registerItemGroups();
    }
}
