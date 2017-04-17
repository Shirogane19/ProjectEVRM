'use strict';

angular.module('myApp', [
  'ngRoute',
  'ui.router',
  'myApp.earnedValue',
  'myApp.riskManagement',
  'myApp.projects',
  'rzModule',
  'ngMaterial'
])

.config(function($stateProvider, $urlRouterProvider) {

    
    $urlRouterProvider.otherwise('/projects');

    $stateProvider

    .state('projects', {
      url: '/projects',
        templateUrl: 'projects.html',
        controller: 'projectsViewCtrl',

    })

    .state('riskManagementView', {
      url: '/Risk-Management',
        templateUrl: 'riskManagement.html',
        controller: 'riskManagementViewCtrl',

    })

    .state('earnedView', {
      url: '/Earned-Value',
        templateUrl: 'earnedValue.html',
        controller: 'earnedValueViewCtrl',

    });
      
})

.controller('ctrl1', function($scope){
 
});










 