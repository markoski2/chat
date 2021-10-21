/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;
import javax.swing.DefaultListModel;

/**
 *
 * @author draw-
 */
public class HiloServidor extends Thread{
    private DataInputStream entrada;
    private DataOutputStream salida;
    private Servidor server;
    private Socket Cliente;
    public static Vector<HiloServidor> usuarioActivo=new Vector();
    private String nombre;
    private ObjectOutputStream salidaObjeto;
    
    public HiloServidor(Socket socketcliente,String nombre,Servidor serv) throws Exception{
        this.Cliente=socketcliente;
        this.server=serv;
        this.nombre=nombre;
        usuarioActivo.add(this);
        for(int i=0;i<usuarioActivo.size();i++){
            usuarioActivo.get(i).envioMensajes(nombre+"se ah conectado.");
        }
    }
    
    public void run(){
        String mensaje ="";
        while(true){
            try{
                entrada=new DataInputStream(Cliente.getInputStream());
                mensaje=entrada.readUTF();
                
                for(int i=0;i<usuarioActivo.size();i++){
                    usuarioActivo.get(i).envioMensajes(mensaje);
                    server.mensajeria("Mensaje enviado:"+mensaje);//aqui poner la encriptacion
                    
                }
            }catch (Exception e){
                break;
            }
        }
        
        usuarioActivo.removeElement(this);
        server.mensajeria("El usuario se ha desconectado.");
        try{
            Cliente.close();
        }catch (Exception e){
            
        }
        
    }
    
    private void envioMensajes(String msg) throws Exception{
        salida=new DataOutputStream(Cliente.getOutputStream());
        salida.writeUTF(msg);//Envio de mensaje
        DefaultListModel modelo=new DefaultListModel();
        
        for(int i=0;i<usuarioActivo.size();i++){
            modelo.addElement(usuarioActivo.get(i).nombre);
        }
        salidaObjeto =new ObjectOutputStream(Cliente.getOutputStream());
        salidaObjeto.writeObject(modelo);
    }
    
    
    
}
