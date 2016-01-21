import java.util.ArrayList;

/**
 * k means�����㷨
 * @author xiaying
 */
public class KMeansMethod {
	private ArrayList<MPoint> points;
	
	private int K;
	
	private MPoint[] center;
	
	public KMeansMethod(){}
	
	/**
	 * �������ݣ��Լ��������
	 * @param points
	 * @param k
	 */
	public void setData(ArrayList<MPoint> points, int k)
	{
		this.K = k;
		this.points = points;
		
		//�������K�����ĵ㣬����֤���ĵ㲻�ظ�
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
	 * k means ����
	 */
	public void K_Means_cluster_method()
	{
		//����ÿ������������
		for(int i = 0 ; i < points.size() ; i++)
		{
			MPoint p = points.get(i);
			p.setType(getCloserCenter(p));
		}
		
		//ʹ��ÿ������������ƽ��λ�����¼������ĵ�λ��
		float dis = 0;
		for(int i = 0 ; i < center.length ; i++)
		{
			dis += calCenterPosition(i);
		}
		
		//���ĵ��ƶ�����С�ڶ�ֵʱ�����������
		if(dis > 3)
		{
			K_Means_cluster_method();
		}
	}
	
	/**
	 * �������Ͻ������ĵ�
	 * @param p Ҫ����ĵ�
	 * @return ���ĵ���
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
	 * �������ĵ��µ�λ�ã���������ԭλ�õľ���
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
