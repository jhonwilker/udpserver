package com.example.demo;

import java.net.*;
import java.io.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@Controller
@SpringBootApplication
public class DemoApplication {

    @RequestMapping("/")
    @ResponseBody
    String home() {
      return "Hello World!";
    }
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
    		DatagramSocket aSocket = null;
		try{

		//int port = Integer.parseInt(args[0]);
		int port = 1234;
	    	aSocket = new DatagramSocket(port);
		//aSocket = new DatagramSocket(6789);
		// create socket at agreed port
		byte[] buffer = new byte[1000];
		while(true){
			DatagramPacket request = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(request);     
			DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), 
			request.getAddress(), request.getPort());
			String msg = new String(request.getData(),"UTF-8");
			System.out.println("UDP Server Recebido: "+ msg);
			aSocket.send(reply);
		}
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e) {System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}

	}	

}
