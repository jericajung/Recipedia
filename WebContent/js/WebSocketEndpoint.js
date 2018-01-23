/**
 * 
 */
var protocol = 'ws://',
	hostname = window.location.hostname,
	port     = ':8081',
	pathname = '/' + window.location.pathname.split('/')[1];
var socket = new WebSocket(protocol + hostname + port + pathname + '/ws');
socket.onopen = function (event) {
	//socket.send("UserConnected");
}

socket.onmessage = function (event) {
	var msg = JSON.parse(event.data);
	// console.log(msg); // debug by uncommenting this
	var action = msg.action;

	if (action == 'Saved') {
		showNotification(msg.userName ,msg.action, msg.recipe);
	} 
}