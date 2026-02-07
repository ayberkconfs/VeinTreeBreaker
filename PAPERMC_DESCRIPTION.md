# 🌳 VeinTreeBreaker - Ultimate Tree & Vein Mining Solution

**VeinTreeBreaker** is a performance-oriented and highly customizable Paper/Spigot plugin that revolutionizes your mining and woodcutting experience. Fell massive trees and collect complex ore veins in seconds with a single strike!

---

## ✨ Key Features

### 🪵 Tree Feller (Advanced Woodcutting)
- **One-Click Tree Felling:** Breaking the base of a tree fells the entire tree (including leaves!).
- **Auto-Replant:** Automatically replants saplings in place of cut trees.
- **Smart Leaf Decay:** Cleans up surrounding leaves (adjustable radius) when a tree is cut.
- **Animated Destruction:** Blocks don't just vanish; they break sequentially with a pleasing animation.

### ⛏️ Vein Miner (Smart Ore Mining)
- **Vein Mining:** Breaking one ore collects all connected ores of the same type.
- **Lava Protection:** Automatically stops if the vein is in contact with lava, preventing your items from burning.
- **Wide Material Support:** Supports not just ores, but also Obsidian, Glowstone, Amethyst, and more.

### ⚙️ Full Control & Balance
- **Durability:** Tools lose durability (at an adjustable rate) for every block broken.
- **Hunger:** Multi-block breaking affects the player's hunger bar, maintaining vanilla balance.
- **Safety Threshold:** The plugin stops automatically if your tool is low on durability (default: 10).
- **World Whitelist/Blacklist:** Restrict functionality to specific worlds.

### 🌍 Language Support
Fully supports the following languages:
- 🇺🇸 English (en_US)
- 🇹🇷 Turkish (tr_TR)
- 🇩🇪 German (de_DE)
- 🇪🇸 Spanish (es_ES)
- 🇫🇷 French (fr_FR)

---

## 🛠️ Commands and Permissions

| Command | Description | Permission |
| :--- | :--- | :--- |
| `/vtb toggle` | Toggles the feature on/off. | `veintreebreaker.toggle` |
| `/vtb reload` | Reloads the plugin configuration. | `veintreebreaker.admin` |

*Note: `/vtb` is a shortcut for the `/veintreebreaker` command.*

---

## 🚀 Installation

1. Drop the `VeinTreeBreaker.jar` file into your server's `plugins` folder.
2. Start the server to generate configuration files.
3. Configure your preferences in `plugins/VeinTreeBreaker/config.yml`.
4. Reload the plugin (`/vtb reload`) and enjoy!

---

## 📸 Visual Effects
The plugin provides satisfying feedback using particle and sound effects as blocks break. All effects can be customized or disabled in the `config.yml`.

---

## 📋 Technical Details
- **Version Support:** 1.21.1 and above (Paper/Spigot)
- **Performance:** Optimized search algorithms ensure minimal impact on server TPS.
- **Java:** Requires Java 21.

---

**Developer:** Gemini
**License:** MIT