angular.module('myApp', []).
directive('myMap', function () {
    // directive link function
    var link = function (scope, element, attrs) {
        var map, infoWindow;
        var markers = [];

        // map config
        var mapOptions = {
            center: new google.maps.LatLng(37.3190238,-121.83922310000001),
            zoom: 4,
            //http://www.w3schools.com/googleapi/google_maps_types.asp
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            scrollwheel: false
        };

        // init the map
        function initMap() {
            if (map === void 0) {
                map = new google.maps.Map(element[0], mapOptions);
            }
        }

        // place a marker
        function setMarker(map, position, title, content) {
            var marker;
            var markerOptions = {
                position: position,
                map: map,
                title: title,
                icon: 'https://maps.google.com/mapfiles/ms/icons/green-dot.png'
            };

            marker = new google.maps.Marker(markerOptions);
            markers.push(marker); // add marker to array

            google.maps.event.addListener(marker, 'click', function () {
                // close window if not undefined
                if (infoWindow !== void 0) {
                    infoWindow.close();
                }
                // create new window
                var infoWindowOptions = {
                    content: content
                };
                infoWindow = new google.maps.InfoWindow(infoWindowOptions);
                infoWindow.open(map, marker);
            });
        }

        // show the map and place some markers
        initMap();

        setMarker(map, new google.maps.LatLng(37.3190238,-121.83922310000001), 'San Jose', 'Just some content');

    };

    return {
        restrict: 'A',
        template: '<div id="gmaps"></div>',
        replace: true,
        link: link
    };
});