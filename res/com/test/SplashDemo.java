package com.test;

import com.jc.ems.modal.common.Crypto;

public class SplashDemo {
	
	public static void main (String args[]) {
		Crypto security = new Crypto();
		System.out.println(security.encryptData("user"));
		System.out.println(security.decryptData(security.encryptData("user")));
    }
}