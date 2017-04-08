'use strict';

angular.module('myApp.riskManagement', [])

.controller('riskManagementViewCtrl', ['$scope','$state',function($scope,$state) {
	
	$scope.risks = [
					{description: "computer goes down", probability: 10, impact: 4},
					{description: "computer get virus", probability: 20, impact: 3},
					{description: "computer run out of RAM", probability: 50, impact: 1},
				   ];
	 
	 $(function(){
		    $('.editText').dblclick( function(){
		    	console.log("1> "+$scope.risks[a].description);
		        var text = $scope.risks[a].description;
		        $(this).html('<input type="text" value="'+text+'">').find('input').focus();
		    }).keypress( function(e){
		        if(e.keyCode == 13){
		        	$scope.risks[a].description = $('input', this).val();
		        	text = $scope.risks[a].description;
		            $(this).html( text );
		            console.log("2> "+$scope.risks[a].description);
		        }
		    });
	 });
	 
	 
	 $scope.test = function(){
		 console.log($scope.risks[1].description);
	 }
 
}]);

function test(){
	RiskManagementDB.printFutbolistas();
}