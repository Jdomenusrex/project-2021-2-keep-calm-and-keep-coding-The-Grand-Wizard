package buff;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import logic.GameLogic;

public class BuffHHa implements BaseBuff{
	private  Map<String, Integer> orbToBuff = new HashMap<>(){{
	    put("HealOrb", 2);
	    put("AttackOrb", 1);
	}};
	private final String buffDescription="For each attack orb that plays, heal 4 HP";
	public void reslove(Map<String, Integer> result1) {
	
		if(result1.keySet().contains("AttackOrb"))
			
			GameLogic.allPlayerBuffThatActivate.replace(this,true);
			
		}
			
	
		
	
	
	@Override
	public boolean equals(Object buff) {
		 if (buff == this) {
	            return true;
	        }
		 return buff instanceof BuffHHa;
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
