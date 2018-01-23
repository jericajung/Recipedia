var ingredientCount = 0;
var instructionCount = 0;
var tagCount = 0;
var recipe = new Recipe();


function parseInstruction(instruction) {
	var newInstructionText = "";
	for (var i = 0; i < instruction.length; i++) {
		
		if (instruction[i] == "\n") {
			newInstructionText += "<br>";
			
		} else {
			newInstructionText += instruction[i];
		}
	}
	return newInstructionText;
}
$(document).ready(function() {
	document.getElementById("submit-ingredient").addEventListener("click", function(event){
		event.preventDefault();
		
		var ingredient = document.getElementById("ingredient-input").value;
		var quantity = document.getElementById("quantity-input").value;
		var units = document.getElementById("unit-input").value;
		$("#ingredient-quantity-form").removeClass("has-feedback");
		$("#ingredient-quantity-form").removeClass("has-error");
		$("#ingredient-name-form").removeClass("has-feedback");
		$("#ingredient-name-form").removeClass("has-error");
		var hasError = false;
		if (isNaN(quantity) || (quantity == "" || quantity == null)) {
			hasError = true;
			var ingredientQuantityForm = document.getElementById("ingredient-quantity-form");
			ingredientQuantityForm.className += " has-feedback has-error";
		} 
		if (ingredient == "" || ingredient == null) {
			hasError = true;
			var ingredientNameForm = document.getElementById("ingredient-name-form");
			ingredientNameForm.className += " has-feedback has-error";
			
		} 
		
		if(!hasError) {
			ingredientCount++;
			document.getElementById("quantity-input").value = "";
			document.getElementById("ingredient-input").value = "";
			document.getElementById("unit-input").selectedIndex = 0;
			var newIngredientDiv = document.createElement("div");
			newIngredientDiv.className = "row ingredient-div w3-animate-opacity w3-animate-top ";
			var newIngredientName = document.createElement("div");
			newIngredientName.className = "col-sm-2 ingredient-name";
			newIngredientName.innerHTML = ingredient;
			newIngredientDiv.appendChild(newIngredientName);
			var newIngredientQuantity = document.createElement("div");
			newIngredientQuantity.className = "col-sm-1 ingredient-quantity";
			newIngredientQuantity.innerHTML = quantity;
			newIngredientDiv.appendChild(newIngredientQuantity);
			newIngredientDiv.setAttribute("id", "ingredient-div-" + ingredientCount);
			var ingredientDivId = "ingredient-div-" + ingredientCount;
			var ingredientDivIdNum = ingredientCount;
			var newIngredientUnits = document.createElement("div");
			newIngredientUnits.className = "col-sm-8 ingredient-units";
			newIngredientUnits.innerHTML = units;
			var closeButton = document.createElement("img");
			closeButton.setAttribute("src", "../images/CloseButton.png");
			closeButton.className = "close-button";
			closeButton.setAttribute("ingredient-id", ingredientCount);
			closeButton.setAttribute("id", "close-button-ingredient-" + ingredientCount);
			closeButton.addEventListener("click", function(event) {
				recipe.removeIngredientWithId(ingredientDivIdNum);
				document.getElementById(ingredientDivId).className = "row ingredient-deleting-div";
				$("#" + ingredientDivId).animate({opacity: 0}, 150, "linear", function() {
					$("#" + ingredientDivId).animate({height: 0, padding: 0}, 150, "linear", function() {
				        $(this).remove();
				    });
			    });
				
				
			});
			
			
			
			newIngredientDiv.appendChild(newIngredientUnits);
			newIngredientDiv.appendChild(closeButton);
			newIngredientDiv.setAttribute("ingredient-id", ingredientCount);
			
			var ingredientBox = document.getElementById("ingredient-box");
			ingredientBox.appendChild(newIngredientDiv);
			var newIngredientObject = new Ingredient(ingredient, quantity, units, ingredientCount);
			recipe.addIngredient(newIngredientObject);
		}
		
		
	}, true);
	document.getElementById("submit-instruction").addEventListener("click", function(event) {
		event.preventDefault();
		$("#instruction-form").removeClass("has-feedback");
		$("#instruction-form").removeClass("has-error");
		var instruction = document.getElementById("instruction-input").value;
		var hasError = false;
		if (instruction == "" || instruction == null) {
			hasError = true;
			var instructionForm = document.getElementById("instruction-form");
			instructionForm.className += " has-feedback has-error";
		}
		if (!hasError) {
			instructionCount++;
			document.getElementById("instruction-input").value = "";
			var newInstructionDiv = document.createElement("div");
			var instructionDivId = "instruction-div-" + instructionCount;
			var instructionDivIdNum = instructionCount;
			newInstructionDiv.className = "row instruction-div w3-animate-top";
			var newInstructionNumber = document.createElement("div");
			newInstructionNumber.innerHTML = "Step " + instructionCount + ": ";
			newInstructionNumber.className = "col instruction-number";
			newInstructionNumber.setAttribute("id", "instruction-number-" + instructionCount);
			var instructionsBox = document.getElementById("instructions-box");
			newInstructionDiv.appendChild(newInstructionNumber);
			newInstructionDiv.setAttribute("id", "instruction-div-" + instructionCount);
			var newInstruction = document.createElement("div");
			instruction = parseInstruction(instruction);
			
			newInstruction.innerHTML = instruction;
			
			var closeButton = document.createElement("img");
			closeButton.setAttribute("src", "../images/CloseButton.png");
			closeButton.className = "close-button instruction-close-button";
			closeButton.setAttribute("instruction-id", instructionCount);
			closeButton.setAttribute("id", "close-button-instruction-" + instructionCount);
			
			
			closeButton.addEventListener("click", function(event) {
				var buttonClicked = event.target || event.srcElement;
				var buttonId = buttonClicked.id;
				var buttonIdNum = parseInt(buttonId.substring(25, buttonId.length));
				recipe.removeInstructionWithId(buttonIdNum);
				
				document.getElementById("instruction-div-" + buttonIdNum).className = "row instruction-deleting-div";
				$("#" + "instruction-div-" + buttonIdNum).animate({opacity: 0}, 150, "linear", function() {
					$("#" + "instruction-div-" + buttonIdNum).animate({height: 0, padding: 0}, 150, "linear", function() {
				        $(this).remove();
				        
				    });
			    });
				
				for (var i = buttonIdNum + 1; i < instructionCount + 1; i++) {
					document.getElementById("instruction-div-" + i).setAttribute("id", "instruction-div-" + (i-1));
					var currentInstructionNumber = document.getElementById("instruction-number-" + i);
					currentInstructionNumber.setAttribute("id", "instruction-number-" + (i-1));
					currentInstructionNumber.innerHTML = "Step " + (i-1) + ": ";
					var currentCloseButton = document.getElementById("close-button-instruction-" + i);
					currentCloseButton.setAttribute("id", "close-button-instruction-" + (i-1));
					//remember to update recipe object
				}
				instructionCount--;
				
			});
			
			
			newInstruction.className = "col";
			newInstructionDiv.appendChild(closeButton);
			newInstructionDiv.appendChild(newInstruction);
			newInstructionDiv.setAttribute("instruction-id", instructionCount);
			instructionsBox.appendChild(newInstructionDiv);
			var newInstructionObject = new Instruction(instruction, instructionCount);
			recipe.addInstruction(newInstructionObject);
		}
		
	}, true);
	
	document.getElementById("submit-tag").addEventListener("click", function(event) {
		event.preventDefault();
		$("#tag-form").removeClass("has-feedback");
		$("#tag-form").removeClass("has-error");
		var tag = document.getElementById("tag-input").value;
		var hasError = false;
		if (tag == "" || tag == null) {
			hasError = true;
			var tagForm = document.getElementById("tag-form");
			tagForm.className += " has-feedback has-error";
		}
		if (!hasError) {
			tagCount++;
			document.getElementById("tag-input").value = "";
			var newTagDiv = document.createElement("div");
			var tagDivId = "tag-div-" + tagCount;
			var tagDivIdNum = tagCount;
			newTagDiv.className = "row tag-div w3-animate-top";
			var newTagNumber = document.createElement("div");
			newTagNumber.innerHTML = "Tag " + tagCount + ": ";
			newTagNumber.className = "col instruction-number";
			newTagNumber.setAttribute("id", "tag-number-" + tagCount);
			var tagBox = document.getElementById("tag-box");
			newTagDiv.appendChild(newTagNumber);
			newTagDiv.setAttribute("id", "tag-div-" + tagCount);
			var newTag = document.createElement("div");
			
			newTag.innerHTML = tag;
			
			var closeButton = document.createElement("img");
			closeButton.setAttribute("src", "../images/CloseButton.png");
			closeButton.className = "close-button instruction-close-button";
			closeButton.setAttribute("tag-id", tagCount);
			closeButton.setAttribute("id", "close-button-tag-" + tagCount);
			
			
			closeButton.addEventListener("click", function(event) {
				var buttonClicked = event.target || event.srcElement;
				var buttonId = buttonClicked.id;
				var buttonIdNum = parseInt(buttonId.substring(17, buttonId.length));
				recipe.removeTagWithId(buttonIdNum);
				
				document.getElementById("tag-div-" + buttonIdNum).className = "row tag-deleting-div";
				$("#" + "tag-div-" + buttonIdNum).animate({opacity: 0}, 150, "linear", function() {
					$("#" + "tag-div-" + buttonIdNum).animate({height: 0, padding: 0}, 150, "linear", function() {
				        $(this).remove();
				        
				    });
			    });
				for (var i = buttonIdNum + 1; i < tagCount + 1; i++) {
					document.getElementById("tag-div-" + i).setAttribute("id", "tag-div-" + (i-1));
					var currentTagNumber = document.getElementById("tag-number-" + i);
					currentTagNumber.setAttribute("id", "tag-number-" + (i-1));
					currentTagNumber.innerHTML = "Tag " + (i-1) + ": ";
					var currentCloseButton = document.getElementById("close-button-tag-" + i);
					currentCloseButton.setAttribute("id", "close-button-tag-" + (i-1));
					//remember to update recipe object
				}
				tagCount--;
				
				
			});
			newTag.className = "col";
			newTagDiv.appendChild(closeButton);
			newTagDiv.appendChild(newTag);
			newTagDiv.setAttribute("tag-id", tagCount);
			tagBox.appendChild(newTagDiv);
			var newTagObject = new Tag(tag, tagCount);
			recipe.addTag(newTagObject);
		}
	});
	document.getElementById("submit-image-url").addEventListener("click", function(event) {
		$("#image-url-form").removeClass("has-feedback");
		$("#image-url-form").removeClass("has-error");
		var hasError = false;
		event.preventDefault();
		var imageURL = document.getElementById("image-url-input").value;
		if (imageURL == "" || imageURL == null) {
			hasError = true;
			var imageURLForm = document.getElementById("image-url-form");
			imageURLForm.className += " has-feedback has-error";
		}
		if(!hasError) {
			$('#recipe-image')
	         .attr('src', imageURL)
	         .width(0)
	         .height(0)
	         .animate({height: 200, width: 300}, 200, "linear", function() {
	        	 $(this).css('display', 'inline');
	         });
			 recipe.imageURL = imageURL;
		}
			 
	});
	document.getElementById("final-submit-button").addEventListener("click", function(event) {
		$("#image-url-form").removeClass("has-feedback");
		$("#image-url-form").removeClass("has-error");
		$("#instruction-form").removeClass("has-feedback");
		$("#instruction-form").removeClass("has-error");
		$("#ingredient-quantity-form").removeClass("has-feedback");
		$("#ingredient-quantity-form").removeClass("has-error");
		$("#ingredient-name-form").removeClass("has-feedback");
		$("#ingredient-name-form").removeClass("has-error");
		$("#name-form").removeClass("has-feedback");
		$("#name-form").removeClass("has-error");
		$("#tag-form").removeClass("has-feedback");
		$("#tag-form").removeClass("has-error");
		var hasError = false;
		var nameInput = document.getElementById("recipe-name-input");
		var name = nameInput.value;
		var imageURLInput = document.getElementById("image-url-input");
		var imageURL = imageURLInput.value;
		if ((imageURL == "" || imageURL == null) && recipe.imageURL == "") {
			hasError = true;
			var imageURLForm = document.getElementById("image-url-form");
			imageURLForm.className += " has-feedback has-error";
		}
		if (name == "" || name == null) {
			hasError = true;
			var nameForm = document.getElementById("name-form");
			nameForm.className += " has-feedback has-error";
		}
		if (recipe.instructions.length == 0) {
			hasError = true;
			var instructionForm = document.getElementById("instruction-form");
			instructionForm.className += " has-feedback has-error";
		}
		if (recipe.ingredients.length == 0) {
			hasError = true;
			var ingredientQuantityForm = document.getElementById("ingredient-quantity-form");
			ingredientQuantityForm.className += " has-feedback has-error";
			var ingredientNameForm = document.getElementById("ingredient-name-form");
			ingredientNameForm.className += " has-feedback has-error";
		}
		if (recipe.tags.length == 0) {
			hasError = true;
			var tagForm = document.getElementById("tag-form");
			tagForm.className += " has-feedback has-error";
		}
		if(!hasError) {
			recipe.imageURL = imageURL;
			recipe.name = name;
			$.ajax({
	            type: 'post',
	            url: '../CreateRecipeServlet',
	            data: JSON.stringify(recipe),
	            contentType: "application/json; charset=utf-8",
	            async: false,
	        	xhrFields: {
	        		withCredentials: true
	        	}
	        }).done(function(data) {
	        	if (data.status == "Success") {
	        		var redirectLocation = data.data;
	        		console.log(redirectLocation);
	                window.location.href = redirectLocation;
	            }
	            else {
	            	console.log("Failed");
	            }
			})
			
		}
			
	})
	
});
	
