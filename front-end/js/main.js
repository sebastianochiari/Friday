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
