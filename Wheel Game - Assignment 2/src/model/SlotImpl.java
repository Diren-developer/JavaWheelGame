package model;

import model.enumeration.Color;
import model.interfaces.Slot;

public class SlotImpl implements Slot {
	private int position;
	private int number;
	private Color color;
	private Slot slot;

	public SlotImpl(int position, Color color, int number) {
		this.position = position;
		this.number = number;
		this.color = color;
		
	}
	@Override
	public int getPosition() {
		return position;
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public boolean equals(Slot slot) {

		if (this.slot.getColor() == slot.getColor() && this.slot.getNumber() == slot.getNumber()) return true;
		else return false;
	}
	
	@Override
	public String toString() {
		return "Position: "+position+" ,Color: "+color+" ,Number: "+number;
	}

}
