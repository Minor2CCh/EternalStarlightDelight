package com.minor2cch.eternalstarlightdelight.config;

import com.minor2cch.eternalstarlightdelight.EternalStarlightDelight;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ESDConfigLoader {
    private ESDConfigLoader() {}
    private static final File DIR = ESDPlatform.INSTANCE.getConfigPath().toFile();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILENAME = EternalStarlightDelight.MOD_ID +".json";
    private static final Path CONFIG_PATH = Path.of(new File(DIR,FILENAME).getPath());
    private static ESDConfig modConfig;
    public static void load(){
        if (Files.exists(CONFIG_PATH)) {
            try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
                modConfig = GSON.fromJson(reader, ESDConfig.class);
            } catch (Exception e) {
                System.err.println("Failed to load config: " + e.getMessage());
                modConfig = new ESDConfig();
            }

            try{
                modConfig.fillDefaults();
            } catch (Exception e) {
                System.err.println("Failed to load config: " + e.getMessage());
                modConfig = new ESDConfig();
            }

        } else {
            modConfig = new ESDConfig();
        }
        save();

    }
    public static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {
                GSON.toJson(modConfig, writer);
            }
        } catch (IOException e) {
            System.err.println("Failed to save config: " + e.getMessage());
        }
    }
    public static ESDConfig getConfig() {
        return modConfig;
    }
}
