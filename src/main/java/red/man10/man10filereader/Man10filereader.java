package red.man10.man10filereader;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public final class Man10filereader extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("mfr").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length == 0) {
                sender.sendMessage("/mfr [filename]");
                return true;
            }
            if (args.length != 1) {
                sender.sendMessage("/mfr [filename]");
                return false;
            }
            Bukkit.getScheduler().runTaskAsynchronously(this, (  ) -> {

                try {

                    // First of all, you need to define file you want to read.
                    File fileToRead = new File(getDataFolder(), args[0] + ".txt");

                    // Setup BufferedReader
                    BufferedReader br = new BufferedReader(new FileReader(fileToRead));

                    // Read line by line
                    String line = null;

                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return true;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("mfr.use")) {
            p.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage("/mfr [filename]");
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage("/mfr [filename]");
            return false;
        }
        Bukkit.getScheduler().runTaskAsynchronously(this, (  ) -> {

            try {

                // First of all, you need to define file you want to read.
                File fileToRead = new File(getDataFolder(), args[0] + ".txt");

                // Setup BufferedReader
                BufferedReader br = new BufferedReader(new FileReader(fileToRead));

                // Read line by line
                String line = null;

                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return false;
    }
}
