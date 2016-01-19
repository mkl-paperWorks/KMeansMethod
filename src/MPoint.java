/**
 * 自己的点类，位置为float类型
 * @author xiaying
 *
 */
public class MPoint {
	private float x;
	private float y;
	
	private int type;
	
	public MPoint(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public MPoint(){}
	
	public void setPosition(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public float[] getPosition()
	{
		return new float[]{x, y};
	}
	
	public float distance(MPoint p)
	{
		return (float)Math.hypot(this.x - p.getX(), this.y - p.getY());
	}

	public void setPosition(float[] position) {
		// TODO Auto-generated method stub
		this.x = position[0];
		this.y = position[1];
	}
}
