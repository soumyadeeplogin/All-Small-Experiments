import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Compare {
	
	public static void main(String args[])
	{
//		File img1 = new File("C:\\test1.jpg");
//		File img2 = new File("C:\\test3.jpg");
		
//		System.out.println(compareImage(img1, img2));
		screenShot("C:\\ScSh1");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		screenShot("C:\\ScSh2");
		
		
		File img1 = new File("C:\\ScSh2.jpg");
		File img2 = new File("C:\\ScSh1.jpg");
		
		System.out.println(compareImage(img1, img2));
	}
	
	public static void screenShot(String fileName)
	{
		try {
            Robot robot;
			robot = new Robot();
            String format = "jpg";
            fileName = fileName+"."+format;
//            String fileName = "C:\\FullScreenshot." + format;
             
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            ImageIO.write(screenFullImage, format, new File(fileName));
             
//            System.out.println("A full screenshot saved!");
       } 
		catch(IOException | AWTException e){
			System.err.println(e);
		}
	}
	
	public static boolean compareImage(File fileA, File fileB) {        
	    try {
	        // take buffer data from botm image files //
	        BufferedImage biA = ImageIO.read(fileA);
	        DataBuffer dbA = biA.getData().getDataBuffer();
	        int sizeA = dbA.getSize();    
	        System.out.println(sizeA);
	        BufferedImage biB = ImageIO.read(fileB);
	        DataBuffer dbB = biB.getData().getDataBuffer();
	        int sizeB = dbB.getSize();
	        System.out.println(sizeB);
	        // compare data-buffer objects //
	        if(sizeA == sizeB) {
	            for(int i=0; i<sizeA; i++) { 
	                if(dbA.getElem(i) != dbB.getElem(i)) {
	                	System.out.println(dbA.getElem(i)   +  "        "   + dbB.getElem(i));
	                	return false;
	                }
	            }
	            return true;
	        }
	        else {
	            return false;
	        }
	    } 
	    catch (Exception e) { 
	        System.out.println("Failed to compare image files ...");
	        return  false;
	    }
	}

}
