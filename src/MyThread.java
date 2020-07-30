import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.UploadErrorException;

public class MyThread extends Thread{		
	public ByteArrayInputStream image;
	public DbxClientV2 client;
		
	//String accesToken = "E4dEkW3J668AAAAAAAACmDVrKyoRrqkBF7O6bRhhd09NBTapurPPCSI***";
	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	@Override
	public void run() {
		try {
			//InputStream in = new ByteArrayInputStream(image);
			//String path = "/" + dateTimeFormat.format(new Date() + ".png");
			//System.out.println(image);
	    	//System.out.println("thread image value " + image.available());
			client.files().uploadBuilder("/"+dateTimeFormat.format(new Date())+".png").uploadAndFinish(image);
			} catch (IOException e) {
			  	e.printStackTrace();
			    } catch (UploadErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DbxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}	
