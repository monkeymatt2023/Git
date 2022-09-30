import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Benjitester {
	@BeforeAll
	static void setUpBeforeClass() throws IOException{
		File f = new File("Test/objects");
		f.mkdirs();
		
		Path p = Paths.get("Test/junit.txt");
        try {
            Files.writeString(p, "Will Tester", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
//	@AfterAll
//	static void tearDownAfterClass() throws IOException{
//		File f1 = new File("Test/junit.txt");
//		f1.delete();
//		File f1hash = new File("Test/objects/b34bcfc4d490f93ed9cf4e560c608b58cd688c24");
//		f1hash.delete();
//		
//		File f2 = new File("Test/objects/dd4840f48a74c1f97437b515101c66834b59b1be");
//		f2.delete();
//		
//		File f3 = new File("Test/index.txt");
//		f3.delete();
//		
//		File f4 = new File("Test/objects");
//		deleteDir(f4);
//	}
//	
//	static void deleteDir(File file) {
//	    File[] contents = file.listFiles();
//	    if (contents != null) {
//	        for (File f : contents)
//	            deleteDir(f);
//	    }
//	    file.delete();
//	}

	@Test
	void testInit() throws IOException {
		Index g = new Index();
		g.initialize();
		
		File file = new File("Test/index.txt");
		assertTrue(file.exists());
		
		Path path = Paths.get("Test/objects");
		assertTrue(Files.exists(path));
		
	}
	
	@Test
	void testBlob() throws NoSuchAlgorithmException, IOException {
		Blob b = new Blob("Test/junit.txt");
		File file  = new File("Test/objects/b34bcfc4d490f93ed9cf4e560c608b58cd688c24");
		assertTrue(file.exists());
	}
	
	@Test
	void testAdd() throws IOException, NoSuchAlgorithmException {
		Index i = new Index();
		i.initialize();
		i.add("junit.txt");
		File file = new File("Test/objects/b34bcfc4d490f93ed9cf4e560c608b58cd688c24");
		assertTrue(file.exists());
	}
	
	
	@Test
	void testRemove() throws NoSuchAlgorithmException, IOException {
		Index i = new Index();
		i.initialize();
		i.remove("junit.txt");
		File file = new File("Test/objects/b34bcfc4d490f93ed9cf4e560c608b58cd688c24");
		assertTrue(file.exists());
	}
	
	@Test
	void testTree() throws NoSuchAlgorithmException, IOException {
		ArrayList <String> listy = new ArrayList<String>();
		listy.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
		listy.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
		listy.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
		listy.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
		listy.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");
		File file = new File("Test/objects/dd4840f48a74c1f97437b515101c66834b59b1be");
		Tree tree = new Tree(listy);
	}
	
	@Test
	void testCommit() throws FileNotFoundException {
		Commit comParent = new Commit("Test/objects/dd4840f48a74c1f97437b515101c66834b59b1be","stuff","benji",null);

	}

}
