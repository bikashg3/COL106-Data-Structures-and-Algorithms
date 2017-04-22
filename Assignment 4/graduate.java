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