package recipediaClasses;

import java.util.ArrayList;

public class JsonRecipe {
	private ArrayList<JsonIngredient> ingredients;
	private ArrayList<JsonInstruction> instructions;
	private ArrayList<JsonTag> tags;
	private String imageURL;
	private String name;
	

	public ArrayList<JsonIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<JsonIngredient> ingredients) {
		this.ingredients = ingredients;
	}

	public ArrayList<JsonInstruction> getInstructions() {
		return instructions;
	}

	public void setInstructions(ArrayList<JsonInstruction> instructions) {
		this.instructions = instructions;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<JsonTag> getTags() {
		return tags;
	}

	public void setTags(ArrayList<JsonTag> tags) {
		this.tags = tags;
	}
	
	public void addTag(JsonTag tag) {
		this.tags.add(tag);
	}
	
}
