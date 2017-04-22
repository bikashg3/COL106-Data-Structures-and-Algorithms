/**
 *
 * @author Bikash Gpt
 */


//This class  maintains heap of event heaps
public class HeapOfHeaps{
	private final Heap<Event> R;
	private int size;

	public HeapOfHeaps(){
		R = new Heap<>();
		size = 0;
	}

//Add an event heap to the heap of heaps
	public void addEvent(Event H){
		R.insert(H.maxScore(), H);
		size++;
	}

//Removes an event H from the heap of heaps
	public void deleteEvent(Event H){
		if(R.deleteValue(H))
			size--;
	}

//Deletes the participant P entirely from all events in the heap of heaps
	public void deleteParticipant(Participant P){
		if(size == 0){
			return;
		}
		Event[] Evs = new Event[size];
		int i = 0;
		while(!R.isEmpty()){
			Evs[i] = R.removeMax();
			Evs[i].deleteParticipant(P);
			i++; 
		}
		for(i = 0;i < size;i++){
			R.insert(Evs[i].maxScore(),Evs[i]);
		}
	}

//Returns the number of events in the heap of heaps
	public int noOfEvents(){
		return size;
	}

//Returns the top three performer across all events in the heap of heaps
	public void topThree(){
		int i, j, count ;
		int[] score = new int[3];
		Event[] events = new Event[3];
		for(i = 0;i < 3;i++){
			score[i] = -1;
		}
		i = 0;
		Event[] Evs = new Event[3];
		Participant[] Ps = new Participant[3];
		int[] inserts = new int[3];
		while(i < 3 && !R.isEmpty()){
			Evs[i] = R.removeMax();
			j = i;
			count = 0;
			while(j < 3 && !Evs[i].H.isEmpty()){
				if(score[j] == -1){
					score[j] = Evs[i].H.max();
					Ps[j] = Evs[i].H.removeMax();
					inserts[count++] = j;
					events[j] = Evs[i];
				}
				else if(score[j] < Evs[i].H.max()){
					if(j < 2){
						score[j+1] = score[j];
						Ps[j+1] = Ps[j];
						events[j+1] = events[j];
					}
					score[j] = Evs[i].H.max();
					Ps[j] = Evs[i].H.removeMax();
					inserts[count++] = j;
					events[j] = Evs[i];
				}
				j++;
			}
			for(j = 0;j < count;j++)
				Evs[i].H.insert(score[inserts[j]], Ps[inserts[j]]);
			i++;
		}

		for(j = 0;j < i;j++){
			R.insert(Evs[j].maxScore(), Evs[j]);
		}

		for(i = 0;i < 3 && score[i] != -1;i++){
			if(score[i] == 0)
				break;
			System.out.println(Ps[i].ID + ", " + Ps[i].participantName + ", "
				+ Ps[i].universityName + ", " + events[i].ID + ", "
				+ events[i].eventName + ", " + score[i]);
		}
	}

//Returns top three participants from different events in the heap of heaps
	public void topThreeEvents(){
		int i = 0;
		int[] score = new int[3];
		Participant[] P = new Participant[3];
		Event[] Evs = new Event[3];
		while(i < 3 && !R.isEmpty()){
			score[i] = R.max();
			Evs[i] = R.removeMax();
			if(score[i] == 0){
				i++;
				break;
			}
			P[i] = Evs[i].H.removeMax();
			System.out.println(P[i].ID + ", " + P[i].participantName + ", "
				+ P[i].universityName + ", " + Evs[i].ID + ", "
				+ Evs[i].eventName + ", " + score[i]);
			Evs[i].H.insert(score[i], P[i]);
			i++;
		}
		for(int j = 0;j < i;j++){
			R.insert(score[j], Evs[j]);
		}
	}

//Search for an event with ID as eventID in heap of heaps
	public Event find(String ID) throws NullPointerException{
		if(size == 0)
			return null;
		Event H = R.find(new Event(ID, "", ""));
		return H;
	}

//Prints the heap of heaps in preorder fashion
	public void print(){
		R.print();
	}


public void topFive(){
		int i, j, count ;
		int[] score = new int[5];
		Event[] events = new Event[5];
		for(i = 0;i < 5;i++){
			score[i] = -1;
		}
		i = 0;
		Event[] Evs = new Event[5];
		Participant[] Ps = new Participant[5];
		int[] inserts = new int[5];
		while(i < 5 && !R.isEmpty()){
			Evs[i] = R.removeMax();
			j = i;
			count = 0;
			while(j < 5 && !Evs[i].H.isEmpty()){
				if(score[j] == -1){
					score[j] = Evs[i].H.max();
					Ps[j] = Evs[i].H.removeMax();
					inserts[count++] = j;
					events[j] = Evs[i];
				}
				else if(score[j] < Evs[i].H.max()){
					if(j < 4){
						score[j+2] = score[j];
						Ps[j+2] = Ps[j];
						events[j+2] = events[j];
					}
					score[j] = Evs[i].H.max();
					Ps[j] = Evs[i].H.removeMax();
					inserts[count++] = j;
					events[j] = Evs[i];
				}
				j++;
			}
			for(j = 0;j < count;j++)
				Evs[i].H.insert(score[inserts[j]], Ps[inserts[j]]);
			i++;
		}

		for(j = 0;j < i;j++){
			R.insert(Evs[j].maxScore(), Evs[j]);
		}

		for(i = 0;i < 5 && score[i] != -1;i++){
			if(score[i] == 0)
				break;
			System.out.println(Ps[i].ID + ", " + Ps[i].participantName + ", "
				+ Ps[i].universityName + ", " + events[i].ID + ", "
				+ events[i].eventName + ", " + score[i]);
		}
	}



	public void topFiveEvents(){
		int i = 0;
		int[] score = new int[5];
		Participant[] P = new Participant[5];
		Event[] Evs = new Event[5];
		while(i < 5 && !R.isEmpty()){
			score[i] = R.max();
			Evs[i] = R.removeMax();
			if(score[i] == 0){
				i++;
				break;
			}
			P[i] = Evs[i].H.removeMax();
			System.out.println(P[i].ID + ", " + P[i].participantName + ", "
				+ P[i].universityName + ", " + Evs[i].ID + ", "
				+ Evs[i].eventName + ", " + score[i]);
			Evs[i].H.insert(score[i], P[i]);
			i++;
		}
		for(int j = 0;j < i;j++){
			R.insert(score[j], Evs[j]);
		}
	}
}