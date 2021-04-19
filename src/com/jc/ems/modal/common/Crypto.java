package com.jc.ems.modal.common;

import java.util.Base64;


public class Crypto {

	public String encryptData(String textToEncrypt) {
		byte[] delta = textToEncrypt.getBytes();
		return Base64.getEncoder().encodeToString(delta);
	}
	
	public String decryptData(String textToDecrypt) {
		byte[] delta  = Base64.getDecoder().decode(textToDecrypt);
		return new String(delta);
	}
}
