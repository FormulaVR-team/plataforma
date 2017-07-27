package com.fvr._comun.img2D.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
* This program demonstrates how to resize an image.
*
* @author www.codejava.net
*
*/
public class ImageUtils {

	/**
	* Test resizing images
	*/
	public static void main(String[] args) {
		
		String[] pito = getWriterFormatNames();
		
		for( String elem : pito ) { 
			System.out.println( elem );
		}

//		   test1();
//		   test2();

	}

	public static void test1() {
		String inputImagePath = "D:/Photo/Puppy.jpg";
		String outputImagePath1 = "D:/Photo/Puppy_Fixed.jpg";
		String outputImagePath2 = "D:/Photo/Puppy_Smaller.jpg";
		String outputImagePath3 = "D:/Photo/Puppy_Bigger.jpg";

		try {
			// resize to a fixed width (not proportional)
			int scaledWidth = 1024;
			int scaledHeight = 768;
			ImageUtils.resize(inputImagePath, outputImagePath1, scaledWidth, scaledHeight);

			// resize smaller by 50%
			double percent = 0.5;
			ImageUtils.resize(inputImagePath, outputImagePath2, percent);

			// resize bigger by 50%
			percent = 1.5;
			ImageUtils.resize(inputImagePath, outputImagePath3, percent);

		} catch (IOException ex) {
			System.out.println("Error resizing the image.");
			ex.printStackTrace();
		}
	}

	public static void test2() {
		try {
			/* Test image to string and string to image start */
			BufferedImage img = ImageIO.read(new File("files/img/TestImage.png"));
			BufferedImage newImg;
			String imgstr;
			imgstr = encodeToString(img, "png");
			System.out.println(imgstr);
			newImg = decodeToImage(imgstr);
			ImageIO.write(newImg, "png", new File( "files/img/CopyOfTestImage.png" ) );
			/* Test image to string and string to image finish */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

   /**
    * Resizes an image to a absolute width and height (the image may not be
    * proportional)
    * @param inputImagePath Path of the original image
    * @param outputImagePath Path to save the resized image
    * @param scaledWidth absolute width in pixels
    * @param scaledHeight absolute height in pixels
    * @throws IOException
    */
   public static void resize(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight)
           throws IOException {
       // reads input image
       File inputFile = new File(inputImagePath);
       BufferedImage inputImage = ImageIO.read(inputFile);

//       // creates output image
//       BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());
//
//       // scales the input image to the output image
//       Graphics2D g2d = outputImage.createGraphics();
//       g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
//       g2d.dispose();
       
       // extracts extension of output file
       String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);

       // writes to output file
       ImageIO.write( resize( inputImage, scaledWidth, scaledHeight ), formatName, new File(outputImagePath));
   }
   /**
    * Resizes an image by a percentage of original size (proportional).
    * @param inputImagePath Path of the original image
    * @param outputImagePath Path to save the resized image
    * @param percent a double number specifies percentage of the output image
    * over the input image.
    * @throws IOException
    */
   public static void resize(String inputImagePath, String outputImagePath, double percent) throws IOException {
       File inputFile = new File(inputImagePath);
       BufferedImage inputImage = ImageIO.read(inputFile);
       int scaledWidth = (int) (inputImage.getWidth() * percent);
       int scaledHeight = (int) (inputImage.getHeight() * percent);
       resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
   }

   public static BufferedImage resize( BufferedImage inputImage, int scaledWidth, int scaledHeight ) {

       // creates output image
       BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

       // scales the input image to the output image
       Graphics2D g2d = outputImage.createGraphics();
       g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
       g2d.dispose();
       
       return outputImage;
   }

   public static BufferedImage resize( BufferedImage inputImage, double percent ) {
	   
	   if ( percent == 1.0 ) { return inputImage; }
	   if ( percent <= 0.0 ) { return inputImage; }
	   
       int scaledWidth = (int) (inputImage.getWidth() * percent);
       int scaledHeight = (int) (inputImage.getHeight() * percent);
	   return resize( inputImage, scaledWidth, scaledHeight );	   
   }

   /**
    * Decode string to image
    * @param imageString The string to decode
    * @return decoded image
    */
   public static BufferedImage decodeToImage(String imageString) {

       BufferedImage image = null;
       byte[] imageByte;
       try {
           BASE64Decoder decoder = new BASE64Decoder();
           imageByte = decoder.decodeBuffer(imageString);
           ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
           image = ImageIO.read(bis);
           bis.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return image;
   }

   public static String[] getWriterFormatNames() {
	   return ImageIO.getWriterFormatNames();
   }

   /**
    * Encode image to string
    * @param image The image to encode
    * @param type jpeg, bmp, ...
    * @return encoded string
    */
   public static String encodeToString(BufferedImage image, String type) {
       String imageString = null;
       ByteArrayOutputStream bos = new ByteArrayOutputStream();

       try {
           ImageIO.write(image, type, bos);
           byte[] imageBytes = bos.toByteArray();

           BASE64Encoder encoder = new BASE64Encoder();
           imageString = encoder.encode(imageBytes);

           bos.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
       return imageString;
   }
  
   public static BufferedImage crearPNG(String text, String font, Color fondo, 
			int estilo1, Color color1, 
			int estilo2, Color color2) {
		int WIDTH = 250;
		int HEIGHT = 250;
		
		int X_WORD = 60; 
		int Y_WORD = 160;
		
		int SIZE = 100;
		
       BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
       Graphics2D g2 = bufferedImage.createGraphics();
       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
               RenderingHints.VALUE_ANTIALIAS_ON);
       g2.setColor(fondo);
//       GradientPaint horizontalGradient = new GradientPaint(0, 0, fondo, WIDTH, 0, color1);
//       g2.setPaint(horizontalGradient);

       g2.fillRect(0, 0, WIDTH, HEIGHT);
       g2.setColor(color1);
       g2.setFont(new Font(font, estilo1, SIZE));
       g2.drawString(text.substring(0, 1).toUpperCase(), X_WORD, Y_WORD);
       g2.setColor(color2);
       g2.setFont(new Font(font, estilo2, SIZE));
       g2.drawString(text.substring(1, 2).toUpperCase(), X_WORD+70, Y_WORD);
       g2.dispose();
       return bufferedImage;

   }

}