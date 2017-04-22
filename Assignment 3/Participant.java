/**
 *
 * @author Bikash Gpt
 */




import java.util.Objects;

//Class containing details about participant
public class Participant{
	public String ID; 
	public String participantName; 
    public String universityName;

	public Participant(String id, String name, String univ){
		ID = id;
		participantName = name;
		universityName = univ;
	}

//String equivalent of object of Participant
        @Override
	public String toString(){
		return universityName;
	}

//To check whether two objects are equal or not
        @Override
	public boolean equals(Object o) {
 
        // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }
 
        if(o instanceof String){
            String s = (String) o;
            return s.equals(ID);
        }

        if (!(o instanceof Participant)) {
            return false;
        } 
        // typecast o to Participant so that we can compare data members 
        Participant c = (Participant) o;
         
        // Compare the data members and return accordingly 
        return c.ID.equals(ID);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.ID);
        return hash;
    }
}