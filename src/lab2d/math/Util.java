package lab2d.math;

public class Util
{
	public static int min(int... entries)
	{
		int min = entries[0];
		for(int c = 1; c < entries.length; c++)
			if(entries[c] < min)
				min = entries[c];
		return min;
	}
	
	public static long min(long... entries)
	{
		long min = entries[0];
		for(int c = 1; c < entries.length; c++)
			if(entries[c] < min)
				min = entries[c];
		return min;
	}
}
