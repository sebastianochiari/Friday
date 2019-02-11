
	pageSize = 9;

	var pageCount =  $(".pr0d0tt0").length / pageSize;

	pageCount = Math.ceil(pageCount);

	showPage = function(page) {
		$(".pr0d0tt0").hide();
		$(".pr0d0tt0").each(function(n) {
			if (n >= pageSize * (page - 1) && n < pageSize * page) {
				$(this).show();
			}
	    });        
	}
    
	showPage(1);

	$("#next").click(function() {
		var next = parseInt($("#next .sr-only").text());
		var previous = parseInt($("#previous .sr-only").text());

		if(next != (pageCount + 1)) {
			showPage(next);
			$("#next .sr-only").text(next + 1);
			$("#previous .sr-only").text(previous + 1);
		}
	});

	$("#previous").click(function() {
		var next = parseInt($("#next .sr-only").text());
		var previous = parseInt($("#previous .sr-only").text());

		if(previous != 0) {
			showPage(previous);
			$("#next .sr-only").text(next - 1);
			$("#previous .sr-only").text(previous - 1);
		}
	});	