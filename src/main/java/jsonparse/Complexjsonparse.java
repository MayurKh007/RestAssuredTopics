package jsonparse;

import io.restassured.path.json.JsonPath;

public class Complexjsonparse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(Payload.courseprice());

		// Print No of courses returned by API

		int count = js.getInt("courses.size()");
		System.out.println(count);

		// Print Purchase Amount

		int totalpurchaseamount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalpurchaseamount);

		// Print Title of the first course
		String firstcoursetitle = js.get("courses[2].title");
		System.out.println(firstcoursetitle);

		// Print All course titles and their respective Prices

		for (int i = 0; i < count; i++) {
			String allcoursetitle = js.get("courses[" + i + "].title");
			int allcourseprice = js.getInt("courses[" + i + "].price");

			System.out.println(allcoursetitle);
			System.out.println(allcourseprice);

		}
		
		
		//Print no of copies sold by RPA Course
		
		for (int i = 0; i < count; i++) {
			
			String allcoursetitle = js.get("courses[" + i + "].title");
			
			if(allcoursetitle.equalsIgnoreCase("RPA"))
			{
				int copies= js.get("courses[" + i + "].copies");
				
				System.out.println(" No of copies sold by RPA Course is " +copies);
				
				break;
			}
		}
	}

}
