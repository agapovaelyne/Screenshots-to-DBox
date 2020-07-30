import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.imageio.ImageIO;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.UploadErrorException;

public class DBoxAcces {
	public static void main(String[] args) throws UploadErrorException, DbxException, InterruptedException{			
		
		//creating  DBox client
        String accesToken = "E4dEkW3J668AAAAAAAACmDVrKyoRrqkBF7O6bRhhd09NBTapurPPCSIh4AGtR5Eg";
	    DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
	    DbxClientV2 client = new DbxClientV2(config, accesToken);
        //creating the thread
        
        
        //getting the buffered image as inputString and start the thread to get 5 screenshots in DBox folder with interval 5 seconds
        
        for (int i=0; i < 4; i++) {
        	try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                BufferedImage image = grabScreen();
            	ImageIO.write(image,"png", os);
                MyThread thread = new MyThread();
                thread.client = client;
            	thread.image = new ByteArrayInputStream(os.toByteArray());
                thread.start();
                thread.sleep(5000);
    			} catch (IOException e) {
    			System.out.println("IO exception"+e);
    			}
        }     
	}
    
	
	//making screenshot 
	public static BufferedImage grabScreen(){ 
		try {
			BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			return image;	
			} catch (SecurityException e) {
			} catch (AWTException e) {
				}
		return null;	
	}
}