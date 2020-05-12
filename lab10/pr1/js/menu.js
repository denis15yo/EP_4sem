document.getElementById("menu").onmouseover= function(event) {
    var target = event.target;
    target.src="img/green.png"
}
document.getElementById("menu").onmouseout= function(event) {
    var target = event.target;
    target.src="img/red.png"
}
