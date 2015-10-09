'use strict';

angular.module('moviedbApp').controller('MovieDialogController',
    ['$http','$scope', '$stateParams', '$modalInstance', 'entity', 'Movie',
        function($http, $scope, $stateParams, $modalInstance, entity, Movie) {

        $scope.movie = entity;
        $scope.load = function(id) {
            Movie.get({id : id}, function(result) {
                $scope.movie = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('moviedbApp:movieUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
        	if ($scope.movie.id != null) {
                Movie.update($scope.movie, onSaveFinished);
            } else {
                Movie.save($scope.movie, onSaveFinished);
            }
        };

        $scope.clear = function() {
        	$modalInstance.dismiss('cancel');
        };
        
        $scope.onTitleChange = function(title) {
        	$http.jsonp(
          	      'http://www.omdbapi.com/?r=json&t='+title+'&callback=JSON_CALLBACK'
	          ).success(function(omdbmovie){
	          	
	        	if (omdbmovie.Response == "True") {
		          	var key, keys = Object.keys(omdbmovie);
		          	var n = keys.length;
		          	var caseCorrectOmdbmovie={}
		          	while (n--) {
		          	  key = keys[n];
		          	  if (key.lastIndexOf("imdb", 0) === 0) {
		          		  //the service returns imdb properties correctly so we don't need to map it
		          		  caseCorrectOmdbmovie[key] = omdbmovie[key];
		          	  } else {
		          		  caseCorrectOmdbmovie[key.toLowerCase()] = omdbmovie[key];
		          	  }
		          	}
		          	
		          	
		          	$scope.movie = caseCorrectOmdbmovie;
	        	}
	          }).error(function(){
	          	// handle errors
	          });
        };
         
        
}]);
