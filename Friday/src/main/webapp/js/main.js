$(document).ready(function(){
});

function goBack() {
    window.history.back();
}

function revealPsw() {
    var elements = document.getElementsByClassName("johnCena");
    for(var i = 0; i < elements.length; i++) {
        if (elements[i].type === "password") {
            elements[i].type = "text";
        } else {
            elements[i].type = "password";
        }
    }
}

function submitForm(id) {
    var select = document.getElementById(id);
    select.submit();
}