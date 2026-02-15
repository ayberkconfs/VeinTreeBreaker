package com.gemini.veintreebreaker;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class UpdateManager {

    private final VeinTreeBreaker plugin;
    private final String PROJECT_SLUG = "veintreebreaker"; 
    
    private String latestVersionNumber = null;
    private boolean updateAvailable = false;

    public UpdateManager(VeinTreeBreaker plugin) {
        this.plugin = plugin;
    }

    public boolean isUpdateAvailable() {
        return updateAvailable;
    }

    public String getLatestVersion() {
        return latestVersionNumber;
    }

    public void checkForUpdate(CommandSender sender, boolean silent) {
        if (!silent) sender.sendMessage(plugin.getLanguageManager().getMessage("update-checking"));
        
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                // Modrinth API: Get Project Versions
                URL url = new URL("https://api.modrinth.com/v2/project/" + PROJECT_SLUG + "/version");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("User-Agent", "VeinTreeBreaker-Plugin");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                if (connection.getResponseCode() == 200) {
                    InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                    JsonArray versions = JsonParser.parseReader(reader).getAsJsonArray();
                    
                    if (versions.size() > 0) {
                        JsonObject latestVersion = versions.get(0).getAsJsonObject();
                        String versionNumber = latestVersion.get("version_number").getAsString();
                        this.latestVersionNumber = versionNumber;
                        
                        // Versiyon kontrolü
                        if (!versionNumber.equalsIgnoreCase(plugin.getDescription().getVersion())) {
                            this.updateAvailable = true;
                            if (!silent) sender.sendMessage(plugin.getLanguageManager().getMessage("update-found").replace("%version%", versionNumber));
                            else plugin.getLogger().info("A new update is available: v" + versionNumber);
                            
                            downloadUpdate(sender, latestVersion, silent);
                        } else {
                            this.updateAvailable = false;
                            if (!silent) sender.sendMessage(plugin.getLanguageManager().getMessage("update-latest"));
                        }
                    }
                } else {
                    plugin.getLogger().warning("Modrinth API Error Code: " + connection.getResponseCode());
                    if (!silent) sender.sendMessage(plugin.getLanguageManager().getMessage("update-error"));
                }
            } catch (Exception e) {
                plugin.getLogger().log(java.util.logging.Level.SEVERE, "Error checking for updates: " + e.getMessage());
                if (!silent) sender.sendMessage(plugin.getLanguageManager().getMessage("update-error"));
            }
        });
    }

    private void downloadUpdate(CommandSender sender, JsonObject versionData, boolean silent) {
        try {
            JsonArray files = versionData.getAsJsonArray("files");
            if (files.size() == 0) {
                 if (!silent) sender.sendMessage(plugin.getLanguageManager().getMessage("update-error"));
                 return;
            }
            
            // Genellikle ilk dosya ana jar dosyasıdır
            JsonObject primaryFile = files.get(0).getAsJsonObject();
            String downloadUrl = primaryFile.get("url").getAsString();
            
            URL url = new URL(downloadUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "VeinTreeBreaker-Plugin");
            
            try (InputStream in = connection.getInputStream()) {
                File updateFolder = new File(plugin.getDataFolder().getParentFile(), "update"); // plugins/update
                if (!updateFolder.exists()) updateFolder.mkdirs();
                
                File currentJar = plugin.getPluginFile();
                File newJar = new File(updateFolder, currentJar.getName());

                Files.copy(in, newJar.toPath(), StandardCopyOption.REPLACE_EXISTING);
                
                if (!silent) sender.sendMessage(plugin.getLanguageManager().getMessage("update-downloaded"));
                else plugin.getLogger().info("Update downloaded to plugins/update folder. Restart to apply.");
            }
            
        } catch (Exception e) {
            plugin.getLogger().log(java.util.logging.Level.SEVERE, "Error downloading update: " + e.getMessage());
            if (!silent) sender.sendMessage(plugin.getLanguageManager().getMessage("update-fail"));
        }
    }
}