package com.chumbok.poetry.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

public class ImageUtil {

	public static BufferedImage readImageFromMpf(MultipartFile mpf)
			throws IOException {

		InputStream in = new ByteArrayInputStream(mpf.getBytes());
		
		BufferedImage src = ImageIO.read(in);

		BufferedImage newImage = new BufferedImage(src.getWidth(),
				src.getHeight(), BufferedImage.TYPE_INT_RGB);
		int[] rgb = src.getRGB(0, 0, src.getWidth(), src.getHeight(), null, 0,
				src.getWidth());
		newImage.setRGB(0, 0, src.getWidth(), src.getHeight(), rgb, 0,
				src.getWidth());

		return newImage;
	}

}
