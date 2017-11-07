$(function(){
	var t = $("a[data-action-label='click_video']");
	console.log(t[1]);
    $("a[data-action-label='click_video']").click(function(){
		var d = $(this).parent().attr('data-item-id');	
		alert(d);
	});
	
})

(function(e){
	function toVideoDail(itemId){
		window.location.href = "toVideoPage" + itemId;
	}
}());