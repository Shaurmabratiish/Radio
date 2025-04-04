package noobsdev.radio.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import noobsdev.radio.Radio;
import noobsdev.radio.blocks.custom.RadioBlock;

public class ModBlocks {

    public static final Block RADIO_BLOCK = registerBlock("radio_block",
            new RadioBlock(FabricBlockSettings.copyOf(Blocks.GLASS).sounds(BlockSoundGroup.SOUL_SAND)));

    //public static final Block OMEGA_RUBY_BLOCK = registerBlock("omega_ruby_block",
      //      new SoundBlock(FabricBlockSettings.copyOf(Blocks.GLASS).sounds(BlockSoundGroup.WOOD)));



    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name,block);
        return Registry.register(Registries.BLOCK, new Identifier(Radio.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Radio.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {

    }

}