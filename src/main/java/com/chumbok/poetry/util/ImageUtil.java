package com.chumbok.poetry.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

public class ImageUtil {

	public static BufferedImage readImageFromMpf(MultipartFile mpf)
			throws IOException {

		BufferedImage src = ImageIO.read(mpf.getInputStream());

		BufferedImage newImage = new BufferedImage(src.getWidth(),
				src.getHeight(), BufferedImage.TYPE_INT_RGB);
		int[] rgb = src.getRGB(0, 0, src.getWidth(), src.getHeight(), null, 0,
				src.getWidth());
		newImage.setRGB(0, 0, src.getWidth(), src.getHeight(), rgb, 0,
				src.getWidth());

		return newImage;
	}

}
