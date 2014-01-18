package lab2d.math;

import static lab2d.math.Unit.*;

/**Precomputes trigonometric function outputs.*/
public class Calc
{
	/**Returned trig values are their decimal values times this (instead of being 1 or 0 as ints).*/
	public static final int TRIG_FACTOR = 1000;
	
	/**How far apart (in lab2d angle units) each measurement will be.*/
	private static final int ANGULAR_RESOLUTION = DEGREE;
	
	private static final int[] sines = new int[FULL_CIRCLE / ANGULAR_RESOLUTION],
			cosines = new int[FULL_CIRCLE / ANGULAR_RESOLUTION],
			tangents = new int[FULL_CIRCLE / ANGULAR_RESOLUTION];
	
	static
	{
		for(int n = 0; n < FULL_CIRCLE / ANGULAR_RESOLUTION; n++)
		{
			int intAngle = n * ANGULAR_RESOLUTION;
			double angle = toRadians(intAngle);
			sines[n] = (int)(java.lang.Math.sin(angle) * TRIG_FACTOR);
			cosines[n] = (int)(java.lang.Math.cos(angle) * TRIG_FACTOR);
			tangents[n] = (int)(java.lang.Math.tan(angle) * TRIG_FACTOR);
		}
	}
	
	
	/**@param angle the angle to take the sine of, in lab2d angle units.
	 * @return the sine of the given angle, out of TRIG_FACTOR instead of out of 1.*/
	public static int sin(int angle)
	{
//		return (int)(TRIG_FACTOR*java.lang.Math.sin(toRadians(angle)));
		return sines[angle % FULL_CIRCLE / ANGULAR_RESOLUTION];
	}
	
	/**@param angle the angle to take the cosine of, in lab2d angle units.
	 * @return the cosine of the given angle, out of TRIG_FACTOR instead of out of 1.*/
	public static int cos(int angle)
	{
//		return (int)(TRIG_FACTOR*java.lang.Math.cos(toRadians(angle)));
		return cosines[angle % FULL_CIRCLE / ANGULAR_RESOLUTION];
	}
	
	/**@param angle the angle to take the tangent of, in lab2d angle units.
	 * @return the tangent of the given angle, out of TRIG_FACTOR instead of out of 1.*/
	public static int tan(int angle)
	{
//		return (int)(TRIG_FACTOR*java.lang.Math.tan(toRadians(angle)));
		return tangents[angle % FULL_CIRCLE / ANGULAR_RESOLUTION];
	}
}
