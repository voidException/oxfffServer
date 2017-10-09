package org.geilove.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 或得本机的ip
 */

public class ServerIP {

    public static String getServeriP() {
        String ipNeed=null;

        try {
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();

            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();

                Enumeration addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = (InetAddress) addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address) {

                        if (ip.getHostAddress().equals("127.0.0.1")) {
                            continue;
                        }
                        //System.out.println("本机的IP = " + ip.getHostAddress());
                       ipNeed=ip.getHostAddress().toString();
                    }
                }
            }
        }
        catch (Exception e) {

        }
        return  ipNeed;
    }

    public  static String getiPPort(){
        String localiP=getServeriP();
        String ipAndrPort="http://"+localiP+":8080/";

        return ipAndrPort;
    }
    public  static void  main(String[] args){
        System.out.println(getiPPort());
    }

}

