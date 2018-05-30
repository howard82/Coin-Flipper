package au.edu.rmit.cpt222.model;
import au.edu.rmit.cpt222.model.interfaces.Coin;

import java.io.Serializable;
import java.util.Random;

@SuppressWarnings("serial")
public class CoinImpl implements Coin, Serializable {

	private Face currentFace;
	private Face [] faces = Coin.Face.values();
	
	public CoinImpl(){
		int random = new Random().nextInt(this.faces.length);
		this.currentFace = faces[random];
	}
	
	@Override
	public Face getCurrentFace() {
		return currentFace;
	}

	@Override
	public void setCurrentFace(Face currentFace) {
		this.currentFace = currentFace;
	}

	@Override
	public void swapFace() {
		// TODO Swap from head to tails visa versa
		if (currentFace == Face.heads)
			currentFace = Face.tails;
		else
			currentFace = Face.heads;
	}
	
	@Override
	public String toString(){
		return "Current Face: " + this.currentFace;
	}

	public Face[] getFaces(){
		return faces;
	}
}
