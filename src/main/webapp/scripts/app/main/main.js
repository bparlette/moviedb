'use strict';

angular.module('moviedbApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('home', {
                parent: 'entity',
                url: '/',
                data: {
                	 authorities: [],
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
            });
    });
