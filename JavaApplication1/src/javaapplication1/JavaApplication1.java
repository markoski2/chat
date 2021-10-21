/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.lang.reflect.Array;
import java.util.Scanner;

/**
 *
 * @author draw-
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print("texto: ");
        Scanner sc = new Scanner(System.in);
        String texto = sc.nextLine(),encryption="";
        int ascii=0;
        
        for(int i=0;i<texto.length();i++){
            ascii=texto.charAt(i);
            encryption=encryption+(char) (ascii+3);
        }
        System.out.print("texto mandado: "+texto+"\n");
        System.out.print("texto encryptado: "+encryption+"\n");
        System.out.print("texto desencryptado: "+desencritador(encryption));
    }
    
    
    public static String desencritador(String texto_encryptado){
        String desencryptador="";
        int ascii=0;
        for(int i=0;i<texto_encryptado.length();i++){
            ascii=texto_encryptado.charAt(i);
            desencryptador=desencryptador+(char)(ascii-3);
        }
        return desencryptador;
    }
    
}
