/**
 * 
 */
package com.edaifu.validate.code.sms;

/**
 * @author JOKER
 *
 */
public class DefaultSmsValidateCodeSender implements SmsValidateCodeSender {

	/* (non-Javadoc)
	 * @see com.easypay.core.validate.code.sms.SmsValidateCodeSender#sender(java.lang.String, java.lang.String)
	 */
	@Override
	public void sender(String mobile, String code) {
		System.out.println("给手机"+mobile+"发送验证码为"+code);
	}

}
