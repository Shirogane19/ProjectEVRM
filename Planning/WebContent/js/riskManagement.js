'use strict';

angular.module('myApp.riskManagement', ['ngMaterial'])

.controller('riskManagementViewCtrl', ['$scope','$state','$timeout','$sce', function($scope,$state,$timeout,$sce) {
	
	$scope.risks = [];
	$scope.inputRisk;
	$scope.numR; //numero de riesgos
	
	 
	  $scope.slider_toggle = {
	    options: {
	      floor: 1,
	      ceil: 5
	    }
	  };
	  
	  $scope.slider_toggle1 = {
			    options: {
			      floor: 1,
			      ceil: 99
			    }
			  };
	
	///-------------------------------------------------------------
	$scope.insertRM = function(){

		console.log("insertRM :"+$scope.inputRisk);

		var rmObject = { numR: $scope.numR, description: $scope.inputRisk, probability: "99", impact: "3", nameProject: "project 5" };

		RiskManagementDB.insert(rmObject, function(data){
			//console.log(data);
			$scope.getAllRiskManagement();
			$scope.inputRisk = "";
		});

	}///

	///-------------------------------------------------------------
	$scope.getAllRiskManagement = function(){

		console.log("getAllRiskManagement");
		
		var requestObject = { nameProject: "project 5" };

		RiskManagementDB.getCollection(requestObject, function(data){
			
			$scope.$apply(function(){
				$scope.risks = data;
				console.log($scope.risks);
				$scope.numR = $scope.risks.length + 1;
			});
		});

	}///
	///-------------------------------------------------------------
	$scope.updateRM = function(r){
		
		console.log("UpdateRM :"+r.description);
		
		//ger.description = $scope.validateValue(r.description);
		//r.probability = $scope.validateValue(r.probability);
		//r.impact = $scope.validateValue(r.impact);

		var objectToUpdate = { numR: r.numR, description: r.description, probability: r.probability, impact: r.impact, nameProject: r.nameProject };

		RiskManagementDB.save(objectToUpdate, function(data){
			//console.log(data);
			$state.reload();
		});

	}///
	 ///-------------------------------------------------------------
    $scope.calcular = function(prob, imp){
    	return ((prob*0.01)*imp).toFixed(2);
    }
    $scope.generateColor = function(value){
    	if(value > (4.95/3)*2){
    		return "red";
    	}else{
    		if(value >= (4.95/3)){
    			return "orange";
    		}else{
    			return "green";
    		}
    	}
    }
    ///-------------------------------------------------------------
    $scope.validateValue = function(value){
    	if(value.length === 0 || value === "null" || isNaN(parseFloat(value))){
			return 1;
		}else{
			if(value.length === parseFloat(value).toString().length){
				return parseFloat(value);
			}else{
				return 1;
			}
		}
    }
    ///-------------------------------------------------------------
    $scope.reload = function(){
    	$state.reload();
    }
	 ///-------------------------------------------------------------
    $scope.getAllRiskManagement();
	
}]) ////controlador

.directive('stringToNumber', function() {
  	return {
	    require: 'ngModel',
	    link: function(scope, element, attrs, ngModel) {
	        ngModel.$parsers.push(function(value) {
	        	return '' + value;
	      	});
	      	ngModel.$formatters.push(function(value) {
	        	return parseFloat(value);
	    });
    }
};////directiva
  
});//