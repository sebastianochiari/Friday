$(document).ready(function(){
    $('.cart-carousel').slick({
        infinite: false,
        arrows: false,
        slidesToShow: 3,
        slidesToScroll: 1,
        variableWidth: true,
    });
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

function change(){
    var select = document.getElementById("ordinamento");
    select.submit();
    // $("#order option").each(function() {
    //     var x = $(this);
    //     if(x.attr('selected')) {
    //         x.removeAttr('selected');
    //     } else {
    //         x.attr('selected', 'selected');
    //     }
    // });
}
