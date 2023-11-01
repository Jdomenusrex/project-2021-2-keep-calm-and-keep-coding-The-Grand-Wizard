package buff;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import logic.GameLogic;



public class Buffaad implements BaseBuff{
	private  Map<String, Integer> orbToBuff = new HashMap<>(){{
	    put("AttackOrb", 2);
	    put("DefenseOrb", 1);
	}};
	private final String buffDescription="deal more 20% damage from attack orb when playing attack and defense orb in the same turn";
	public void reslove(Map<String, Integer> result1) {
	
		if(result1.keySet().contains("AttackOrb")&&result1.keySet().contains("DefenseOrb"))
			GameLogic.allPlayerBuffThatActivate.replace(this,true);
	
		}
	
	
	@Override
	public boolean equals(Object buff) {
		 if (buff == this) {
	            return true;
	        }
		 return buff instanceof Buffaad;
	    }

	 @Override
	    public int hashCode() {
	        return Objects.hash( );
	    }


	@Override
	public Map<String, Integer> getOrbtobuff() {
		// TODO Auto-generated method stub
		return this.orbToBuff;
	}


	public String getBuffDescription() {
		return buffDescription;
	}



	

}
