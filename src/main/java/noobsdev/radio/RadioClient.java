package noobsdev.radio;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import noobsdev.radio.blocks.custom.entity.ModBlocksEntity;
import noobsdev.radio.blocks.custom.entity.render.RadioBlockRender;

public class RadioClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(ModBlocksEntity.RADIO_BLOCK_ENTITY_BLOCK_ENTITY_TYPE, RadioBlockRender::new);
    }
}
