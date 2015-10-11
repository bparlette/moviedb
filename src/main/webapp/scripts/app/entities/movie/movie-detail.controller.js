'use strict';

angular.module('moviedbApp')
    .controller('MovieDetailController', function ($scope, $rootScope, $stateParams, entity, Movie, MovieService) {
        $scope.movie = entity;
        
        $scope.ratingClass = MovieService.ratingClass;
        
        $scope.load = function (id) {
            Movie.get({id: id}, function(result) {
                $scope.movie = result;
            });
        };
        $rootScope.$on('moviedbApp:movieUpdate', function(event, result) {
            $scope.movie = result;
        });
    });
