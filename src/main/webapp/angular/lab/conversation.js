'use strict';


var conversationApp = angular.module('conversationApp', ['Authentication','PictureService']);

conversationApp
		.controller(
				"ConversationCtrl",
				[		'Base64',
				 		'PictureCaptureUploadService',
						'$scope',
						'$http',
						'$rootScope',
						
						function(Base64, PictureCaptureUploadService, $scope, $http,$rootScope) {
							
							$rootScope.longitude = null;
							$rootScope.latitude = null;
							
							$rootScope.init = function() {
								
								
								if (navigator.geolocation) {
									navigator.geolocation
											.watchPosition($scope.getLocation);
								} else {
									x.innerHTML = "Geolocation is not supported by this browser.";
								}

							};
							
							
							$scope.myForm = {};
							
							$scope.submitTheForm = function() {
								console.log("--> Submitting conversation start");
							
								
								var conversation = {};

								conversation.title = $scope.myForm.title;
								conversation.content = {};
								conversation.content.contentString = $scope.myForm.content;
								conversation.longAndLat = [$rootScope.longitude,$rootScope.latitude ];
								
								
								var authdata = Base64.encode("khoa0304" + ':'
										+ "welcome1");

								$http.defaults.headers.common['authorization'] = 'Basic '
										+ authdata;

								var responsePromise = $http
										.post(
												"http://localhost:8080/LocationCast/rest/conversation/create",
												conversation, {});

								responsePromise
										.success(function(dataFromServer,
												status, headers, config) {
											console
													.log("--> Submitting form passed. Status "
															+ status);

											if (status == 201) {
												$scope.status = " Status code return "
														+ status;
											}

											$scope.myForm = {};
											$scope.showUserPosition();
										});
								responsePromise.error(function(data, status,
										headers, config) {
									console.log("--> Submitting form failed.");
								});
							};

							$scope.myEmptyForm = {
								title : "",
								content : ""
							};

							$scope.reset = function() {

								$scope.myForm = angular
										.copy($scope.myEmptyForm);
								
								$scope.myForm.$setPristine();

							};

							$scope.getLocation = function(position) {
								$scope.message = "Latitude: "
										+ position.coords.latitude
										+ "<br>Longitude: "
										+ position.coords.longitude;

								
								$rootScope.longitude = position.coords.longitude;
								
								$rootScope.latitude = position.coords.latitude;
							};

						

							

							$scope.showUserPosition = function() {
								
								var latlon = $rootScope.latitude + ","
										+$rootScope.longitude;
								
								console.log("Show user position. "+latlon);
								
								var img_url = "http://maps.googleapis.com/maps/api/staticmap?center="
										+ latlon
										+ "&zoom=16&size=400x400&markers=size:%7Ccolor:red&maptype=roadmap";
								document.getElementById("mapholder").innerHTML = "<img src='"
										+ img_url + "'>";
							};

							$scope.showbrowserError = function(error) {
								
								var x = document.getElementById("messages");
								
								
								switch (error.code) {
								case error.PERMISSION_DENIED:
									x.innerHTML = "User denied the request for Geolocation."
									break;
								case error.POSITION_UNAVAILABLE:
									x.innerHTML = "Location information is unavailable."
									break;
								case error.TIMEOUT:
									x.innerHTML = "The request to get user location timed out."
									break;
								case error.UNKNOWN_ERROR:
									x.innerHTML = "An unknown error occurred."
									break;
								}
							};
							
							$scope.takePicture = function(){
								var take = PictureCaptureUploadService.takePicture();
							};
							

						} ]);