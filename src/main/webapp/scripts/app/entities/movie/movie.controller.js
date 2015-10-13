'use strict';

angular.module('moviedbApp')
    .controller('MovieController', function ($scope, Movie, ParseLinks, MovieService) {
        $scope.movies = [];
        $scope.page = 0;
        $scope.search;
        
        $scope.ratingClass = MovieService.ratingClass;
        
        $scope.loadAll = function() {
            Movie.query({page: $scope.page, size: 20, search:$scope.search}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                	$scope.movies.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 0;
            $scope.search = "";
            $scope.movies = [];
            $scope.loadAll();
        };
        
        $scope.loadSearch = function() {
        	$scope.page = 0;
        	$scope.movies = [];
            $scope.loadAll();
        };
        
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        
        $scope.loadAll();

        $scope.delete = function (id) {
            Movie.get({id: id}, function(result) {
                $scope.movie = result;
                $('#deleteMovieConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Movie.delete({id: id},
                function () {
                    $scope.reset();
                    $('#deleteMovieConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.movie = {title: null, year: null, rated: null, released: null, runtime: null, genre: null, director: null, actors: null, plot: null, language: null, country: null, awards: null, poster: null, metascore: null, imdbRating: null, imdbVotes: null, imdbID: null, type: null, response: null, comment: null, id: null};
        };
        
        
    });
