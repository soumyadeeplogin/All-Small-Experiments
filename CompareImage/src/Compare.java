import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;

import javax.imageio.ImageIO;

public class Compare {
	
	public static void main(String args[])
	{
		File img1 = new File("C:\\test1.jpg");
		File img2 = new File("C:\\test3.jpg");
		
		System.out.println(compareImage(img1, img2));
		
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
