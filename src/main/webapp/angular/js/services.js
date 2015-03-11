var starterService = angular.module('starter.services', ['Authentication']);

starterService.factory('LocationService', function($q, $window,
		$rootScope) {

	var latLong = null;


	
	return {
		
		  getCoordinates : function() {
	
			  var deferred;
	          deferred = $q.defer();
	   
	          if ($window.navigator && $window.navigator.geolocation) {
	        	  	$window.navigator.geolocation.getCurrentPosition(function(position) {
	        	  		return deferred.resolve(position.coords);
	        	  	}, function(error) {
	        	  		return deferred.reject("Unable to get your location");
	        	  	});
	          } else {
	            deferred.reject("Your browser cannot access to your position");
	          }
	   
	          return deferred.promise;
		},
		
	}
	
});

starterService.factory('GetConversationService',['AuthenticationService','$http', function(AuthenticationService,$http) {
	var BASE_URL = "/LocationCast/rest/conversation/load/";

	var conversations = [];

	return {
		GetConverseation : function(conversationProximity,longitude,latitude) {
			
			AuthenticationService.SetCredentials("khoa0304", "welcome1");
		
			return $http.get(
					BASE_URL + longitude + "/" + latitude
							+ "/" +conversationProximity+"/").then(function(response) {
				conversations = response.data;
				return conversations;
			});
		}// ,

	}
}]);

starterService.factory('Map',['GetConversationService', function(GetConversationService) {

	
		
		var map, infoWindow;
		var markers = [];

	

		// init the map
		var initMap = function initMap(element,mapOptions) {
			if (map === void 0) {
				map = new google.maps.Map(element, mapOptions);
			}
		}

		// place a marker
		var createMarker = function (map, position, title, content) {
			var marker;
			var markerOptions = {
				position : position,
				map : map,
				title : title,
				icon : 'https://maps.google.com/mapfiles/ms/icons/green-dot.png'
			};

			marker = new google.maps.Marker(markerOptions);
			markers.push(marker); // add marker to array

			google.maps.event.addListener(marker, 'click', function() {
				// close window if not undefined
				if (infoWindow !== void 0) {
					infoWindow.close();
				}
				// create new window
				var infoWindowOptions = {
					content : content
				};
				infoWindow = new google.maps.InfoWindow(infoWindowOptions);
				infoWindow.open(map, marker);
			});
		}
		
		return{
			
			initializeMap : function() {
	
				
			
				var mapOptions = {
					center : new google.maps.LatLng(37.3190238, -121.83922310000001),
					zoom : 18,
					// http://www.w3schools.com/googleapi/google_maps_types.asp
					mapTypeId : google.maps.MapTypeId.ROADMAP,
					scrollwheel : false
				}
				// show the map and place some markers
				initMap(document.getElementById("gmaps"),mapOptions);
	
			},
		
		    loadAllConversation : function(longitude,latitude){
		    	
		    	GetConversationService.GetConverseation("1000.0d",longitude,latitude).then(function(conversationList) {
		    		
		    		for( var i in  conversationList){
		    			var conversation = conversationList[i];
			    		var longLat = conversation.longAndLat;
			    		var content = conversation.content.contentString;
			    		var createdDate = conversation.id;
			    		console.log(longLat[0] +" " +longLat[1] +" " + content + " "+ new Date(createdDate).toString());
			    		createMarker(map, new google.maps.LatLng(longLat[1], longLat[0]),
								new Date(createdDate).toString(), content);
			    	}
		    	}, function(value) {
		    		console.error(value);
		    	});
		    },
			
            publishSingleLocation : function(conversation){
		    	var longLat = conversation.longAndLat;
	    		var content = conversation.content.contentString;
	    		var createdDate = conversation.id;
	    		console.log(longLat[0] +" " +longLat[1] +" " + content + " "+ new Date(createdDate).toString());
	    		createMarker(map, new google.maps.LatLng(longLat[1], longLat[0]),
						new Date(createdDate).toString(), content);
			  
		    }
			
		
		}


}]);