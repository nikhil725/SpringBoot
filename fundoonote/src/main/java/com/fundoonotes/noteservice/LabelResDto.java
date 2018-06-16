package com.fundoonotes.noteservice;


public class LabelResDto {

	private int labeld;
	private String name;


	public LabelResDto(Label label) {

		this.labeld = label.getLabeld();
		this.name = label.getName();
	}

	public int getLabeld() {
		return labeld;
	}
	public void setLabeld(int labeld) {
		this.labeld = labeld;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
