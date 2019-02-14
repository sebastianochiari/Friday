var platform = new H.service.Platform({
    'app_id': 'teSpEFeKPAje4MJeqpJZ',
    'app_code': 'oUjGgZQJpfqiEFR-Ji5FGA'
    });

var PCID = String(document.getElementById("PICIAIDI").value);
CosaCercare(PCID)
console.log(PCID)
var lat;
var lng;
var posizione;

function getLocation(){

    if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition( function(position) {
                lat = position.coords.latitude;
                lng = position.coords.longitude;
                console.log("Latitudine: "+ lat);
                console.log("Longitudine: "+ lng);
                posizione = lat+','+lng;
                console.log(lat+','+lng);

                searchSomething();
        });
    }
}

async function searchSomething(){

    params = {
            'q': PCID, //categoria
            'at': posizione //dove
            }

          // Obtain an Explore object through which to submit search requests:
          var search = new H.places.Search(platform.getPlacesService());

          // Define result and error holder
          var placeDetails, error;

          // Run a search request with parameters, headers (empty), and callback
          // functions:
          search.request(params, {}, onResult, onError);

          // Success handler - fetch the first set of detailed place data from
          // the response:
          function onResult(data) {
            console.log(data)
            for(var i=0; i<data.results.items.length && i<3; i++){
                data.results.items[i].follow(onFetchPlaceDetails, onError);
            }
           }

          // Define a callback to process a successful response to the
          // request for place details:
          function onFetchPlaceDetails(data) {
              console.log(data)
            placeDetails = data;
            addElement(data);
           }

          // Define a callback to handle errors:
          function onError(data) {
            error = data;
            console.log(error);
          }

}

async function searchCategory(){


    params = {
            'cat': 'eat-drink', //categoria
            'at': '46.0664228,11.1257601' //dove
        }

    // Obtain an Explore object through which to submit search requests:
    var explore = new H.places.Explore(platform.getPlacesService());

    // Define result and error holder
    var placeDetails, error;

    // Run a search request with parameters, headers (empty), and callback
    // functions:
    explore.request(params, {}, onResult, onError);

    // Success handler - fetch the first set of detailed place data from
    // the response:
    function onResult(data) {
            for(var i=0; i<data.results.items.length; i++){
                data.results.items[i].follow(onFetchPlaceDetails, onError);
            }
           }

          // Define a callback to process a successful response to the
          // request for place details:
          function onFetchPlaceDetails(data) {
            placeDetails = data;

            //console.log(placeDetails)
           }

          // Define a callback to handle errors:
          function onError(data) {
            error = data;
            console.log(error);
          }

}


function addElement(data){
    var div = document.createElement("div");
    div.classList.add("row");
    div.classList.add("mt-3");
    div.innerHTML ="<div class=\"col-2 align-self-center\"><img class=\"displayCenter\" src=\""+data.icon+"\"></div><div class=\"col\"><h5>"+data.name+"</h5><p class=\"mt-2 list-product-description\">"+data.location.address.text+"</p></div><div class=\"col align-self-center\"><a class=\"text-link\" href=\"https://www.google.com/maps/place/"+data.location.position[0]+","+data.location.position[1]+"\" target=\"_blank\">Vai alla mappa</a></div>";
    // div.innerHTML = "<div class=\"col-2\"><img class=\"displayCenter\" src=\""+data.icon+"\"></div> <div class=\"col\"><h5>"+data.name+"</h5><p class=\"mt-2 list-product-description\">"+data.location.address.text+"</p><a class=\"\" href=\"https://www.google.com/maps/place/"+data.location.position[0]+","+data.location.position[1]+"\" target=\"_blank\">Link</a></div>";
    document.getElementById("modalmap").appendChild(div);
}

function CosaCercare(input){
    switch(input){
        case 'Fai da te' :
            PCID = 'Fai da te'
            break;
        case 'Spesa' :
            PCID = 'supermercato'
            break;
        case 'Party' :
            PCID = 'centro commerciale'
            break;
        case 'Scuola' :
            PCID = 'cartoleria'
            break;
    }   
}