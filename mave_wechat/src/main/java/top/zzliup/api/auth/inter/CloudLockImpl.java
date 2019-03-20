package top.zzliup.api.auth.inter;

import java.util.HashMap;
import java.util.Map;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CloudLockImpl {
	public static String token = "wxzzliuptop";
	
	public boolean checkWx(String signature,String timestamp,String nonce) {
		String str = token+timestamp+ nonce;
		System.out.println("timestamp"+timestamp);
		System.out.println("nonce"+nonce);
		System.out.println(str);
		System.out.println(signature);
		try {
			String shastr = getSHA1(token, timestamp, nonce);
			if(shastr.equals(signature)) {
				return true;
			}
		} catch (Exception e) {
			
		}
		return false;
				
	}
	
	
	public static String getSha1(String str){
	    if(str == null || str.length()==0){
	        return null;
	    }
	    char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

	    try {
	        MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
	        mdTemp.update(str.getBytes("UTF-8"));

	        byte[] md = mdTemp.digest();
	        int j = md.length;
	        char buf[] = new char[j*2];
	        int k = 0;
	        for(int i=0;i<j;i++){
	            byte byte0 = md[i];
	            buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
	            buf[k++] = hexDigits[byte0 & 0xf];
	        }
	        return new String(buf);
	    } catch (Exception e) {
	        return null;
	    }
	}



	public static String getSHA1(String token, String timestamp, String nonce) throws Exception {
		try {
			String[] array = new String[] { token, timestamp, nonce };
			StringBuffer sb = new StringBuffer();
			// ×Ö·û´®ÅÅÐò
			Arrays.sort(array);
			for (int i = 0; i < 3; i++) {
				sb.append(array[i]);
			}
			String str = sb.toString();
			// SHA1Ç©ÃûÉú³É
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes());
			byte[] digest = md.digest();

			StringBuffer hexstr = new StringBuffer();
			String shaHex = "";
			for (int i = 0; i < digest.length; i++) {
				shaHex = Integer.toHexString(digest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexstr.append(0);
				}
				hexstr.append(shaHex);
			}
			return hexstr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	/*timestamp1551700038
	nonce1737173494
	wxzzliuptop15517000381737173494
	c8c8ed59c552aade66fe2771af0ccf0dd736e6d1*/

	 public static void main(String[] args) throws Exception {
		System.out.println(getSHA1(token, "1551700038", "1737173494"));
	 }
}