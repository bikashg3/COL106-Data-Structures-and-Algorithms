/**
 *
 * @author Bikash Gpt
 */


import java.io.*;

public class Simulate{
	public static HeapOfHeaps Rdz;
	public static SinglyLinkedList<Participant> Sll;

//This is the main function to read single enquiry from a given text file
	public static void main(String[] args) {
		 long startTime = System.currentTimeMillis();
        
        
            try{
			Rdz = new HeapOfHeaps();
			Sll = new SinglyLinkedList<>();
                     try (BufferedReader getcommands = new BufferedReader(new FileReader("q1_small.txt"))) {
                         String enquiry;
                         while((enquiry = getcommands.readLine()) != null){
                             doQuery(enquiry);
                         }
                     }
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		catch(NullPointerException n){
			System.out.println("Invalid Query Exception");
		}
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println("elapsed time: "+elapsedTime);
	}

//Execute a single enquiry from the file
	public static void doQuery(String enquiry) throws NullPointerException{
		String[] instructs1 = enquiry.split(", ");
		String[] instructs2 = instructs1[0].split(" ");
		int len = instructs2.length + instructs1.length - 1;
		String[] instructs = new String[len];
		int i;
		for(i = 0;i < instructs2.length;i++){
			instructs[i] = instructs2[i];
		}
		for(i = 1;i < instructs1.length;i++){
			instructs[instructs2.length + i - 1] = instructs1[i];
		}
		Participant P;
		Event Ev;

		if(instructs[0].equals("ADD")){
                    switch (instructs[1]) {
                        case "PARTICIPANT":
                            P = new Participant(instructs[2], instructs[3], instructs[4]);
                            Sll.addFirst(P);
                            break;
                        case "EVENT":
                            Rdz.addEvent(new Event(instructs[2], instructs[3], instructs[4]));
                            break;
                        default:
                            P = Sll.find(instructs[1]);
                            Ev = Rdz.find(instructs[2]);
                            if(Ev == null || P ==null)
                                throw new NullPointerException();
                            Ev.addParticipant(P);
                            Rdz.deleteEvent(Ev);
                            Rdz.addEvent(Ev);
                            break;
                    }
		}
		else if(instructs[0].equals("DELETE")){
			if(instructs[1].equals("PARTICIPANT")){
				P = Sll.find(instructs[2]);
				if(P == null)
					throw new NullPointerException();
				Rdz.deleteParticipant(P);
			}
			else if(instructs[1].equals("EVENT") && instructs[2].equals("PARTICIPANT")){
				Ev = Rdz.find(instructs[4]);
				P = Sll.find(instructs[3]);
				if(P == null || Ev == null)
					throw new NullPointerException();
				Ev.deleteParticipant(P);
			}
			else{
				Ev = Rdz.find(instructs[2]);
				if(Ev == null)
					throw new NullPointerException();
				Rdz.deleteEvent(Ev);
			}
		}
		else if(instructs[0].equals("UPDATE")){
			P = Sll.find(instructs[2]);
			Ev = Rdz.find(instructs[3]);
			if(P == null || Ev == null)
				throw new NullPointerException();
			Ev.updateParticipant(P, Integer.parseInt(instructs[4]));
			Rdz.deleteEvent(Ev);
			Rdz.addEvent(Ev);
		}
		else if(instructs.length > 1 && instructs[1].equals("IN")){
			Ev = Rdz.find(instructs[3]);
			if(Ev == null)
				throw new NullPointerException();
			Ev.topThree();
		}
		else if(instructs[0].equals("TOP3")){
			Rdz.topThree();
		}
                else if(instructs[0].equals("TOP5")){
                        Rdz.topFive();
                }
	}
}