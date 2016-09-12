package net.mythril.stage; 

public class StageSelector 
{
	public int stageNum = 1;
	Stage1 s1 = new Stage1();
	
	public void loadStageData()
	{
		s1.loadAll();
	}
	
	public void select()
	{
		if(stageNum == 1)
		{
			s1.renderAll();
		}
	}
}
