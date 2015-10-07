/* globals $ */
'use strict';

angular.module('moviedbApp')
    .directive('moviedbAppPagination', function() {
        return {
            templateUrl: 'scripts/components/form/pagination.html'
        };
    });
