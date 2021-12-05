package Tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Edgerard
 */
public class FileHelper {
	private String fileName = "Log File.txt";
	private String srcMesagge = "FileHelper";
	
	public FileHelper(String remitente){
		try {
			File myObj = new File(fileName);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
				this.escribir("Archivo Creado");
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		this.srcMesagge = remitente;
	}
	
	public void escribir(String log){
		try {
			FileWriter fileWriter = new FileWriter(fileName, true);
			
			Timestamp timeStamp = new Timestamp(new Date().getTime());
			
			fileWriter.write(srcMesagge + "	:	" + log + "	|	" + timeStamp + "\n\n");
			fileWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public void setSrc(String newSrc){
		this.srcMesagge = newSrc;
	}
}
