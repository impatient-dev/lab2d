package lab2d.math;

/**Contains static unit-related stuff. THIS CLASS IS OBSOLETE! Its unit conventions are no longer used.
 * 
 * Internally, this program uses its own system of units. These 
 * Since all calculations are done with integers, these units are intended to be rather large,
 * so that they have enough precision for a decent simulation.
 * 
 * NOTE: all times are in milliseconds; adjust speeds, etc. accordingly.
 * 
 * These units must be consistent such that real-world physics math works unaltered (except for the constants);
 * code elsewhere must be able to do f=ma directly, without needing to multiply by some extra constant.
 * 
 * A note on precision: it is perfectly safe to store angles with ints, but other types generally require longs
 * unless they are used in restricted contexts where they will stay small.
 * For convenience in these restricted contexts, many constants here are defined as ints even though
 * general-purpose usage of them requires longs.*/
public class Unit
{
	/*ANGLES*/
	
	/**1 degree in the internal angle units used by this program. This program currently uses tenths of a degree.*/
	public static final int DEGREE = 10;
	/**Pi, in the internal angle units used by this program.*/
	public static final int PI = (int)(Math.PI * DEGREE);//XXX this is so ridiculously wrong!
	/**360 degrees,in the internal angle units used by this program.*/
	public static final int FULL_CIRCLE = 360 * DEGREE;
	/**Converts degrees to radians.*/
	public static double degreesToRadians(double degrees){return degrees * Math.PI / 180;}
	/**Converts the internal angle units used by this program to radians.*/
	public static double toRadians(int angle){return angle * Math.PI / DEGREE / 180;}
	/**Converts radians to the internal angle units used by this program*/
	public static int fromRadians(double angle){return (int)(angle * 180 * DEGREE / Math.PI);}
	
	
	/*LENGTH*/
	
	/**1 meter in the internal length units used by this program. This program currently uses thousandths of a millimeter (millionths of a meter).*/
	public static final int METER = 1;//000000;
	/**1 kilometer in this program's internal length units.*/
	public static final long KILOMETER = 1000 * METER;
	
	
	/*MASS*/
	
	/**1 kilogram in the internal mass units used by this program. This program currently uses grams.*/
	public static final int KILOGRAM = 1;//000;
	
	
	public static final int SECOND = 1;//000, NEWTON = KILOGRAM * METER / SECOND / SECOND;
}
