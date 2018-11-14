"use strict";

moduleTipousuario.controller("tipousuarioEditController", [
  "$scope",
  "$http",
  "$routeParams",
  "toolService",
  "$window",
  function ($scope, $http, $routeParams, toolService,$window) {
      
       $scope.ob="tipousuario";


        if (!$routeParams.id) {
            $scope.id = 1;
        } else {
            $scope.id = $routeParams.id;
        }    
             
    $http({
      method: "GET",
       url: 'http://localhost:8081/json?ob='+$scope.ob+'&op=get&id=' + $scope.id
    }).then(function (response) {
      console.log(response);
      $scope.id = response.data.message.id;
      $scope.desc = response.data.message.desc;
    }), function (response) {
      console.log(response);
    };

$scope.isActive = toolService.isActive;
$scope.visualizar=false;
$scope.error=false;
 
    $scope.update = function () {
    
      var json = {
        id: $scope.id,
        desc: $scope.desc
      }
      $http({
        method: 'GET',
        header: {
          'Content-Type': 'application/json;charset=utf-8'
        },
        url: 'http://localhost:8081/json?ob='+$scope.ob+'&op=update',
        params: { json: JSON.stringify(json) }
      }).then(function (response) {
        console.log(response);
        $scope.visualizar=true;       
      }), function (response) {
        console.log(response);
         $scope.error=true;
      }
    }

    $scope.volver = function () {
            $window.history.back();
            }
              $scope.openModal = function () {
           
        }
  }
]);