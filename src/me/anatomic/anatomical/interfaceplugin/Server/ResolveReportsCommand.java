package me.anatomic.anatomical.interfaceplugin.Server;

import com.mojang.authlib.BaseUserAuthentication;
import me.anatomic.anatomical.interfaceplugin.network.ResolveReports;
import me.anatomic.anatomical.interfaceplugin.network.Websockets;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ResolveReportsCommand implements CommandExecutor {

    Websockets wsC;
    public ResolveReportsCommand(Websockets ws){
        wsC = ws;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // /resolvereports AnatomicGod
        if (args.length < 1){
            return false;
        }
        else {
            String username = args[0];
            ResolveReports data = new ResolveReports(username, Bukkit.getServer().getPlayer(sender.getName()).getUniqueId().toString());

            if(wsC == null || wsC.ws == null || !wsC.runningSocket){
                sender.sendMessage("[*} There is no connection to the interface currently.");
                return true;
            }

            wsC.ws.sendText(data.jsonObj.toString());
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getConsoleSender(),
                    "tellraw " + sender.getName() +
                            " {text:\"[***] All the reports of " + username + " have been resolved.\", \"color\": \"green\", \"bold\": \"true\"}");
            return true;
        }
    }
}
