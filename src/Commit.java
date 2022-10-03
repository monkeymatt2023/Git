import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Commit {
	private String child;
	private String parent;
	private String summary;
	private String author;
	private String date;
	private String fileName;
	private Tree tree;
	
	public Commit(String s, String a, String pointer) throws NoSuchAlgorithmException, IOException {
		summary = s;
		author = a;
		this.date = getDate();
		fileName = sha1(s, getDate(), a, pointer);
		parent = pointer;
		child = null;
		tree = createTree();
		clearIndex();
		writeFile();
	}
	
	public Tree createTree() throws NoSuchAlgorithmException, IOException {
		ArrayList<String> list = new ArrayList<String>();
		if (parent != null) {
			Scanner input = new Scanner("Test/objects" + parent);
			String line = input.nextLine();
			list.add("tree : " + line.substring(13));
			input.close();
		}
		Scanner input2 = new Scanner("Test/index.txt");
		while (input2.hasNext()) {
			String line = input2.nextLine();
			int indexSHA = line.indexOf(':')+1;
			int indexFileName = line.indexOf(' ');
			list.add("blob : " + line.substring(indexSHA) + line.substring(0,indexFileName));
		}
		input2.close();
		return new Tree(list);
	}
	
	public void clearIndex() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter("Test/index.txt");
		pw.close();
	}
	
	public String sha1(String summary, String date, String author, String parent) {
		String value = "";
		value += summary;
		value += date;
		value += author;
		value += parent;
		String sha1 = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(value.getBytes("utf8"));
	        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e){
			e.printStackTrace();
		}
		return sha1;
	}
	
	public String getDate() {
		Calendar c = Calendar.getInstance();
		int mYear = c.get(Calendar.YEAR);
		int mMonth = c.get(Calendar.MONTH);
		int mDay = c.get(Calendar.DAY_OF_MONTH);
		date = mMonth +"/"+ mDay +"/"+ mYear;
		return date;
	}
	
	public void writeFile() throws NoSuchAlgorithmException, IOException {
		File f = new File(fileName);
		PrintWriter p = new PrintWriter("Test/objects/"+ f);
		if(parent == null) {
			parent = "";
		}
		if(child == null) {
			child = "";
		}
		p.append(tree.filename() + "\n");
		p.append(parent + "\n");
		p.append(child + "\n");
		p.append(author + "\n");
		p.append(date + "\n");
		p.append(summary + "\n");
		p.close();
	}
	
	public String getCommitName() {
		return sha1(summary, date, author, parent);
	}
	

	
//	public static void main(String[]args) throws NoSuchAlgorithmException, IOException {
//		ArrayList <String> arr = new ArrayList <String>();
//		arr.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
//		arr.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
//		arr.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
//		arr.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
//		arr.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");
//		Tree tree1 = new Tree(arr);
//		File file = new File("objects/dd4840f48a74c1f97437b515101c66834b59b1be");
//		Commit com = new Commit("Test/objects/36dbe7dab0f9aac93d48ee3cb8ef00ef1c510720","hello","benji",null); 
//	
//	}
	
	
	
	
	
	
	
	
	
	
	
}
