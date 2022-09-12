import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.*;
import java.util.*;

public class Blob {
	
	private String SHA1="";
	private String filePath;
	
	public Blob(String filePath) throws IOException, NoSuchAlgorithmException {
		SHA1 = sha1Code (filePath);
		File f = new File(filePath);
		Scanner sc = new Scanner(f);
		FileWriter fw = new FileWriter("./Test/objects/"+ SHA1);
		PrintWriter pw = new PrintWriter (fw);
		while(sc.hasNextLine()) {
	        String s = sc.nextLine();
	        pw.write(s);
	    	}
			if(sc != null) {
				sc.close();  
			}
			if(pw != null) {
				pw.flush();
				pw.close();
			}
	}
	
	public String getSha1() {
		return SHA1;
	}
	

    public static String sha1Code(String filePath) throws IOException, NoSuchAlgorithmException, FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, digest);
        byte[] bytes = new byte[1024];
        while (digestInputStream.read(bytes) > 0);
        byte[] resultByteArry = digest.digest();
        return bytesToHexString(resultByteArry);
    }
    
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            int value = b & 0xFF;
            if (value < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(value).toUpperCase());
        }
        return sb.toString();
    }
    
    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, FileNotFoundException {
        Blob blob = new Blob("./Test/foo.txt");
        System.out.println(blob.sha1Code("./Test/foo.txt")); 
    }
    
    
}