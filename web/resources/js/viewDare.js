var instructionsRow;
var locationRow;
var submissionRow;

$(document).ready(function() {
	init();
	$("#instructions-label").click(function() {
		instructionsRow.slideToggle();
	});
	$("#location-label").click(function() {
		locationRow.slideToggle();
	});
	$("#submission-label").click(function() {
		submissionRow.slideToggle();
	});
});

function init() {
	instructionsRow = $("#instructions-row");
	locationRow = $("#location-row");
	submissionRow = $("#submission-row");
	instructionsRow.slideUp(0);
	locationRow.slideUp(0);
	submissionRow.slideUp(0);

}