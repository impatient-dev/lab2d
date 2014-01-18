package lab2d.math;

import static lab2d.math.Unit.*;

/**A vector of 2 longs.*/
public class Vec2l implements Cloneable
{
	private long x,y;
	
	
	public Vec2l(long x, long y){this.x = x; this.y = y;}
	public Vec2l(Vec2l copy){this(copy.x, copy.y);}
	
	public Vec2l clone(){return new Vec2l(this);}
	
	/**Gets x.*/ public long x(){return x;}
	/**Gets y.*/ public long y(){return y;}
	/**Sets x.*/ public void x(long x){this.x = x;}
	/**Sets y.*/ public void y(long y){this.y = y;}
	/**Sets both components.*/public void set(long x, long y){this.x = x; this.y = y;}
	
	/**Gets a component.*/
	public long get(Component c)
	{
		if(c == Component.X)
			return x;
		return y;
	}
	/**Sets a component.*/
	public void set(Component c, long value)
	{
		if(c == Component.X)
			x = value;
		else
			y = value;
	}
	
	/*ARITHMETIC*/
	
	
	public Vec2l reverse(){return new Vec2l(-x, -y);}
	public void reverseLocal(){x = -x; y = -y;}
	
	public Vec2l add(Vec2l other){return new Vec2l(x + other.x, y + other.y);}
	public void addLocal(Vec2l other){x += other.x; y += other.y;}
	
	/**Subtracts another vector from this one, returning the new vector.*/
	public Vec2l subtract(Vec2l other){return new Vec2l(x - other.x, y - other.y);}
	/**Subtracts another vector from this one*/
	public void subtractLocal(Vec2l other){x -= other.x; y -= other.y;}
	
	public Vec2l multiply(long scalar){return new Vec2l(x * scalar, y * scalar);}
	public void multiplyLocal(long scalar){x *= scalar; y *= scalar;}
	
	public Vec2l divide(long scalar){return new Vec2l(x / scalar, y / scalar);}
	public void divideLocal(long scalar){x /= scalar; y /= scalar;}
	
	
	/*SPECIAL*/
	
	/**Returns the square of the magnitude of this vector.*/
	public long magnitude2(){return x*x + y*y;}
	/**Returns the magnitude of this vector. Unreliable if vector is close to zero, and slower than getting the square of the magnitude.*/
	public long magnitude(){return (long)Math.sqrt(magnitude2());}
	
	/**Returns the square of the distance between this vector and another one.*/
	public long distanceTo2(Vec2l other)
	{
		long dx = x - other.x, dy = y - other.y;
		return dx * dx + dy* dy;
	}
	
	/**Returns the distance to another vector. Involves a square root (expensive).*/
	public long distanceTo(Vec2l other)
	{
		long dist2 = distanceTo2(other);
		return (long)Math.sqrt(dist2);
	}
	
	/**Returns the dot product of this vector and another one.*/
	public long dot(Vec2l other){return this.x * other.x + this.y * other.y;}
	
	/**Get the direction of this vector.*/
	public int dir()
	{throw new UnsupportedOperationException("TODO");
//		return (int)(DEGREE * Math.atan((double)y / x));//TODO this is wrong
	}
	
	/**Returns this vector rotated by the provided angle counterclockwise.*/
	public Vec2l rotate(int angle)
	{
		return new Vec2l((Calc.cos(angle) * x - Calc.sin(angle) * y) / Calc.TRIG_FACTOR, (Calc.sin(angle) * x + Calc.cos(angle) * y) / Calc.TRIG_FACTOR);
	}
	/**Rotates this vector by the provided angle, counterclockwise.*/
	public void rotateLocal(int angle)
	{
		set((Calc.cos(angle) * x - Calc.sin(angle) * y) / Calc.TRIG_FACTOR, (Calc.sin(angle) * x + Calc.cos(angle) * y) / Calc.TRIG_FACTOR);
	}
	
	
	@Override public String toString(){return "("+x+","+y+")";}
}
