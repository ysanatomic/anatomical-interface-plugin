package me.anatomic.anatomical.interfaceplugin.Server;

import com.avaje.ebeaninternal.server.query.BackgroundIdFetch;
import me.anatomic.anatomical.interfaceplugin.InterfacePlugin;
import me.anatomic.anatomical.interfaceplugin.network.GetNotesRequest;
import me.anatomic.anatomical.interfaceplugin.network.Websockets;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import javax.naming.Context;
import java.util.Arrays;

public class ViewInfoCommand implements CommandExecutor{

    Websockets wsC;
    InterfacePlugin ctx;
    public ViewInfoCommand(Websockets ws, InterfacePlugin ctxx){
        wsC = ws;
        ctx = ctxx;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
//            Bukkit.getServer().getPlayer(sender.getName()).sendMessage("Correct usage: /pinfo <player>");
            return false;
        }
        else {
            Integer page = 1;
            if(args.length < 2){
                page = 1;
            } else {
                try{
                    page = Integer.parseInt(args[1]);
                } catch(Exception e){
                    page = 1;
                }
            }
            if(wsC == null || wsC.ws == null || !wsC.runningSocket){
                sender.sendMessage("[*] There is no connection to the interface currently.");
                return true;
            }
            GetNotesRequest request = new GetNotesRequest(args[0], Bukkit.getServer().getPlayer(sender.getName()).getUniqueId().toString(), page);
            wsC.ws.sendText(request.jsonObj.toString());

            String url = ctx.getConfig().get("HttpOrHttps") +"://" + ctx.getConfig().get("interfaceURL") + "/player/"+args[0]+"/";
            String message = "Click this to get all notes about "+args[0];
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getConsoleSender(),
                    "tellraw " + sender.getName() +
                            " {text:\"" + message + "\",clickEvent:{action:open_url,value:\"" +
                            url + "\"}, \"coor\": \"green\"}");
            return true;
        }

    }


}
