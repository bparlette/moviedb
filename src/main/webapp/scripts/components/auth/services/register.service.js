'use strict';

angular.module('moviedbApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


