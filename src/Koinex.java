import io.restassured.response.Response;

public class Koinex implements Runnable {
	Response response;
	float XRP_Prev;
	int MaxDifference = 10;
	float AbsoluteXrpDifference = 1;
	public void GetData()
	{
		System.out.println("Refereshing Data...");
		try {
			response = RestHTTP.DoGet();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public float getRipple()
	{
		float XRP = 0;
		try {
			//System.out.println(response.jsonPath().get("stats.XRP.last_traded_price").toString());
			XRP = response.jsonPath().getFloat("stats.XRP.last_traded_price");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Old_Value :   " + XRP_Prev   );
		System.out.println("New_Value :   " + XRP   );
		float diff = XRP - XRP_Prev;
		
		if (Math.abs(diff) > AbsoluteXrpDifference)
		{	if (diff > 0)
				System.out.println("Ripple Price Increased by :      " + Math.abs(diff));
			else if (diff < 0)
				System.out.println("Ripple Price Decreased by :      " + Math.abs(diff));
		}
		XRP_Prev = XRP;
		return XRP;
	}
	private Boolean ComputeDifference(int OldPrice,int NewPrice)
	{
		int diff = Math.abs(((OldPrice - NewPrice)/OldPrice)*100);
		if (diff > MaxDifference)
			return true;
		else 
			return false;
		
	}
	@Override
	public void run() {
		while (true)
		{
			this.GetData();
			this.getRipple();
			try {
				Thread.sleep(25000);
			} catch (InterruptedException e) {}
		}
	}

}
