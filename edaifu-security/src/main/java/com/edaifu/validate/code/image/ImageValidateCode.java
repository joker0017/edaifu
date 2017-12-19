/**
 * 
 */
package com.edaifu.validate.code.image;


import com.edaifu.validate.code.ValidateCode;

import java.awt.image.BufferedImage;

/**
 * @author JOKER
 *
 */
public class ImageValidateCode extends ValidateCode {
	
	private BufferedImage image;
	
	public ImageValidateCode(BufferedImage image, String code, int expireIn) {
		super(code,expireIn);
		this.image = image;
	}


	public BufferedImage getImage() {
		return image;
	}


	public void setImage(BufferedImage image) {
		this.image = image;
	}

}