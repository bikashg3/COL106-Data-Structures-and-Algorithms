/**
 *
 * @author Bikash Gpt
 */


//This Class contains details about an event and heap of participants
public class Event{
	public String ID;
	public String eventName;
	public String eventDescription;
	public int noOfParticipants;
	public Heap<Participant> H;              //Heap of participants

	public Event(String id, String name, String edscrp){
		ID = id;
		eventName = name;
		eventDescription = edscrp;
		noOfParticipants = 0;
		H = new Heap<>();
	}

//To add a new participant to the heap
	public void addParticipant(Participant P, int score){
		H.insert(score, P);
		noOfParticipants++;
	}

//To add a new paricipant to the heap without score
	public void addParticipant(Participant P){
		H.insert(0, P);
		noOfParticipants++;
	}

//Updates the score of a given participant
	public void updateParticipant(Participant P, int score) throws NullPointerException{
		H.modifyKeyOfNode(P, score);
	}

//Deletes a specific participant
	public void deleteParticipant(Participant P){
		if(H.deleteValue(P))
			noOfParticipants--;
	}


//Return the maximum score of participant in the event
	public int maxScore(){
		if(!H.isEmpty())
			return H.max();
		else
			return -1;
	}

//Top three performers in the event
	public void topThree(){
		int i = 0;
		int[] score = new int[3];
		Participant[] P = new Participant[3];
		while(i < 3 && !H.isEmpty()){
			score[i] = H.max();
			P[i] = H.removeMax();
			if(score[i] == 0){
				i++;
				break;
			}
			System.out.println(P[i].ID + ", " + P[i].participantName + ", " + 
				P[i].universityName + ", " + score[i]);
			i++;
		}
		for(int j = 0;j < i;j++){
			H.insert(score[j], P[j]);
		}
	}

//Returns the number of participants in the event
	public int getSize(){
		return noOfParticipants;
	}

//To check whether two objects are equal or not
	public boolean equals(Object o) {
 
        // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }

        if (!(o instanceof Event)) {
            return false;
        }
         
        // typecast o to Event so that we can compare data members 
        Event c = (Event) o;
         
        // Compare the data members and return accordingly 
        return c.ID.equals(ID);
    }

//Prints the event heap in preorder fashion
    public void print(){
    	H.print();
    }

//Returns the string equivalent of Event
    public String toString(){
    	return eventName;
    }

    //Top Five performers in the event
	public void topFive(){
		int i = 0;
		int[] score = new int[5];
		Participant[] P = new Participant[5];
		while(i < 5 && !H.isEmpty()){
			score[i] = H.max();
			P[i] = H.removeMax();
			if(score[i] == 0){
				i++;
				break;
			}
			System.out.println(P[i].ID + ", " + P[i].participantName + ", " + 
				P[i].universityName + ", " + score[i]);
			i++;
		}
		for(int j = 0;j < i;j++){
			H.insert(score[j], P[j]);
		}
	}
}