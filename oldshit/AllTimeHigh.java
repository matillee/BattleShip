import java.io.BufferedReader;
import java.io.FileReader;


public class AllTimeHigh {
	
//	BufferedReader textScan = new BufferedReader(new FileReader("MinTextfil.txt"));
	
	float bestDamageResult;
	String bestDamagePerson;
	float bestHitResult;
	String bestHitPerson;
	
	public AllTimeHigh(float damageResult, String damagePerson, float hitResult, String hitPerson) {
		if (damageResult > bestDamageResult) {
			bestDamageResult = damageResult;
			bestDamagePerson = damagePerson;
		}
		if (hitResult > bestHitResult) {
			bestHitResult = hitResult;
			bestHitPerson = hitPerson;
		}
			
	}

	public void damagePercent(){
		
		System.out.println("Best damage percent is " + bestDamageResult + " by " + bestDamagePerson);
		
	}
	
	public void hitPercent() {
		System.out.println("Best hit percent is " + bestHitResult +" by " + bestHitPerson);
	}
	
	// spara statisik i en fil
	// hur många vinster, spelare.
}
