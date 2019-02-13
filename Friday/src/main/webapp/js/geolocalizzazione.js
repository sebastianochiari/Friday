/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Initialize the platform object:
    var platform = new H.service.Platform({
    'app_id': 'teSpEFeKPAje4MJeqpJZ',
    'app_code': 'oUjGgZQJpfqiEFR-Ji5FGA'
    });

    // Obtain the default map types from the platform object
    var maptypes = platform.createDefaultLayers();
    var map;
    var search = new H.places.Search(platform.getPlacesService()), searchResult, error;
    
    function getMap(){

        // Instantiate (and display) a map object:
        map = new H.Map(
        document.getElementById('mapContainer'),
        maptypes.normal.map,
        {
          zoom: 10,
          center: { lng: 13.4, lat: 52.51 }
        });
    }
    
    function searchsomething(){
        
        

        // Define search parameters:
            var params = {
              // Plain text search for places with the word "hotel"
              // associated with them:
              'q': 'hotel',
              //  Search in the Chinatown district in San Francisco:
              'in': '37.7942,-122.4070;r=500'
            };

            // Define a callback function to handle data on success:
            function onResult(data) {
              searchResult = data;
            }

            // Define a callback function to handle errors:
            function onError(data) {
              error = data;
            }
            
            search.request(params, {}, onResult, onError);
            console.log(searchResult);
            console.log(error);
            console.log(platform)
            console.log(search)
        
        }
    
    getMap();
    searchsomething();


