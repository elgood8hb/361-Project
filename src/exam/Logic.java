/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

/**
 *
 * @author elgoo
 */


import java.net.*;
import java.io.*;


public class Logic extends ClientServer {
    
    private char c;
    private char s;
    private String request;
    
public void requestService (Socket socket) throws IOException{
    String servStr = readFromSocket(socket);
    UserInterface interface = new UserInterface();
    interface(s, serStr);
    
    
}

}
