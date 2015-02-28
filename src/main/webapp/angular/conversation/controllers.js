'use strict';
var appControllers = angular.module('starter.controllers',
		[]);

appControllers.controller('ConversationsCtrl',
		function($scope, LocationService) {
	        
			$scope.getLocation = function() {

				var latLong = LocationService.getLatLong().then(
			            function(latLong) {
			                $scope.latLong = latLong;
			                console.log('LatLong=');
			                console.log($scope.latLong);
			              },
			            
			            function(error) {
			                alert(error);
			            }
			        )
			};

		});

appControllers.controller('DashCtrl', function($scope) {
});