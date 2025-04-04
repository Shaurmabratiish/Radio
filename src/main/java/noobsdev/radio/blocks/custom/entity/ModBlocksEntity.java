package noobsdev.radio.blocks.custom.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import noobsdev.radio.Radio;
import noobsdev.radio.blocks.ModBlocks;

public class ModBlocksEntity {

    public static final BlockEntityType<RadioBlockEntity> RADIO_BLOCK_ENTITY_BLOCK_ENTITY_TYPE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Radio.MOD_ID, "radio_block_be"),
                    FabricBlockEntityTypeBuilder.create(RadioBlockEntity::new,
                            ModBlocks.RADIO_BLOCK).build());

    public static void registerBlocksEntities() {

    }

}
