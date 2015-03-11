var myApp = angular.module('map1', [ 'Authentication','starter.services' ]);


myApp.controller('Map1Ctrl', [
		'AuthenticationService',
		'GetConversationService',
		'LocationService',
		'Map',
		'$q',
		'$scope',
		'$rootScope',
		function(AuthenticationService, GetConversationService,
				LocationService, Map,$q, $scope,$rootScope) {

			

	       $scope.getLocation = function() {
	       

			  var deferred;
	          deferred = $q.defer();
		          
	    	   LocationService.getCoordinates().then(
	    			function(coords) {
	    				deferred.resolve(coords);
	    			},
	    			function(error){
	    				deferred.reject(error);
	    			}
	    		)
	    	   
	    		return deferred.promise;
	    	       
	        };

		    
			$scope.getConversation = function(conversationProximity) {

				AuthenticationService.SetCredentials("khoa0304", "welcome1");

				GetConversationService.GetConverseation(conversationProximity)
						.then(function(conversations) {
							$scope.conversations = conversations;
							$scope.$broadcast('scroll.refreshComplete');
						});
			};
			
			$scope.initMap = function() {

				Map.initializeMap();
			}
			
			$scope.connectRealTime = function() {
	            var socket = new SockJS('/LocationCast/rest/conversation/conversationAdd');
				stompClient = Stomp.over(socket);
	            stompClient.connect({}, function(frame) {
	                stompClient.subscribe('/topic/showConversation', function(calResult){
	                	Map.publishSingleLocation(JSON.parse(calResult.body));
	                });
	            });
	        }
			$scope.loadConversation = function(){
				
		          
				$scope.getLocation().then(function(coords) {
					
					$rootScope.longitude = coords.longitude;
    				$rootScope.latitdue = coords.latitude;
    				Map.loadAllConversation(coords.longitude, coords.latitude);
    				
				},
				function(error) {
					alert(error);
				});
			}
		
}]);