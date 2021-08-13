package me.anatomic.divictus.interfaceplugin.Server;

import me.anatomic.divictus.interfaceplugin.InterfacePlugin;
import me.anatomic.divictus.interfaceplugin.network.GetNotesRequest;
import me.anatomic.divictus.interfaceplugin.network.SendReport;
import me.anatomic.divictus.interfaceplugin.network.SetNoteRequest;
import me.anatomic.divictus.interfaceplugin.network.Websockets;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.Arrays;

public class ReportCommand implements CommandExecutor {

    Websockets wsC;
    InterfacePlugin plugin;
    public ReportCommand(Websockets ws, InterfacePlugin pl){
        wsC = ws;
        plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // /newnote AnatomicGod <....>
        if (args.length < 2){
            return false;
        }
        else {
            if(plugin.reportPlayersOnTimeout.contains(Bukkit.getPlayer(sender.getName()))){
                sender.sendMessage("[*] You can report once every 3 minutes.");
                return true;
            }
            String username = args[0];
            Player player = Bukkit.getServer().getPlayer(username);
            if(player == null){
                sender.sendMessage("[*] There is no such player.");
                return true;
            }
            ArrayList<String> argWords = new ArrayList<String>(Arrays.asList(args));
            System.out.println(argWords);
            argWords.remove(0);
            String reportContent = String.join(" ", argWords);
            SendReport note = new SendReport(sender.getName(), reportContent, username);
            System.out.println(note.jsonObj);
            System.out.println(wsC);
            System.out.println(wsC.ws);
            if(wsC == null || wsC.ws == null || !wsC.runningSocket){
                sender.sendMessage("[*] There is no connection to the interface currently.");
                return true;
            }
            wsC.ws.sendText(note.jsonObj.toString());
            sender.sendMessage("[***] Report about " + args[0] + " sent!");
            plugin.reportPlayersOnTimeout.add(Bukkit.getPlayer(sender.getName()));
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    plugin.reportPlayersOnTimeout.remove(Bukkit.getPlayer(sender.getName()));
                }
            }, 3600L);
            return true;
        }
    }
}

