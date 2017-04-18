'use strict';

angular.module('myApp.projects', ['ngStorage'])

.controller('projectsViewCtrl', ['$scope','$state','$localStorage', function($scope,$state, $localStorage) {
	$scope.projectRepeat = false;
	$scope.evrm = false;
	$scope.listProject = [];
	$scope.pjt=[];
	
	$scope.seleccionar = function(proyectoSeleccionado){
		$scope.evrm = true;
		$scope.proyecto = proyectoSeleccionado;
		$localStorage.data = $scope.proyecto;
	}
	
	$scope.insertProject = function(nuevoProyecto){
		
		//Obtener todos los proyectos existentes
		ProjectDB.getCollection(function(data){
			$scope.listProject = data;
			var repetido = false;
			//Recorrer la lista de proyectos obtenidos e validar si ya existe o no
			for(var i in data){
				if($scope.listProject[i].name === nuevoProyecto){
					$scope.$apply(function(){
						$scope.projectRepeat = true;
					});
					repetido = true;
				}else{
					
				}
			}
			if(repetido === false){
				var pjtObject = { name: nuevoProyecto };
				ProjectDB.insert(pjtObject, function(data){
					$scope.getAllProject();
					//$scope.agregar = "";
					$scope.nuevoProyecto = "";
					$scope.projectRepeat = false;
				});
			}
		});
	}
	
	$scope.getAllProject = function(){

		ProjectDB.getCollection(function(data){
			$scope.$apply(function(){
				$scope.pjt = data;
			});
		});

	}///
	console.log("Estoy en projects");
	$scope.getAllProject();
}]);