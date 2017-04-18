'use strict';

angular.module('myApp.earnedValue', ['ngStorage'])

.controller('earnedValueViewCtrl', ['$scope','$state','$timeout', '$sce', '$localStorage', function($scope,$state,$timeout,$sce,$localStorage) {

	//console.log("Valor Ganado");

	$scope.evs = []; // Coleccion de valores ganados
	$scope.inputPV; //Valor planeado
	$scope.faseN; //cantidad de fases en la gestion de valor ganado
	$scope.name = $localStorage.data; //Recibe el nombre del proyecto seleccionado

	///-------------------------------------------------------------
	$scope.insertEV = function(){

		//console.log("Insertar");

		var evObject = { fase: $scope.faseN, pv: $scope.inputPV, av: "", porcentajeEV: "", nonbreProyecto: $scope.name };

		EarnedValueDB.insert(evObject, function(data){
			//console.log(data);
			$scope.getAllEarnedValue();
			$scope.inputPV = "";
		});

	}///
	///-------------------------------------------------------------
	$scope.getAllEarnedValue = function(){
		
		var requestObject = { nombreProyecto: $scope.name };

		EarnedValueDB.getCollection(requestObject, function(data){
			console.log(data);
			$scope.$apply(function(){
				$scope.evs = data;
				$scope.faseN = $scope.evs.length + 1;
			});
		});

	}///
	///-------------------------------------------------------------
	$scope.updateEV = function(e){

		//console.log("UpdateEV");

		e.av = $scope.validateValue(e.av);
		e.pv = $scope.validateValue(e.pv);
		e.porcentajeEV = $scope.validateValue(e.porcentajeEV);

		var objectToUpdate = { fase: e.fase, pv: e.pv, av: e.av, porcentajeEV: e.porcentajeEV, nonbreProyecto: e.nonbreProyecto };

		EarnedValueDB.save(objectToUpdate, function(data){
			//console.log(data);
			$state.reload();
		});

	}///
	///-------------------------------------------------------------
	$scope.calcularEV = function (PV, EV) { 
		if (isNaN(parseFloat(PV) * parseFloat(EV) / 100)) {
	    	return "---";
	    }
	    return (parseFloat(PV) * parseFloat(EV) / 100).toFixed(2);
    }
	///-------------------------------------------------------------
	$scope.calcularCV = function (PV,EV,AV) {
		if (isNaN(parseFloat(PV) * parseFloat(EV) / 100)) {
		    return "---";
		}else{
			var ev =  parseFloat(PV) * parseFloat(EV) / 100;
			if(isNaN(ev - parseFloat(AV))){
				return "---";
			}else{
				return (ev - parseFloat(AV)).toFixed(2);
			}
		}
    }
	///-------------------------------------------------------------
	$scope.calcularSV = function (PV,EV) {
		if (isNaN(parseFloat(PV) * parseFloat(EV) / 100)) {
		    return "---";
		}else{
			var ev =  parseFloat(PV) * parseFloat(EV) / 100;
			return (ev - parseFloat(PV)).toFixed(2);
		}
    }
    ///-------------------------------------------------------------
	$scope.calcularCPI = function (PV,EV,AV) {
		if (isNaN(parseFloat(PV) * parseFloat(EV) / 100)) {
		    return "---";
		}else{
			var ev =  parseFloat(PV) * parseFloat(EV) / 100;
			if(isNaN(ev / parseFloat(AV))){
				return "---";
			}else{
				return (ev / parseFloat(AV)).toFixed(2);
			}
		}
    }
    ///-------------------------------------------------------------
	$scope.calcularSPI = function (PV,EV) {
		if (isNaN(parseFloat(PV) * parseFloat(EV) / 100)) {
		    return "---";
		}else{
			var ev =  parseFloat(PV) * parseFloat(EV) / 100;
			if(isNaN(ev / parseFloat(PV))){
				return "---";
			}else{
				return (ev / parseFloat(PV)).toFixed(2);
			}
		}
    }
    ///-------------------------------------------------------------
    $scope.generateColor = function (value) {
    	if(value > 0){
    		return "green";
    	}else{
    		if(value == 0){
    			return "blue";
    		}else{
    			return "red";
    		}
    	}
    }
    ///-------------------------------------------------------------
    $scope.generateColor2 = function (value) {
    	if(value > 1){
    		return "green";
    	}else{
    		if(value == 1){
    			return "blue";
    		}else{
    			return "red";
    		}
    	}
    }
    ///-------------------------------------------------------------
    $scope.validateValue = function(value){
    	if(value.length === 0 || value === "null" || isNaN(parseFloat(value))){
			return 0;
		}else{
			if(value.length === parseFloat(value).toString().length){
				return parseFloat(value);
			}else{
				return 0;
			}
		}
    }
    ///-------------------------------------------------------------
    $scope.reload = function(){
    	$state.reload();
    }
    ///-------------------------------------------------------------
    $scope.getAllEarnedValue();

}])////controlador

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