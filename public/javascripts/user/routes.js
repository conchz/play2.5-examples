/**
 * Configure routes of user module.
 */
define(['angular', './controllers', 'common'], function (angular, controllers) {
    'use strict';

    var mod = angular.module('user.routes', ['user.services', 'yourprefix.common']);
    mod.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/login', {templateUrl: '/assets/javascripts/user/login.html', controller: controllers.LoginCtrl});
        // .when('/user', {templateUrl: '/assets/templates/user/user.html', controller: controllers.UserCtrl})
        // .when('/user/:id', {templateUrl: '/assets/templates/user/editUser.html', controller: controllers.UserCtrl});
    }]);
    return mod;
});
