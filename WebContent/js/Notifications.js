/**
 * 
 */
function showNotification(user, action, recipe) {
		//make a better notification parser
		var nText = "";
		if (action === "LikeUploaded") {
			nText = user + " " + "liked your recipe '" + recipe + "'!";
		} else if (action === "LikeSaved") {
			nText = user + " " + "liked your saved recipe '" + recipe + "'!"; 
		} else if (action ==="Follow") {
			nText = user + " " + "just became of fan of you!";
		} else if (action === "Saved") {
			nText = user + " saved your recipe '" + recipe + "'!";
		}
		var newNotification = new Noty({
			text: nText
		});
		newNotification.show();
}
function showNotificationSelf(action, recipe) {
	var nText = "You " + action + " '" + recipe + "'!";
	var newNotification = new Noty({
		text: nText
	})
	newNotification.show();
}
$(document).ready(function() {
	Noty.overrideDefaults({
	    layout   : 'topRight',
	    theme    : 'bootstrap-v3',
	    timeout: 5000,
	    type: "custom",
	    closeWith: ['click', 'button']
	});
//	var n = new Noty({
//		text: "Daniel liked your recipe 'Chocolate Cake'!",
//		
//		});
//	var n1 = new Noty({
//		text: "Jen is now a fan of you!",
//		
//		});
//	showNotification("Daniel", "Saved", "Mac and cheese");
	
//	n.show();
//	n1.show();
});