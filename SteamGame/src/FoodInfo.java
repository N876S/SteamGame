
public class FoodInfo {
	/*
	 * Index Types (x value)
	 * 0 : Pizza
	 * 1 : 
	 * 2 : 
	 */
	
	/*
	 * Index Stats (y value)
	 * 0 : Damage
	 * 1 : Health
	 * 2 : Movement Speed
	 * 3 : Attack Length
	 * 4 : Cost
	 * 5 : Cooldown
	 * 6 : Range
	 */
	
	/*
	 * Rarity Codes
	 * c : common
	 * u : uncommon
	 * r : rare
	 * e : epic
	 * l : legendary
	 * x : exclusive
	 */
	
	static final int[][] defaultStats = {
			{10},	//0
			{300},	//1
			{1},	//2
			{60},	//3
			{10},	//4
			{120},	//5
			{50}	//6
	};
	static final char[] rarity = {
			'c' 
	};
	static final String[] imageStrings = {
			"res/pizza.png"
	};
}
