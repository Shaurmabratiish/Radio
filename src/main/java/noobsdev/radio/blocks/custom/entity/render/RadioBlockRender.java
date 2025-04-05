package noobsdev.radio.blocks.custom.entity.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import noobsdev.radio.blocks.custom.entity.RadioBlockEntity;

public class RadioBlockRender implements BlockEntityRenderer<RadioBlockEntity> {
    @Override
    public void render(RadioBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        String render = "§a" + String.valueOf(entity.getFrequency());

        matrices.push();
        matrices.translate(0.5, 0.65, -0.05);

        // Уменьшаем текст, чтобы он соответствовал размеру блока
        matrices.scale(0.85f / 16.0f, 0.85f / 16.0f, 0.85f); // Масштабируем текст до размера блока

        // Поворот текста для правильного отображения
        matrices.scale(-1.0f, -1.0f, -1.0f);  // Поворот текста для правильного отображения

        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

        // Рендеринг текста
        textRenderer.draw(
                render,
                -textRenderer.getWidth(render) / 2.0f,
                0,
                0xFFFFFF,
                true,
                matrices.peek().getPositionMatrix(), // Используем текущую матрицу
                vertexConsumers,
                TextRenderer.TextLayerType.NORMAL,
                0,
                light
        );

        matrices.pop();


    }
    public RadioBlockRender(BlockEntityRendererFactory.Context contex) {

    }
}
