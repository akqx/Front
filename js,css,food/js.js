function onClick(element) {
    document.getElementById("img01").src = element.src;
    document.getElementById("zoomPhoto").style.display = "block";
    var captionText = document.getElementById("caption");
    captionText.innerHTML = element.alt;
}