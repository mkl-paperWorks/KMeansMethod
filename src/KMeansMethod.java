import java.util.ArrayList;

/**
 * k means聚类算法
 * @author xiaying
 */
public class KMeansMethod {
	private ArrayList<MPoint> points;
	
	private int K;
	
	private MPoint[] center;
	
	public KMeansMethod(){}
	
	/**
	 * 设置数据，以及聚类个数
	 * @param points
	 * @param k
	 */
	public void setData(ArrayList<MPoint> points, int k)
	{
		this.K = k;
		this.points = points;
		
		//随机生成K个中心点，并保证中心点不重复
		center = new MPoint[k];
		int[] numrec = new int[k];
		for(int i = 0 ; i < k ; i++)
		{
			center[i] = new MPoint();
			int randnum;
			boolean is;
			
			do{
				is = false;
				randnum = (int)(Math.random() * points.size());
				for(int j = 0 ; j < i ; j++)
				{
					if(numrec[j] == randnum)
					{
						is = true;
						break;
					}
				}
			}while(is);
			
			numrec[i] = randnum;
			MPoint p = points.get(randnum);
			center[i].setPosition(p.getPosition());
		}
	}
	
	public MPoint[] getCenter() {
		return center;
	}

	public void update()
	{
		K_Means_cluster_method();
	}
	
	/**
	 * k means 方法
	 */
	public void K_Means_cluster_method()
	{
		//计算每个点所属区域
		for(int i = 0 ; i < points.size() ; i++)
		{
			MPoint p = points.get(i);
			p.setType(getCloserCenter(p));
		}
		
		//使用每个区域所属点平均位置重新计算中心点位置
		float dis = 0;
		for(int i = 0 ; i < center.length ; i++)
		{
			dis += calCenterPosition(i);
		}
		
		//中心点移动距离小于定值时迭代计算结束
		if(dis > 3)
		{
			K_Means_cluster_method();
		}
	}
	
	/**
	 * 计算距离较近的中心点
	 * @param p 要计算的点
	 * @return 中心点编号
	 */
	public int getCloserCenter(MPoint p)
	{
		float min = 0;
		int num = -1;
		
		for(int i = 0 ; i < center.length ; i++)
		{
			float dis = p.distance(center[i]);
			if(i == 0)
			{
				min = dis;
				num = i;
			}
			else if(dis < min)
			{
				min = dis;
				num = i;
			}
		}
		return num;
	}
	
	/**
	 * 计算中心点新的位置，并返回与原位置的距离
	 * @param k
	 * @return
	 */
	public float calCenterPosition(int k)
	{
		float x = 0;
		float y = 0;
		int num = 0;
		for(int i = 0 ; i < points.size(); i++)
		{
			if(points.get(i).getType() == k)
			{
				x += points.get(i).getX();
				y += points.get(i).getY();
				num++;
			}
		}
		MPoint p = new MPoint();
		p.setPosition(center[k].getPosition());
		center[k].setPosition(x/num, y/num);
		
		return p.distance(center[k]);
	}
}
