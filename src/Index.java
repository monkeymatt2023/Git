import java.util.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Index {
	
	private String filename;
	private static HashMap <String, String> map;
	
	public Index() throws IOException {
		map = new HashMap <String, String>();
	}
	
	public void initialize() throws FileNotFoundException, IOException{
		File theDir = new File("Test/objects");
		if (!theDir.exists()){
		    theDir.mkdirs();
		}
		Path p = Paths.get("Test");
		File index = new File(p + "/index");
		index.createNewFile();
    }
	
	
	public void add(String filename) throws IOException, NoSuchAlgorithmException{
		Blob blob = new Blob("Test/" + filename);
		map.put(filename, blob.getSha1());
		writeToFile(filename);
	}
	
	public void remove(String filename)throws IOException, FileNotFoundException {
		File remove = new File("Test/objects/"+ map.get(filename));
		remove.delete();
		map.remove(filename);
		writeToFile(filename);
	}
	
	public void writeToFile (String filename) throws FileNotFoundException {
		File file = new File("Test/index");
		
		PrintWriter pw = new PrintWriter(file);
		for (String s : map.keySet())
			pw.append(s + ": " + map.get(s) + "\n");
		pw.close();
		
//	    BufferedWriter bf = null;
//        try {
//            bf = new BufferedWriter(new FileWriter(file));
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                bf.write(entry.getKey() + ":" + entry.getValue());
//                bf.newLine();
//            }
//              bf.flush();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        finally {
//            try {
//                bf.close();
//            }
//            catch (Exception e) {
//            }
//        }
	}
	
	public static void clearMap() {
		map = new HashMap <String, String>();
	}
	
//	public static void main(String[] args) throws FileNotFoundException, IOException, NoSuchAlgorithmException{
//		Index ind = new Index();
//		ind.initialize();
//		ind.add("foo.txt");
//		ind.add("foobar.txt");
//		ind.add("bar.txt");
//		ind.remove("foo.txt");
//		
//	}   
}
