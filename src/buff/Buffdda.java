package buff;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import logic.GameLogic;

public class Buffdda implements BaseBuff {
	
		private Map<String, Integer> orbToBuff = new HashMap<>(){{
		    put("AttackOrb", 1);
		    put("DefenseOrb", 2);
		}};
		private final String buffDescription="gain more 20% shield from defense orb when playing attack and defense orb in the same turn";
		public void reslove(Map<String, Integer> result1) {
			// TODO uto-generated method stub
			if(result1.keySet().contains("AttackOrb")&&result1.keySet().contains("DefenseOrb"))
				GameLogic.allPlayerBuffThatActivate.replace(new Buffdda() ,true);
				
	
			}
		
		
		@Override
		public boolean equals(Object buff) {
			 if (buff == this) {
		            return true;
		        }
			 
			 return buff instanceof Buffdda;
		    }

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
