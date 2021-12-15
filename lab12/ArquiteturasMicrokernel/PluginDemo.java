package ArquiteturasMicrokernel;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

//Pattern Used: Microkernel (Plugins)
    
public class PluginDemo {

    public static void main(String[] args) {
        File proxyList = new File("./");
        List<IPlugin> plgs = new ArrayList<>();
    
        for (String f: proxyList.list()) {
            if (f.endsWith(".class")) {
                try {
                    plgs.add(PluginManager.load("./" + f.substring(0, f.lastIndexOf("."))));
                } catch (Exception e) {
                    System.err.println("\t" + f + ": Componente ignorado. Não é IPlugin.");
                }
            }
        }

        Iterator<IPlugin> it = plgs.iterator();
        while (it.hasNext()) {
            it.next().fazQualquerCoisa();
        }
    }
}
