package buff;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import logic.GameLogic;

public class BuffaaH implements BaseBuff {
	private  Map<String, Integer> orbToBuff = new HashMap<>(){{
	    put("HealOrb", 1);
	    put("AttackOrb", 2);
	}};
	private final String buffDescription="hp gain from heal orb deal damage to enemy";
	public void reslove(Map<String, Integer> result1) {
	
		
			GameLogic.allPlayerBuffThatActivate.replace(this,true);
			
		}
			
	
		
	
	
	@Override
	public boolean equals(Object buff) {
		 if (buff == this) {
	            return true;
	        }
		 return buff instanceof BuffaaH;
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
