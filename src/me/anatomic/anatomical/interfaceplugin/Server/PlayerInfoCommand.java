package me.anatomic.anatomical.interfaceplugin.Server;

import me.anatomic.anatomical.interfaceplugin.network.GetPlayerInfoRequest;
import me.anatomic.anatomical.interfaceplugin.network.Websockets;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class PlayerInfoCommand implements CommandExecutor {

    Websockets wsC;

    public PlayerInfoCommand(Websockets ws) {
        wsC = ws;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
//            Bukkit.getServer().getPlayer(sender.getName()).sendMessage("Correct usage: /pinfo <player>");
            return false;
        } else {
            if(wsC.ws != null){
                GetPlayerInfoRequest request = new GetPlayerInfoRequest(args[0], Bukkit.getPlayer(sender.getName()).getUniqueId().toString());
                wsC.ws.sendText(request.jsonObj.toString());
                Bukkit.getServer().dispatchCommand(
                        Bukkit.getConsoleSender(),
                        "tellraw " + sender.getName() +
                                " {text:\"" + "==============================" + "\", \"color\": \"red\"}");
                return true;
            }
            else {
                Bukkit.getServer().dispatchCommand(
                        Bukkit.getConsoleSender(),
                        "tellraw " + sender.getName() +
                                " {text:\"" + "No connection." + "\", \"color\": \"red\"}");
                return true;
            }

        }

    }
}