# ForceMOTD

ForceMOTD is a lightweight Paper/Spigot plugin that allows you to force a custom Server List MOTD, completely overriding `server.properties`. It is designed for Paper 1.21.1+ but works on most modern versions.

## ✨ Features

*   **Force Override**: Takes priority over `server.properties` and other plugins.
*   **Full Formatting Support**: Supports standard color codes (`&` and `§`), bold, italics, etc.
*   **Automatic Centering**: built-in pixel-perfect centering for professional looking MOTDs.
*   **Multi-line Support**: Easily add newlines with `\n`.
*   **Zero Lag**: MOTD is cached in memory and served instantly during server pings.
*   **In-Game Management**: Update your MOTD without editing files manually.

## 📥 Installation

1.  Download the latest `ForceMOTD.jar` from the releases page.
2.  Place the JAR file into your server's `plugins` folder.
3.  Restart your server.
4.  (Optional) Edit `plugins/ForceMOTD/config.yml` to customize your message.

## ⚙️ Configuration

The configuration file `config.yml` is generated automatically.

```yaml
# ForceMOTD Configuration

# You can use standard color codes (§ or &) and newlines (\n).
# To automatically center the MOTD, you can add <center> to the text.
MOTD: "<center>&aHypixel Network &c[1.8/1.21]&r\n<center>&3&lSB 0.24.1 &8- &b&lBONUS COINS + XP"

# If true, the plugin will attempt to center ALL MOTDs automatically.
# Set to false if you want manual control or use the <center> tag.
center-motd: false

# Log to console when config is reloaded
log-on-reload: true
```

### Centering Text
You have two options to center text:
1.  **Per-line**: Add `<center>` at the start of the line in your MOTD string.
2.  **Global**: Set `center-motd: true` in the config to automatically center everything.

## 🎮 Commands & Permissions

| Command | Description | Permission |
| :--- | :--- | :--- |
| `/forcemotd reload` | Reloads the configuration file from disk. | `forcemotd.admin` |
| `/forcemotd set <motd>` | Sets a new MOTD immediately. | `forcemotd.admin` |

**Permission Node:** `forcemotd.admin` (Default: OP)

## 📄 License

This project is licensed under the [MIT License](LICENSE).
