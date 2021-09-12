/**
 * Represents information about a city Maman13 question1 2019a
 * @author Maytal Twig
 *version 07/12/18
 */
public class Country {
	private String _countryName;
	private City [] _cities;
	private int _noOfCities;  
	public final int MAX_NUM_CITIES=1000;
	public final int MIN_VAL=0;

	/**
	 *  A constructor that receives a country name and initializes the array to Maximal.
	 */

	public Country(String countryName)
	{
		_countryName=countryName;
		_cities=new City[MAX_NUM_CITIES];
		_noOfCities=MIN_VAL;
	}

	 /**
	  * Returns new cities array.
	  */
	
	public City[] getCities(){
		City[] c3=new City[_noOfCities];
		for (int i=0;i<_noOfCities;i++)
			c3[i]=new City(_cities[i]);
		return c3;
	}

	/**Adds the given city to the country.
	 * @param name
	 * @param xCenter
	 * @param yCenter
	 * @param xStation
	 * @param yStation
	 * @param numOfResidents
	 * @param noOfNeighborhoods
	 * @return true if there is place in the array to add a city
	 * @return false if there is no place 
	 */

	public boolean addCity(String name, double xCenter, double yCenter, double  xStation, double  yStation, long numOfResidents, int noOfNeighborhoods){
		if (_noOfCities==MAX_NUM_CITIES)
			return false;
		_cities[_noOfCities++]=new City(name,xCenter,yCenter, xStation,  yStation, numOfResidents, noOfNeighborhoods);
		return true;
	}

	/**
	 * Returns the number of residents of the country.
	 */

	public long getNumOfResidents() {
		int residents=MIN_VAL;
		for(int i=0;i<_noOfCities;i++){
			residents+=_cities[i].getNumOfResidents();
		}
		return residents;
	}

	/**
	 * Returns the The longest distance between two cities in the country.
	 */

	public double longestDistance(){
		if (_noOfCities<2)//Stop conditions
			return 0;

		double maxDistance=MIN_VAL;
		for (int i=0;i<_noOfCities;i++)
			for(int j=i+1;j<_noOfCities;j++)
				if (_cities[i].getCityCenter().distance(_cities[j].getCityCenter())>maxDistance)//Determines the most distant cities
					maxDistance=_cities[i].getCityCenter().distance(_cities[j].getCityCenter());
		return maxDistance;
	}

	/**
	 * printing all the cities in the country that are located north of a certain city (above the city).
	 * @param cityName
	 */

	public String citiesNorthOf (String cityName){
		City now = cityByName(cityName); 
		if (now.equals(null))
			return ("There is no city with the name "+cityName);//Stop conditions

		String str=" ";
		boolean flag=false;
		str="The cities north of "+cityName+" are:";
		for (int i=0;i<_noOfCities;i++)
			if(_cities[i].getCityCenter().isAbove(now.getCityCenter())){//Checks if there is a more northern city according to the method in Point
				str+="\n\n"+_cities[i];
				flag=true;
			}
		if (!flag) 
			return "There are no cities north of "+cityName;
		return str;
	}
	/**
	 * checking the array by city name.
	 */

	private City cityByName (String cityName){
		for (int i=0;i<_noOfCities;i++)
			if (cityName.equals(_cities[i].getCityName()))
				return new City(_cities[i]);
		return null;
	}

	/**
	 * the most south city in the country
	 */

	public City southernmostCity (){
		if (_noOfCities==0)
			return null;
		City mostSouthernCity=(_cities[0]);
		for (int i=1;i<_noOfCities;i++){
			if(_cities[i].getCityCenter().isUnder(mostSouthernCity.getCityCenter())){

				mostSouthernCity=(_cities[i]);
			}
		}
		return new City (mostSouthernCity);
	}

	/**
	 * Returns the name of the country.
	 */

	public String getCountryName() {
		return _countryName;
	}

	/**
	 * Returns the number of the cities in the country.
	 */

	public int getNumOfCities(){
		return _noOfCities;
	}

	/**
	 * unify between two cities.
	 * @param cityName1
	 * @param cityName2
	 */

	public City unifyCities (String cityName1, String cityName2){
		City uniCity=null;
		int city1Index=getIndexByCityName(cityName1);
		int city2Index=getIndexByCityName(cityName2);
		City c1=_cities[city1Index];
		City c2=_cities[city2Index];
		String uniName=cityName1+"-"+cityName2;
		long uniRes=c1.getNumOfResidents()+c2.getNumOfResidents();
		int uniNeighborhoods=c1.getNoOfNeighborhoods()+c2.getNoOfNeighborhoods();
		double XuniCityCenter=(c1.getCityCenter().getX()+c2.getCityCenter().getX())/2;
		double YuniCityCenter=(c1.getCityCenter().getY()+c2.getCityCenter().getY())/2;
		Point uniCentralStation=c2.getCentralStation();
		if (c1.getCentralStation().isLeft(c2.getCentralStation()))//Sets the station by the western city using the method from point class
			uniCentralStation=c1.getCentralStation();
		int lessRes=city1Index;
		int moreRes=city2Index;
		if(c2.getNumOfResidents()<=c1.getNumOfResidents()){
			lessRes=city2Index;
			moreRes=city1Index;
		}
		uniCity=new City(uniName, XuniCityCenter, YuniCityCenter
				, uniCentralStation.getX(), uniCentralStation.getY(), uniRes, uniNeighborhoods);
		_cities[lessRes]=_cities[_noOfCities-1];
		_cities[moreRes]=uniCity; //The new city is the city with more residents
		_cities[_noOfCities-1]=null;
		_noOfCities--;
		return uniCity;
	}
	
	/**
	 * Getting the index by the city name.
	 */

	private int getIndexByCityName (String checkName)
	{
		for (int i=0;i<_noOfCities;i++)
			if (_cities[i].getCityName().equals(checkName))
				return i;
		return -1;
	}

	/**
	 * Returns a string representation of City in the format
	 *City Name: Tel Aviv
	 *City Center: (5.0,8.0)
	 *Central Station: (3.0,4.0)
	 *NumOfResidents: 1004
	 *No Of Neighborhoods: 5
	 */

	public String toString(){
		String str="Cities of " +_countryName+":";
		for (int i=0;i<_noOfCities;i++)
			str+="\n\n"+_cities[i];

		return str;
	}
}
