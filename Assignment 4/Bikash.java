import java.util.*; 
import java.io.*; 

class graduate
{
	String graduateID;
	String graduateName;
	double cgpa;
	String[] Clist = new String[1000]; 
	boolean match;
	int cur;
	int psize;
	String hiredCompany;

	public graduate(String ID, String name, double cg)
	{
		graduateID = ID;
		graduateName = name;
		cgpa = cg;
		match = false;
		cur = 0;
		psize = 0;
		hiredCompany = "";
		for(int i = 0; i < 1000; i++)
		{
			Clist[i] = "-1";
		}
	}
}

class company
{
	String companyID;
	String companyInformation;
	int capacity;
	int size;
	String[] Glist = new String[5000];
	String[] hired = new String[capacity];
	
	public company(String ID, String info, int cap)
	{
		companyID = ID;
		companyInformation = info;
		capacity = cap;
		size = 0;
		Glist = new String[5000];
	    hired = new String[capacity];
		for (int i = 0; i < cap; i++)
		{
			hired[i] = "-1";
		}
		for (int i = 0; i < 5000; i++)
		{
			Glist[i] = "-1";
		}
	}
}

class companyList
{
	company[] list = new company[1000];
	int number;
	
	public int gethashcode(String ID)
	{
		int hs = ID.hashCode();
		return hs%1000;
	}
	
	public void addCompany(String ID, String info, int capacity)
	{
		int pos = gethashcode(ID);
		if(list[pos] == null)
		{
			list[pos] = new company(ID, info, capacity);
			number ++;
		}
		else
		{
			while(list[pos] != null)
			{
				pos++;
			}
			list[pos] = new company(ID, info, capacity);
			number ++;
		}
	}
	
	public void displayCompany(String ID)
	{
		int pos = gethashcode(ID);
		while(!list[pos].companyID.equals(ID))
		{
			pos++;
		}
		System.out.println(list[pos].companyInformation +", "+ list[pos].capacity);
	}

	public void addGraduateRanking(String cID, LinkedList<String> r)
	{
		int pos = gethashcode(cID);
		int i = 0;

		while(!list[pos].companyID.equals(cID))
		{
			pos++;
		}
		Iterator<String> itr = r.iterator();  
		while(itr.hasNext())
		{
			list[pos].Glist[i] = itr.next();
			i++;
		}
		
	}

	public void displayRanking(String cID)
	{
		int pos = gethashcode(cID);
		while(!list[pos].companyID.equals(cID))
		{
			pos++;
		}
		int i = 0;
		while(!list[pos].Glist[i+1].equals("-1"))
		{
			System.out.print(list[pos].Glist[i] +", ");
			i++;
		}
		System.out.print(list[pos].Glist[i]);
		System.out.println();		
	}

	public void updateCapacity(String cID, int cap)
	{
		int pos = gethashcode(cID);
		while(!list[pos].companyID.equals(cID))
		{
			pos++;
		}
		list[pos].capacity = cap;
	}

	public void deleteCompany(String ID, graduateList g)
	{
		int pos = gethashcode(ID);
		while(!list[pos].companyID.equals(ID))
		{
			pos++;
		}
		list[pos] = null;
		number --;	
		int i = 0;
		while(i < 5000)
		{
			if(g.list[i] == null)
				i++;
			else if(g.list[i] != null)
			{
				int j = 0;
				while(j<1000 && g.list[i].Clist[j] != "-1")
				{
					if(!g.list[i].Clist[j].equals(ID))
						j++;
					else if(g.list[i].Clist[j].equals(ID))
					{
						while(j < 1000 && g.list[i].Clist[j+1] != "-1")
						{
							g.list[i].Clist[j] = g.list[i].Clist[j+1];
							j++;
						}
					g.list[i].Clist[j] = "-1";
					g.list[i].psize --;
					}
				}
				i++;
			}
		}	
	}

	public int getCompany(String ID)
	{
		int pos = gethashcode(ID);
		while(!list[pos].companyID.equals(ID))
		{
			pos++;
		}
		return pos;		
	}
	
	public int searchGraduate(company c, String ID)
	{
		int i =0;
		while(!c.Glist[i].equals("-1"))
		{
			if(c.Glist[i].equals(ID))
				return i;
			i++;
		}
		return -1;
	}
	
	public void deleteGraduateCompany(String gID, String cID, graduateList gl)
	{
		int c = getCompany(cID);
		int g = gl.getGraduate(gID);
		int pos = searchGraduate(list[c], gID);
		if (pos == -1)
			{
				System.out.println("Exception");
				return;
			}
		else
		{
			while(!list[c].Glist[pos].equals("-1"))
			{
				list[c].Glist[pos] = list[c].Glist[pos+1];
				pos++;
			}
		}
		int i = 0;
		while(!gl.list[g].Clist[i].equals(cID) && !gl.list[g].Clist[i].equals("-1"))
		{
			i++;
		}
		if(gl.list[g].Clist[i].equals("-1"))
		{
			System.out.println("Exception");
			return;
		}
		else
		{
				while(!gl.list[g].Clist[i].equals("-1"))
				{
					gl.list[g].Clist[i] = gl.list[g].Clist[i+1];
					i++;
				}
		}		
		
	}
	
	public boolean compareGraduate(company c, String ID1, String ID2)
	{
		if(searchGraduate(c,ID1) < searchGraduate(c,ID2))
			return true;
		else
			return false;
	}
}

class graduateList
{
	graduate[] list = new graduate[5000];
	int number;
	
	public int getHashcode(String ID)
	{
		int hs = ID.hashCode();
		return hs%1000;
	}
	
	public void addGraduate(String ID, String name, double cgpa, LinkedList<String> r)
	{
		int pos = getHashcode(ID);
		while(list[pos] != null)
		{
			pos++;
		}
		list[pos] = new graduate(ID, name, cgpa);
		int i = 0;
		Iterator<String> itr = r.iterator();  
		while(itr.hasNext())
		{
			list[pos].Clist[i] = itr.next();
			i++;
			list[pos].psize ++;
		}
		number ++;
	}
	
	public void displayGraduate(String ID)
	{
		int pos = getHashcode(ID);
		while(!list[pos].graduateID.equals(ID))
		{
			pos++;
		}
		System.out.print(list[pos].graduateName + ", " + list[pos].cgpa);
		int i = 0;
		while(list[pos].Clist[i] != "-1")
		{
			System.out.print(", " + list[pos].Clist[i]);
			i++;
		}
		System.out.println();
	}

	public void updateCGPA(String ID, int cg)
	{
		int pos = getHashcode(ID);
		while(!list[pos].graduateID.equals(ID))
		{
			pos++;
		}
		list[pos].cgpa = cg;		
	}

	public void updatePreference(String ID, LinkedList<String> r)
	{
		int pos = getHashcode(ID);
		while(!list[pos].graduateID.equals(ID))
		{
			pos++;
		}
		int i;
		for(i = 0; i < 1000; i++)
		{
			list[pos].Clist[i] = "-1";
		}
		i = 0;
		Iterator<String> itr = r.iterator();  
		while(itr.hasNext())
		{
			list[pos].Clist[i] = itr.next();
			//System.out.println("update no. " + i + " to " + list[pos].Clist[i]);
			i++;
		}
		list[pos].psize = i;
		//displayGraduate(ID);
	}

	public void deleteGraduate(String ID, companyList cl)
	{
		int pos = getHashcode(ID);
		while(!list[pos].graduateID.equals(ID))
		{
			pos++;
		}
		int i = 0;
		while(i<1000)
		{
			if(cl.list[i] == null)
				i++;
			else if(cl.searchGraduate(cl.list[i], ID) == -1)
				i++;
			else if(cl.searchGraduate(cl.list[i], ID) != -1)
			{
				int j = cl.searchGraduate(cl.list[i], ID);
				while(j < 1000 && cl.list[i].Glist[j+1] != null)
				{
					cl.list[i].Glist[j] = cl.list[i].Glist[j+1];
					j++;
				}
				cl.list[i].Glist[j] = null;
				i++;
			}
		}
		list[pos] = null;
		number --;		
	}

	public int getGraduate(String ID)
	{
		int pos = getHashcode(ID);
		while(!list[pos].graduateID.equals(ID))
		{
			pos++;
		}
		return pos;
	}
}

public class Bikash
{
	
	public void match(graduateList g, companyList c)
	{
		for (int l = 0; l<1000; l++){
			//System.out.println("Iteration number l= "+ l + " has started");
			boolean change = false;
		for (int i = 0; i<5000; i++)
		{
			if(g.list[i] == null)    
				continue;											//No graduate at that position 
			else if(g.list[i].match == true || g.list[i].cur >= g.list[i].psize)
				{
					//System.out.println("graduate " + g.list[i].graduateID + " is ignored " +g.list[i].match + " " + g.list[i].cur + " " + g.list[i].psize);
				}											//graduate is hired or has tried all companies
			else //if(g.list[i].match == false && g.list[i].cur < g.list[i].psize)
			{
				change = true; 
				//System.out.println("graduate " + g.list[i].graduateID + " is being added, psize & cur = " +g.list[i].psize + " " + g.list[i].cur);
				String comp = g.list[i].Clist[g.list[i].cur];
				//System.out.println(comp);
				int m = c.getCompany(comp);
				int k = c.searchGraduate(c.list[m], g.list[i].graduateID);
				if(k == -1)
					{
						System.out.println("Exception");
						g.list[i].cur ++;
						//System.out.println("graduate not in company ");
					}
				else if(k != -1)       										//graduate found
				{
					//System.out.println("graduate found");
					if(c.list[m].size < c.list[m].capacity)      //company can hire more
					{
						c.list[m].hired[c.list[m].size] = g.list[i].graduateID;
						c.list[m].size ++;
						g.list[i].hiredCompany = c.list[m].companyID;
						g.list[i].match = true;
						//System.out.println("graduate " +g.list[i].graduateID + "hired in company " + c.list[m].companyID);
					}
					else if (c.list[m].size >= c.list[m].capacity)
					{
						//System.out.println("Company full");
						String temp = g.list[i].graduateID;
						int j = 0;
						while(j < c.list[m].capacity)
						{
							if(c.compareGraduate(c.list[m], temp, c.list[m].hired[j]) == true)
							{
								String a = c.list[m].hired[j];
								c.list[m].hired[j] = temp;
								temp = a;
								int q = g.getGraduate(temp);
								g.list[q].match = false;
								//System.out.println(g.list[q].graduateID + " is rejected");
								g.list[q].cur ++;
								g.list[q].hiredCompany = "";
								int p = g.getGraduate(c.list[m].hired[j]);
								//System.out.println(g.list[p].graduateID + " is hired");
								g.list[p].match = true;
								g.list[p].hiredCompany = c.list[m].companyID;
								j = 0;
							}
							else 
								g.list[i].cur ++;
								j++;
						}											//graduate has been accepted/rejected by his (cur)th company
					}
				}
			}
		}
		if (change == false)
			break;
	}
		int b = 0;
		while (b<5000)
		{
			if(g.list[b] == null)
					b++;
			else       
			{
				System.out.println(g.list[b].graduateID + ", " + g.list[b].hiredCompany);
				g.list[b].match = false;
				g.list[b].hiredCompany = "";
				b++;
			}                                                                                                                                                                                                                                                                                                                                                                                                                                                        
		}
		for(int w = 0; w < 1000; w++)
		 {
		 	if(c.list[w] != null)
		 		{
		 			for(int e = 0; e < c.list[w].capacity; e++)
		 			{
		 				c.list[w].hired[e] = "-1";
		 			}
		 			c.list[w].size = 0; 
		 		}
		 }

	}
}
