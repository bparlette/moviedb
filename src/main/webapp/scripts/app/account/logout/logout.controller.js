'use strict';

angular.module('moviedbApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
