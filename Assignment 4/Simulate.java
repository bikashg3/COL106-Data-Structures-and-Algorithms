 import java.util.*; 
 import java.io.*;
 
 public class Simulate
 {
	 
	 public static void main(String[] args)
	 {
		companyList cl = new companyList();
		graduateList gl = new graduateList();
		Bikash L = new Bikash();

		Simulate s = new Simulate();	
		try 
		{
			FileInputStream fstream =  new FileInputStream("query.txt"); 
	 		Scanner f = new Scanner(fstream);
	 		long startTime = System.currentTimeMillis();
	 		while(f.hasNextLine())
	 		{
	 			String a = f.nextLine(); 
	 			String[] word = a.split(" ");
	 			//System.out.println(word[0]+"."+word[1]+"."+word[2]);
	 			if(word[0].equals("ADD") && word[1].equals("COMPANY"))
	 			{
	 				//System.out.println("adding company");
					String sub2 = word[2].substring(0,word[2].length()-1);
					String sub3 = word[3].substring(0,word[3].length()-1);
					int cap = Integer.parseInt(word[4]);
					cl.addCompany(sub2, sub3, cap);
				}
				
				else if(word[0].equals("ADD") && word[1].equals("GRADUATE"))
				{
					//System.out.println("adding GRADUATE");
					String sub2 = word[2].substring(0,word[2].length()-1);
					String sub3 = word[3].substring(0,word[3].length()-1);
					String sub4 = word[4].substring(0,word[4].length()-1);
					double cg = Double.parseDouble(sub4);
					int i = 5;
					LinkedList<String> r = new LinkedList<>();
					while(i+1 < word.length)
					{
						r.add(word[i].substring(0,word[i].length()-1));
						i++;
					}
					r.add(word[i]);
					gl.addGraduate(sub2,sub3,cg,r);
				}
				
				else if(word[0].equals("RANK") && word[1].equals("GRADUATES"))
				{
					//System.out.println("RANK GRADUATE");
					String sub1 = word[2].substring(0,word[2].length()-1);
					LinkedList<String> r = new LinkedList<>();
					int i = 3;
					while(i+1 < word.length)
					{
						r.add(word[i].substring(0,word[i].length()-1));
						i++;
					}
					r.add(word[i]);
					cl.addGraduateRanking(sub1, r);
				}				
				
				else if(word[0].equals("DISPLAY") && word[1].equals("COMPANY"))
				{
					//System.out.println("displayCompany");
					cl.displayCompany(word[2]);
				}
				
				else if(word[0].equals("DISPLAY") && word[1].equals("GRADUATE"))
				{
					//System.out.println("displayGraduate");
					gl.displayGraduate(word[2]);
				}
				
				else if(word[0].equals("DISPLAY") && word[1].equals("RANKING"))
				{
					//System.out.println("displayRanking");
					cl.displayRanking(word[2]);
				}
				
				else if(word[0].equals("UPDATE") && word[1].equals("CAPACITY"))
				{
					//System.out.println("updateCapacity");
					String sub1 = word[2].substring(0,word[2].length()-1);	
					int cp = Integer.parseInt(word[3]);
					cl.updateCapacity(sub1, cp);			
				}
				
				else if(word[0].equals("UPDATE") && word[1].equals("CGPA"))
				{
					//System.out.println("updateCGPA");
					String sub1 = word[2].substring(0,word[2].length()-1);
					int cg = Integer.parseInt(word[3]);
					gl.updateCGPA(sub1, cg);
				}
				
				else if(word[0].equals("UPDATE") && word[1].equals("GRADUATE") && word[2].equals("PREFERENCE"))
				{
					//System.out.println("updatePreference");
					String sub1 = word[3].substring(0,word[3].length()-1);
					int i = 4;
					LinkedList<String> r = new LinkedList<>();
					while(i+1 < word.length)
					{
						r.add(word[i].substring(0,word[i].length()-1));
						i++;
					}
					r.add(word[i]);
					gl.updatePreference(sub1, r);
				}
				
				else if(word[0].equals("DELETE") && word[1].equals("COMPANY"))
				{
					//System.out.println("deleteCompany");
					cl.deleteCompany(word[2],gl);
				}
				
				else if(word[0].equals("DELETE") && word[1].equals("GRADUATE") && word[2].equals("COMPANY"))
				{
					String sub1 = word[3].substring(0, word[3].length()-1);
					cl.deleteGraduateCompany(sub1, word[4], gl);
				}
				
				else if(word[0].equals("DELETE") && word[1].equals("GRADUATE"))
				{
					//System.out.println("deleteGraduate");
					gl.deleteGraduate(word[2],cl);
				}
				
				else if(word[0].equals("MATCH"))
				{
					//System.out.println("match");
					L.match(gl,cl);
				}
			}
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println(elapsedTime);
		}
		catch (FileNotFoundException e)
		{
	 		System.out.println("File not found");
		}
	}
 }
 
