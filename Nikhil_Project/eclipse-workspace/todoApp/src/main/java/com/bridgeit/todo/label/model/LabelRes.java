package com.bridgeit.todo.label.model;

public class LabelRes {

	private int labelId;
	private String name;

	public LabelRes(Label object) {

		this.labelId = object.getLabelId();
		this.name = object.getName();
	}

	public int getLabelId() {
		return labelId;
	}

	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
