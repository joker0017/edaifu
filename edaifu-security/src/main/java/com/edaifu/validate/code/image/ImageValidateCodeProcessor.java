/**
 * 
 */
package com.edaifu.validate.code.image;

import com.edaifu.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author JOKER
 *
 */
@Component("imageValidateCodeProcessor")
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageValidateCode> {

	@Override
	protected void send(ServletWebRequest request, ImageValidateCode imageValidateCode) throws Exception {
		ImageIO.write(imageValidateCode.getImage(), "JPEG", request.getResponse().getOutputStream());
	}

}
