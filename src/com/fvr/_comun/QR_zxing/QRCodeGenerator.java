package com.fvr._comun.QR_zxing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeGenerator {

	public static void main(String[] args) throws WriterException, IOException {
		String qrCodeText = "http://latengo.es";
		String filePath = "C:\\datos\\QR_test.png";
		int size = 300;
		String fileType = "png";
		File qrFile = new File(filePath);
		
		createQRImageFile(qrFile, qrCodeText, size, fileType);

	}

	public static void createQRImageFile(File qrFile, String qrCodeText, int size, String fileType) throws WriterException, IOException {

		BufferedImage image = generateQRImage(qrCodeText, size, Color.BLACK, Color.ORANGE);

		ImageIO.write(image, fileType, qrFile);

	}

	public static BufferedImage generateQRImage(String qrCodeText, int size, Color foreground, Color background) throws WriterException, IOException {
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		ByteMatrix byteMatrix = 
				qrCodeWriter.encode(
						qrCodeText,
						BarcodeFormat.QR_CODE, 
						size, 
						size, 
						hintMap
						);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		// Paint and save the image using the ByteMatrix
		Graphics2D graphics = (Graphics2D) image.getGraphics();

//		graphics.setColor(Color.BLACK);
		graphics.setColor(foreground);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);

//		graphics.setColor(Color.WHITE);
		graphics.setColor(background);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j) != 0) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}

		return image;
	}

}