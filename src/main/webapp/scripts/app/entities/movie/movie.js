'use strict';

angular.module('moviedbApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('movie', {
                parent: 'entity',
                url: '/movies',
                data: {
                    authorities: [],
                    pageTitle: 'moviedbApp.movie.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/movie/movies.html',
                        controller: 'MovieController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('movie');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('movie.detail', {
                parent: 'entity',
                url: '/movie/{id}',
                data: {
                    authorities: [],
                    pageTitle: 'moviedbApp.movie.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/movie/movie-detail.html',
                        controller: 'MovieDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('movie');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Movie', function($stateParams, Movie) {
                        return Movie.api.get({id : $stateParams.id});
                    }]
                }
            })
            .state('movie.new', {
                parent: 'movie',
                url: '/new',
                data: {
                    authorities: [],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/movie/movie-dialog.html',
                        controller: 'MovieDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {title: null, year: null, rated: null, released: null, runtime: null, genre: null, director: null, actors: null, plot: null, language: null, country: null, awards: null, poster: null, metascore: null, imdbRating: null, imdbVotes: null, imdbID: null, type: null, response: null, comment: null, id: null};
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('movie', null, { reload: true });
                    }, function() {
                        $state.go('movie');
                    })
                }]
            })
            .state('movie.edit', {
                parent: 'movie',
                url: '/{id}/edit',
                data: {
                    authorities: [],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/movie/movie-dialog.html',
                        controller: 'MovieDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Movie', function(Movie) {
                                return Movie.api.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('movie', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });

angular.module('moviedbApp')
	.directive("movieDetailTemplate", function() {
	  return {
	    restrict: "E",
	    replace: true,
	    templateUrl: "scripts/app/entities/movie/movie-detail-template.html"
	  };
	});