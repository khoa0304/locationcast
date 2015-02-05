'use strict';

// angular.module('Authentication', []);

var conversationApp = angular.module('conversationApp', []);

conversationApp
		.controller(
				"ConversationCtrl",
				[		'Base64',
						'$scope',
						'$http',
						'$rootScope',
						
						function(Base64, $scope, $http,$rootScope) {
							
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

						} ]);

conversationApp
		.factory(
				'Base64',
				function() {
					/* jshint ignore:start */

					var keyStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';

					return {
						encode : function(input) {
							var output = "";
							var chr1, chr2, chr3 = "";
							var enc1, enc2, enc3, enc4 = "";
							var i = 0;

							do {
								chr1 = input.charCodeAt(i++);
								chr2 = input.charCodeAt(i++);
								chr3 = input.charCodeAt(i++);

								enc1 = chr1 >> 2;
								enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
								enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
								enc4 = chr3 & 63;

								if (isNaN(chr2)) {
									enc3 = enc4 = 64;
								} else if (isNaN(chr3)) {
									enc4 = 64;
								}

								output = output + keyStr.charAt(enc1)
										+ keyStr.charAt(enc2)
										+ keyStr.charAt(enc3)
										+ keyStr.charAt(enc4);
								chr1 = chr2 = chr3 = "";
								enc1 = enc2 = enc3 = enc4 = "";
							} while (i < input.length);

							return output;
						},

						decode : function(input) {
							var output = "";
							var chr1, chr2, chr3 = "";
							var enc1, enc2, enc3, enc4 = "";
							var i = 0;

							// remove all characters that are not A-Z, a-z, 0-9,
							// +, /, or =
							var base64test = /[^A-Za-z0-9\+\/\=]/g;
							if (base64test.exec(input)) {
								window
										.alert("There were invalid base64 characters in the input text.\n"
												+ "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n"
												+ "Expect errors in decoding.");
							}
							input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

							do {
								enc1 = keyStr.indexOf(input.charAt(i++));
								enc2 = keyStr.indexOf(input.charAt(i++));
								enc3 = keyStr.indexOf(input.charAt(i++));
								enc4 = keyStr.indexOf(input.charAt(i++));

								chr1 = (enc1 << 2) | (enc2 >> 4);
								chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
								chr3 = ((enc3 & 3) << 6) | enc4;

								output = output + String.fromCharCode(chr1);

								if (enc3 != 64) {
									output = output + String.fromCharCode(chr2);
								}
								if (enc4 != 64) {
									output = output + String.fromCharCode(chr3);
								}

								chr1 = chr2 = chr3 = "";
								enc1 = enc2 = enc3 = enc4 = "";

							} while (i < input.length);

							return output;
						}
					};

					/* jshint ignore:end */
				});
