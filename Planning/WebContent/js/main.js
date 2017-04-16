'use strict';

angular.module('myApp', [
  'ngRoute',
  'ui.router',
  'myApp.earnedValue',
  'myApp.riskManagement',
  'myApp.projects'
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

  $scope.imgs = ['images/office1.jpg',
            'images/office2.jpg',
            'images/office3.jpg',
            'images/office4.jpg',
            'images/office5.jpg'
            ];
  var i = 1;
  $scope.$apply(function(){
	  document.getElementById("bgimg").style.backgroundImage = "url("+$scope.imgs[i]+")";
	  setTimeout(changeImage, 10000);
	
	 function changeImage(){
		i++;
		if(i === 4){
			i = 0;
		}
		$("#bgimg").fadeOut(200, function(){
			document.getElementById("bgimg").style.backgroundImage = "url("+$scope.imgs[i]+")";
			$("#bgimg").fadeIn(200);
		});
		setTimeout(changeImage, 10000);
	 }
  });
 
});










 