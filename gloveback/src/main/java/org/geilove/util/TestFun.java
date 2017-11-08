package org.geilove.util;

/**
 * Created by mfhj-dz-001-424 on 17/1/17.
 */
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Random;
import java.util.UUID;
public class TestFun {

    public  static  void  main(String[] args) {
//        String uuid = UUID.randomUUID().toString();
//        System.out.print(uuid);
        //String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        //System.out.println(uuid);
       // System.out.println(UUID.randomUUID());
        //System.out.print(UUID.randomUUID().toString().length());

        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
            for(int i=1; i<=4-randLength; i++)
                fourRandom = "0" + fourRandom  ;
        }

        System.out.println(fourRandom.toString());
//
//        try{
//            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
//            InetAddress ip = null;
//            while (allNetInterfaces.hasMoreElements()) {
//                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
//
//                Enumeration addresses = netInterface.getInetAddresses();
//                while (addresses.hasMoreElements()) {
//                    ip = (InetAddress) addresses.nextElement();
//                    if (ip != null && ip instanceof Inet4Address) {
//
//                        if (ip.getHostAddress().equals("127.0.0.1")){
//                            continue;
//                        }
//                        System.out.println("本机的IP = " + ip.getHostAddress());
//                    }
//                }
//            }
//        }catch (Exception e){
//        }
    }

}

