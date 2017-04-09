
public class Client{
	private int serviceTime;
	private int id;
	private int arrivialTime;
	
	Client(int id, int serviceTime, int arrivialTime){
		this.id = id;
		this.serviceTime = serviceTime;
		this.arrivialTime = arrivialTime;
	}
	
	public int getId(){
		return id;
	}
	
	public int getServiceTime(){
		return serviceTime;
	}
	
	public int getArrivialTime(){
		return arrivialTime;
	}
	
	public String toString(){
		return ("\n Client  id : " + id + " Arrivial Time : " + arrivialTime + " Service Time : " + serviceTime );
	}
}
