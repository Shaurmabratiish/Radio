package noobsdev.radio.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import noobsdev.radio.Radio;
import noobsdev.radio.blocks.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup RUBY_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(Radio.MOD_ID, "radio"), FabricItemGroup.builder().displayName(Text.translatable("itemgroup.radio")).icon(() -> new ItemStack(ModBlocks.RADIO_BLOCK)).entries((displayContext, entries) -> {

        entries.add(ModBlocks.RADIO_BLOCK);
    }).build());


    public static void registerItemGroups() {

    }
}

