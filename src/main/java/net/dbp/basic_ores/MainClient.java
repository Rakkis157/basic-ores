package net.dbp.basic_ores;

import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class MainClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(Main.TEST_FURNACE_SCREEN_HANDLER, TestFurnaceScreen::new);
    }
}