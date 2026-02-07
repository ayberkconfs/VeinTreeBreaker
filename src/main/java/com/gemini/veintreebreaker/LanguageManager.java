package com.gemini.veintreebreaker;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class LanguageManager {

    private final VeinTreeBreaker plugin;
    private FileConfiguration langConfig;
    private final Map<String, String> messages = new HashMap<>();

    public LanguageManager(VeinTreeBreaker plugin) {
        this.plugin = plugin;
        loadLanguages();
    }

    public void loadLanguages() {
        // Create folder if not exists
        File langFolder = new File(plugin.getDataFolder(), "languages");
        if (!langFolder.exists()) {
            langFolder.mkdirs();
        }

        // Save default resources
        String[] supportedLangs = {"en_US", "tr_TR", "de_DE", "es_ES", "fr_FR"};
        for (String lang : supportedLangs) {
            File file = new File(langFolder, "messages_" + lang + ".yml");
            if (!file.exists()) {
                plugin.saveResource("languages/messages_" + lang + ".yml", false);
            }
        }

        // Load selected language
        String selectedLang = plugin.getConfig().getString("settings.language", "en_US");
        File langFile = new File(langFolder, "messages_" + selectedLang + ".yml");

        // Fallback to default if selected doesn't exist
        if (!langFile.exists()) {
            plugin.getLogger().log(Level.WARNING, "Language file " + selectedLang + " not found! Falling back to en_US.");
            langFile = new File(langFolder, "messages_en_US.yml");
        }

        langConfig = YamlConfiguration.loadConfiguration(langFile);

        // Cache messages
        messages.clear();
        for (String key : langConfig.getKeys(false)) {
            messages.put(key, ChatColor.translateAlternateColorCodes('&', langConfig.getString(key)));
        }
        
        plugin.getLogger().info("Loaded language: " + selectedLang);
    }

    public String getMessage(String key) {
        String prefix = messages.getOrDefault("prefix", "");
        String msg = messages.getOrDefault(key, "Missing key: " + key);
        return prefix + msg;
    }

    public String getRawMessage(String key) {
        return messages.getOrDefault(key, key);
    }
}
