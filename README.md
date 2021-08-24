# Interface Plugin

This is the plugin made to work with the [Interface](https://github.com/ysanatomic/DivictusInterface "Interface").

Available Commands:
```
/interfaceinfo
/report
/pinfo - Staff Command (permission: divictusinterface.playerinfo)
/notes - Staff Command (permission: divictusinterface.notes)
/newnote - Staff Command (permission: divictusinterface.notes)
/allowreports - Staff Command (permission: divictusinterface.reportcontrol)
/blockreports - Staff Command (permission: divictusinterface.reportcontrol)
/resolvereports - Staff Command (permission: divictusinterface.reportcontrol)
```

The Config for the plugin is extremely simple.

```
interfaceURL: localhost:8000
authToken: e28f6137-24f1-46ca-b655-093565a78d46
HttpOrHttps: http
WsOrWss: ws
```
You should replace the `interfaceURL` and `authToken` to match the token of your server client token from the interface.

You should replace `http` with `https` and `ws` with `wss` if the interface is under SSL.


![Help Display](https://i.imgur.com/mweKf7r.png)

![Report Staff Message](https://i.imgur.com/1WE11Vq.png)

![Notes](https://i.imgur.com/S4bWLgQ.png)

![Player info](https://i.imgur.com/K5JU7XN.png)