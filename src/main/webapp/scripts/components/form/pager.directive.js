/* globals $ */
'use strict';

angular.module('moviedbApp')
    .directive('moviedbAppPager', function() {
        return {
            templateUrl: 'scripts/components/form/pager.html'
        };
    });
