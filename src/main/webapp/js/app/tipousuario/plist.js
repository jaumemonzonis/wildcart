'use strict'

//http://localhost:8081/json?ob=usuario&op=login&user=rigo&pass=B221D9DBB083A7F33428D7C2A3C3198AE925614D70210E28716CCAA7CD4DDB79
//http://localhost:8081/json?ob=usuario&op=check
//http://localhost:8081/json?ob=usuario&op=logout

//http://localhost:8081/json?ob=usuario&op=login&user=ddd&pass=d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1


moduleTipousuario.controller('tipousuarioPlistController', ['$scope', '$http', '$location', 'toolService', '$routeParams',
    function ($scope, $http, $location, toolService, $routeParams) {
        
        $scope.ob="tipousuario";
        $scope.totalPages = 1;

        if (!$routeParams.order) {
            $scope.orderURLServidor = "";
            $scope.orderURLCliente = "";
        } else {
            $scope.orderURLServidor = "&order=" + $routeParams.order;
            $scope.orderURLCliente = $routeParams.order;
        }

        if (!$routeParams.rpp) {
            $scope.rpp = 10;
        } else {
            $scope.rpp = $routeParams.rpp;
        }

        if (!$routeParams.page) {
            $scope.page = 1;
        } else {
            if ($routeParams.page >= 1) {
                $scope.page = $routeParams.page;
            } else {
                $scope.page = 1;
            }
        }


        $scope.resetOrder = function () {
            $location.url($scope.ob +`/plist/` + $scope.rpp + `/` + $scope.page);
        }
        $scope.view = function (id) {
            $location.url($scope.ob +`/view/${id}`);
        }

        $scope.remove= function (id) {
            $location.url($scope.ob +`/remove/${id}`);
        }

        $scope.edit= function (id) {
            $location.url($scope.ob +`/edit/${id}`);
        }

        $scope.ordena = function (order, align) {
            if ($scope.orderURLServidor == "") {
                $scope.orderURLServidor = "&order=" + order + "," + align;
                $scope.orderURLCliente = order + "," + align;
            } else {
                $scope.orderURLServidor = $scope.orderURLServidor + "-" + order + "," + align;
                $scope.orderURLCliente = $scope.orderURLCliente + "-" + order + "," + align;
            }
            $location.url($scope.ob +`/plist/` + $scope.rpp + `/` + $scope.page + `/` + $scope.orderURLCliente);
        }

        //getcount
        $http({
            method: 'GET',
            url: '/json?ob='+$scope.ob+'&op=getcount'
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataUsuariosNumber = response.data.message;
            $scope.totalPages = Math.ceil($scope.ajaxDataUsuariosNumber / $scope.rpp);
            if ($scope.page > $scope.totalPages) {
                $scope.page = $scope.totalPages;
                $scope.update();
            }
            pagination2();
        }, function (response) {
            $scope.ajaxDataUsuariosNumber = response.data.message || 'Request failed';
            $scope.status = response.status;
        });

        $http({
            method: 'GET',
            url: '/json?ob='+$scope.ob+'&op=getpage&rpp=' + $scope.rpp + '&page=' + $scope.page + $scope.orderURLServidor
        }).then(function (response) {
            $scope.status = response.status;
            $scope.ajaxDataUsuarios = response.data.message;
        }, function (response) {
            $scope.status = response.status;
            $scope.ajaxDataUsuarios = response.data.message || 'Request failed';
        });



        $scope.update = function () {
            $location.url($scope.ob +`/plist/` + $scope.rpp + `/` + $scope.page + '/' + $scope.orderURLCliente);
        }




        //paginacion neighbourhood
        function pagination2() {
            $scope.list2 = [];
            $scope.neighborhood = 3;
            for (var i = 1; i <= $scope.totalPages; i++) {
                if (i === $scope.page) {
                    $scope.list2.push(i);
                } else if (i <= $scope.page && i >= ($scope.page - $scope.neighborhood)) {
                    $scope.list2.push(i);
                } else if (i >= $scope.page && i <= ($scope.page - -$scope.neighborhood)) {
                    $scope.list2.push(i);
                } else if (i === ($scope.page - $scope.neighborhood) - 1) {
                    $scope.list2.push("...");
                } else if (i === ($scope.page - -$scope.neighborhood) + 1) {
                    $scope.list2.push("...");
                }
            }
        }

        $scope.openModal = function () {
           
        }


        $scope.isActive = toolService.isActive;



    }



]);