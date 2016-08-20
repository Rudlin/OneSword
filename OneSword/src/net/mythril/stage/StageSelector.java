package net.mythril.stage;

public class StageSelector 
{
	public int stageNum;
	Stage1 s1 = new Stage1();
	public void select()
	{
		if(stageNum == 1)
		{
			s1.load();
		}
	}
}
