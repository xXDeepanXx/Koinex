
public class Data {

	public static void main(String[] args) {

		Koinex data= new Koinex();
		Thread fetchData = new Thread(data);
		fetchData.start();

	}

}
