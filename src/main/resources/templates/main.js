
	alert("funciona js external");
$(document).ready(function(){
	alert("funciona jquery external");
	$("#formulario").css("visibility", hidden);
  	$(".insertar").click(function(){
    $("formulario").css("visibility", "visible");
  });
});

