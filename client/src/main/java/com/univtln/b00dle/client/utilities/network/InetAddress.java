package com.univtln.b00dle.client.utilities.network;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Project client.
 * Package com.univtln.b00dle.client.utilities.network.
 * File InetAddress.java.
 * Created by Ysee on 01/11/2016 - 14:40.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */
public class InetAddress {
    /**
     * Get MAC adress
     * @return String MACAdress
     */
    public static String getMacAdresse(){

        java.net.InetAddress ip = null;
        try {
            ip = java.net.InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            return sb.toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return null;
    }
}
