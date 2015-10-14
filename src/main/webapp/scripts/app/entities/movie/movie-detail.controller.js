'use strict';

angular.module('moviedbApp')
    .controller('MovieDetailController', function ($scope, $rootScope, $stateParams, entity, Movie) {
        $scope.movie = entity;
        
        $scope.ratingClass = Movie.ratingClass;
        
        $scope.load = function (id) {
            Movie.api.get({id: id}, function(result) {
                $scope.movie = result;
            });
        };
        $rootScope.$on('moviedbApp:movieUpdate', function(event, result) {
            $scope.movie = result;
        });
    });
